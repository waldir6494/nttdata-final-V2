package com.nttdata.app.account.model;

import com.nttdata.app.account.model.entity.Account;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class CreateStaff {


    private String type; // TITULAR OR SIGNATORE
    private String name;
    private String surname;
    private String dni;

    private Integer account_id;
}
