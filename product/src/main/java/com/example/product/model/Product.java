package com.example.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",length = 25,nullable = false)
    private String name;
    private boolean maintenance;
    @Column(name = "movement_day_especific")
    private boolean movementDayEspecific;
    @Column(name = "have_limit_movemet")
    private boolean haveLimitMovement;
    @Column(name = "amount_maintenance",length = 3)
    private Integer amountMaintenance;
    @Column(name = "max_movement",length = 3)
    private Integer maxMovement;
    @Temporal(TemporalType.DATE)
    private Date dayEspecificDate;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name="type_product_id")
    private TypeProduct TypeProduct;
}
