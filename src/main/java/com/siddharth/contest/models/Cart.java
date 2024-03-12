package com.siddharth.contest.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter

public class Cart {
    private Long Id;
    private Long UserId;
    private Timestamp date;
    private List<ProductQuantity> products;


}




