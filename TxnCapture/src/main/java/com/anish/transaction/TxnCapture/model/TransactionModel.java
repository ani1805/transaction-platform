package com.anish.transaction.TxnCapture.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionModel {
    private String transactionUUID;

    private BigDecimal transactionAmount;

    private String transactionStatus;

    private LocalDateTime createdAt;
}
