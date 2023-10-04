package com.transaction.manager.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Payment {

    private Long id;
    private Long parentId;
    private String sender;
    private String receiver;
    private BigDecimal totalAmount;
    private BigDecimal paidAmount;
}
