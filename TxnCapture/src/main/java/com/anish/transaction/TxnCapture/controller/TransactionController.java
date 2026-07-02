package com.anish.transaction.TxnCapture.controller;

import com.anish.transaction.TxnCapture.dto.CreateTransactionRequest;
import com.anish.transaction.TxnCapture.exception.TransactionNotFoundException;
import com.anish.transaction.TxnCapture.model.TransactionModel;
import com.anish.transaction.TxnCapture.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transactions")
    public ResponseEntity<?> createTransaction(@RequestBody CreateTransactionRequest request){
        TransactionModel transaction = transactionService.postTransaction(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<?> getTransactionsById(@PathVariable String id){
        TransactionModel transaction = transactionService.getTransactionById(id);
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }

    @GetMapping("/transactions")
    public ResponseEntity<?> getAllTransactions(){
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.getAllTransactions());
    }
}
