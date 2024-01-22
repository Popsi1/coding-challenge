package com.seerbit.codingchallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionStatisticsDto {
    private BigDecimal sum;
    private BigDecimal average;
    private BigDecimal max;
    private BigDecimal min;
    private long count;
}

