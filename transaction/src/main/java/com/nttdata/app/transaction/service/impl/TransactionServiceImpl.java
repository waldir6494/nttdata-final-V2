package com.nttdata.app.transaction.service.impl;

import com.nttdata.app.transaction.client.account.AccountClient;
import com.nttdata.app.transaction.client.account.model.Account;
import com.nttdata.app.transaction.client.account.model.CreateAccount;
import com.nttdata.app.transaction.client.product.ProductClient;
import com.nttdata.app.transaction.exceptions.RequirementFailedException;
import com.nttdata.app.transaction.model.AccountModel;
import com.nttdata.app.transaction.model.TransactionCreate;
import com.nttdata.app.transaction.model.entity.Transaction;
import com.nttdata.app.transaction.repository.TransactionRepository;
import com.nttdata.app.transaction.repository.TypeTransactionRepository;
import com.nttdata.app.transaction.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.logging.Logger;

@Service
public class TransactionServiceImpl implements ITransactionService {
    private final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final Predicate<Float> verifyWithdrawal = balance -> balance >= 0;
    private final BiPredicate<Float, Float> verifyCredit = (firstAmount, secondAmount) -> firstAmount <= secondAmount;
    private final BiPredicate<LocalDate, LocalDate> verifyDate = (firstDate, secondDate) -> firstDate.equals(secondDate);
    @Autowired
    AccountClient accountClient;
    @Autowired
    ProductClient productClient;
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TypeTransactionRepository typeTransactionRepository;

    /**
     * Realiza un deposito en una cuenta de debito
     *
     * @param transactionCreate - Datos de la transacción a crear.
     * @return - La transaccion creada
     */
    @Override
    public Transaction deposit(TransactionCreate transactionCreate) {
        try{
            //Obtiene los datos de la cuenta del microservicio account y lo mapea en un AccountModel
            AccountModel account = this.getAccount(transactionCreate.getAccountId());

            //Se verifica que la cuenta sea de debito
            this.checkTypeAccount(() -> account.getProduct().getTypeProduct().getIsCredit());

            //Se verifica si hay una limitacion de dias para el movimiento
            if(this.checkConditionalsDebit(() -> account.getProduct().isMovementDayEspecific(), () ->  !this.verifyDate.test(account.getProduct().getDayEspecificDate().toInstant().atZone(ZoneId.of("America/Lima")).toLocalDate(), new Date().toInstant().atZone(ZoneId.of("America/Lima")).toLocalDate())) )
                throw new RequirementFailedException("Este tipo no puede realizar movimientos en este dia.");

            //Se verifica si hay una limitacion de cantidad de movimientos
            if(this.checkConditionalsDebit(() -> account.getProduct().isHaveLimitMovement(), () -> account.getCurrentMovement() >= account.getProduct().getMaxMovement()))
                throw new RequirementFailedException("Este tipo de cuenta no acepta esta operacion o alcanzo el limite de movimientos.");

            account.setBalance(transactionCreate.getAmount() + account.getBalance());

            //Se actualiza la cuenta con el nuevo balance y cantidad de movimientos
            return this.saveAndUpdateTransaction(account, transactionCreate);

        } catch (RequirementFailedException | HttpClientErrorException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Retorna un retiro en una cuenta de debito
     *
     * @param transactionCreate - Datos de la transacción a crear.
     * @return - La transaccion creada
     */
    @Override
    public Transaction withdrawal(TransactionCreate transactionCreate) {

        try{
            //Obtiene los datos de la cuenta del microservicio account y lo mapea en un AccountModel
            AccountModel account = this.getAccount(transactionCreate.getAccountId());

            //Se verifica que la cuenta sea de debito
            this.checkTypeAccount(() ->account.getProduct().getTypeProduct().getIsCredit());

            //Se verifica si hay una limitacion de dias para el movimiento
            if(this.checkConditionalsDebit(() -> account.getProduct().isMovementDayEspecific(), () ->  !this.verifyDate.test(account.getProduct().getDayEspecificDate().toInstant().atZone(ZoneId.of("America/Lima")).toLocalDate(), new Date().toInstant().atZone(ZoneId.of("America/Lima")).toLocalDate())) )
                throw new RequirementFailedException("Este tipo no puede realizar movimientos en este dia.");

            //Se verifica si hay una limitacion de cantidad de movimientos
            if(this.checkConditionalsDebit(() -> account.getProduct().isHaveLimitMovement(), () -> account.getCurrentMovement() >= account.getProduct().getMaxMovement()))
                throw new RequirementFailedException("Este tipo de cuenta no acepta esta operacion o alcanzo el limite de movimientos.");

            if(!this.verifyWithdrawal.test(account.getBalance() - transactionCreate.getAmount()))
                throw new RequirementFailedException("No tienes fondos suficientes para realizar esta operacion.");

            account.setBalance(account.getBalance() - transactionCreate.getAmount());
            return this.saveAndUpdateTransaction(account, transactionCreate);
        } catch (RequirementFailedException | HttpClientErrorException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Realiza la creacion de la transaccion
     *
     * @param account - Datos de la cuenta.
     * @param transactionCreate - Datos de la transaccion.
     * @return - La transaccion creada
     */
    private Transaction saveAndUpdateTransaction (AccountModel account, TransactionCreate transactionCreate) throws RequirementFailedException, HttpClientErrorException {
        //se aumenta la cantidad de movimientos
        account.setCurrentMovement(account.getCurrentMovement()+1);

        //Actualiza la cuenta con el nuevo balance y movimentos
        this.updateAccount(account, transactionCreate);
        Transaction transaction = new Transaction();
        transaction.setTypeTransaction(this.typeTransactionRepository.findAll().stream().filter(type -> type.getId().equals(transactionCreate.getTypeTransactionId())).findFirst()
                .orElseThrow(() -> new RequirementFailedException("Este tipo de operacion no se encuentra registrado.")));
        transaction.setAmount(transactionCreate.getAmount());
        transaction.setAccountId(account.getId());
        transaction.setDateTransaction(new Date());
        return this.transactionRepository.save(transaction);
    }

    /**
     * Realiza el pago de una tarjeta de credito
     *
     * @param transactionCreate - Datos de la transaccion.
     * @return - La transaccion creada
     */
    @Override
    public Transaction paymentCredit(TransactionCreate transactionCreate) {
        try{
            //Obtiene los datos de la cuenta del microservicio accounts
            AccountModel account = this.getAccount(transactionCreate.getAccountId());

            //verifica que sea una cuenta de credito
            this.checkTypeAccount(() -> !account.getProduct().getTypeProduct().getIsCredit());

            //verifica que el pago no supere la deuda de credito
            if(!this.verifyWithdrawal.test(account.getBalance() - transactionCreate.getAmount()))
                throw new RequirementFailedException("El monto ingresado supera su deuda de credito.");

            account.setBalance(account.getBalance() - transactionCreate.getAmount());

            return this.saveAndUpdateTransaction(account, transactionCreate);

        } catch (RequirementFailedException | HttpClientErrorException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Carga un consumo en la tarjeta de credito
     *
     * @param transactionCreate - Datos de la transaccion.
     * @return - La transaccion creada
     */
    @Override
    public Transaction useCredit(TransactionCreate transactionCreate) {
        try{
            //Obtiene los datos de la cuenta del microservicio accounts
            AccountModel account = this.getAccount(transactionCreate.getAccountId());

            //verifica que sea una cuenta de credito
            this.checkTypeAccount(() ->!account.getProduct().getTypeProduct().getIsCredit());

            //verifica que el consumo no exceda el limite de la linea de credito
            if(!this.verifyCredit.test(account.getBalance() + transactionCreate.getAmount(), account.getCredit()))
                throw new RequirementFailedException("Usted no puede exceder su linea de credito.");

            account.setBalance(account.getBalance() + transactionCreate.getAmount());

            return this.saveAndUpdateTransaction(account, transactionCreate);

        } catch (RequirementFailedException | HttpClientErrorException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public Transaction show(Long id) {
        return this.transactionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Transaction> findByAccount(Long id) {
        return this.transactionRepository.findByAccountId(id);
    }

    @Override
    public List<Transaction> all() {
        return this.transactionRepository.findAll();
    }

    private void checkTypeAccount(Supplier<Boolean> isCredit) throws RequirementFailedException{
        if(isCredit.get())
            throw new RequirementFailedException("Este tipo de cuenta no acepta esta operacion.");
    }

    /**
     * Realiza la verificacion de dos condicionales con lazy evaluation
     *
     * @param haveMaxMovement - Confirmacion de limite de movimientos.
     * @param maxMovement - Maximo movimiento permitido.
     * @return - Boolean de la operacion
     */
    private boolean checkConditionalsDebit(Supplier<Boolean> haveMaxMovement, Supplier<Boolean> maxMovement) {
        return (haveMaxMovement.get() && maxMovement.get());
    }

    /**
     * Realiza la actualizacion de la cuenta pudiendo ser de credito o debito
     *
     * @param account - Cuenta con los datos actualizados.
     * @param transactionCreate - Objeto con el id de la cuenta a actualizar.
     */
    private void updateAccount(AccountModel account, TransactionCreate transactionCreate) throws HttpClientErrorException {
        this.accountClient.putAccountFeign(
                new CreateAccount(null, null, account.getBalance(), account.getCredit(), account.getCurrentMovement()), transactionCreate.getAccountId());
    }

    /**
     * Obtiene los datos de una cuenta del microservicio accounts
     *
     * @param id - Identificador de la cuenta.
     * @return - Boolean de la operacion
     */
    private AccountModel getAccount(Long id) throws HttpClientErrorException {
        Account accountClient = this.accountClient.getAccountFeign(id);
        return new AccountModel(
                accountClient.getId(),
                accountClient.getBalance(),
                accountClient.getCredit(),
                accountClient.getCurrentMovement(),
                this.productClient.getProductFeign(accountClient.getProduct())
        );
    }

}
