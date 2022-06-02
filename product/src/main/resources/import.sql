INSERT INTO TYPE_PRODUCT VALUES(1,false,'Debito');
INSERT INTO TYPE_PRODUCT VALUES(2,true,'Credito');

--INSERT INTO PRODUCT VALUES(ID,AMOUNT-MAINTENANCE,DAY-SPECIFIC-DATE,HAVALIMITMOVEN,MAINTENANCE,MAX-MOVEM,MOVENTDAYESPECIFI,NAME,TYPRODCUTID);
INSERT INTO PRODUCT VALUES(1,0,null,true,false,10,false,'Ahorro',1);
INSERT INTO PRODUCT VALUES(2,5,null,false,true,0,false,'Cuenta Corriente',1);
INSERT INTO PRODUCT VALUES(3,0,'2022-06-03 00:19:25',false,false,1,true,'Plazo Fijo',1);
INSERT INTO PRODUCT VALUES(4,0,null,false,false,0,false,'Credito Personal',2);
INSERT INTO PRODUCT VALUES(5,0,null,false,true,0,false,'Credito Empresarial',2);

--INSERT INTO LIMI VALUES(ID,Limit-Account,Max-account,Type-Customer,Product-Id);
INSERT INTO LIMI VALUES(1,true,1,1,1);-- Limite para una cuenta de ahorro cliente Personal
INSERT INTO LIMI VALUES(2,true,1,1,2);-- Limite para una cuenta de cuenta corriente cliente Personal
INSERT INTO LIMI VALUES(3,true,1,1,3);-- Limite para una cuenta plazo fijo cliente Personal
INSERT INTO LIMI VALUES(4,true,1,1,4);-- Limite para una Cuenta de credito cliente Personal
INSERT INTO LIMI VALUES(5,false,0,2,5);--Limite para una cuenta Credito cliente Empresarial
INSERT INTO LIMI VALUES(6,false,0,2,2);--Limite para una cuenta Corriente cliente Empresarial




