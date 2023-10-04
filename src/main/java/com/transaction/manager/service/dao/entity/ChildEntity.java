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
public class ChildEntity {
    private Long id;
    private Long parentId;
    private BigDecimal paidAmount;
}
