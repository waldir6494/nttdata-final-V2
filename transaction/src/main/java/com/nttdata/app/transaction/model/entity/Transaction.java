package com.nttdata.app.transaction.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne  // Foreign Key
    @JoinColumn(name = "type_id")
    private TypeTransaction typeTransaction;

    @Column(name = "amount", nullable = false)
    private Float amount = 0f;

    @Column(name = "account_id", nullable = false)
    private Long accountId;
}
