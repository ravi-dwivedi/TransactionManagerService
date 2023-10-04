package com.transaction.manager.service.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ParentEntity {

    private Long id;
    private String sender;
    private String receiver;
    private BigDecimal totalAmount;
}
