package com.example.product.model;

import com.example.product.client.customer.model.TypeCustomer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "limi")
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "type_customer")
    Integer typeCustomer;
    @Column(name = "limit_account")
    Boolean limitAccount;
    @Column(name = "max_account")
    Integer maxAccount;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
}
