package com.transaction.manager.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    private Long id;
    private String sender;
    private String receiver;
    private BigDecimal totalAmount;
    private BigDecimal totalPaidAmount;
}
