package com.nttdata.app.account.model.entity;

import com.nttdata.app.account.model.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@Entity
@Table(name = "staff")
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type",length = 30)
    private String type; // TITULAR OR SIGNATORE
    @Column(name = "name",length = 30)
    private String name;
    @Column(name = "surname",length = 30)
    private String surname;
    @Column(name = "dni",length = 8)
    private String dni;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
