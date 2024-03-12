package com.siddharth.contest.cartDtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class FakeStoreDto {
    private Long id;
    private Long userId;
    private Timestamp date;
    private List<ProductQuantityDto> products;
}



