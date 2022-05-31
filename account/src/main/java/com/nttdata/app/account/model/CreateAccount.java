package com.nttdata.app.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateAccount {
    private Long customer_id;
    private Integer product_id;
    private Float balance;
    private Float credit;
    private Integer currentMovement;


}
