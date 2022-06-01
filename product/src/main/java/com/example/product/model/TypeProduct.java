package com.example.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "type_product")
public class TypeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name",length = 30)
    String name;
    @Column(name = "is_credit")
    Boolean isCredit;
//    @OneToMany(mappedBy = "TypeProduct",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    List<Product> products;
}