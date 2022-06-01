package com.nttdata.app.customer.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @Column(name = "surname", length = 50, nullable = false)
    private String surname;

    @Column(name = "dni", length = 8, nullable = false)
    private String dni;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeCustomer type;
}
