package com.nttdata.app.transaction.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Entity
@Table(name = "type_transactions")
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class TypeTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 40, nullable = false)
    private String name;

}
