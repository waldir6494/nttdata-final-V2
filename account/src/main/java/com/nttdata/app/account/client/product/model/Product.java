package com.nttdata.app.account.client.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private long id;
    private String name;
    private boolean maintenance;
    private boolean movementDayEspecific;
    private boolean haveLimitMovement;
    private Integer amountMaintenance;
    private Integer maxMovement;
    private Date dayEspecificDate;
    private TypeProduct TypeProduct;
}
