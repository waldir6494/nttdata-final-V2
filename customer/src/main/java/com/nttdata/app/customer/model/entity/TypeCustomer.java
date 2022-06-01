package com.nttdata.app.customer.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="type_customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TypeCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "description", length = 30, nullable = false)
    String description;
}
