package com.nttdata.app.account.model.entity;

import com.nttdata.app.account.client.customer.model.Customer;
import com.nttdata.app.account.client.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float balance;
    @Column(name = "current_movement")
    private Integer currentMovement;
    @Column(name = "product_id")
    private Integer product;
    private Float credit;
    @Column(name = "customer_id")
    private Integer customer;  //Cliente Personal o Empresarial
}
