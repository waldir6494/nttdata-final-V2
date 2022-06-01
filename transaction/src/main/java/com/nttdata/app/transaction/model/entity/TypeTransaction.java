package com.nttdata.app.transaction.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Entity
@Table(name = "type_transactions")
@NoArgsConstructor
@Data
public class TypeTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @OneToMany(mappedBy = "typeTransaction", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
