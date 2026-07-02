package com.anish.transaction.TxnCapture.service;

import com.anish.transaction.TxnCapture.dto.CreateTransactionRequest;
import com.anish.transaction.TxnCapture.exception.TransactionNotFoundException;
import com.anish.transaction.TxnCapture.model.TransactionModel;
import com.anish.transaction.TxnCapture.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionModel postTransaction(CreateTransactionRequest request){
        String txnId = request.getTransactionUUID();
        if(transactionRepository.existsById(txnId)) return getTransactionById(txnId);
        TransactionModel model = new TransactionModel();
        model.setTransactionUUID(txnId);
        model.setTransactionAmount(request.getTransactionAmount());
        model.setCreatedAt(LocalDateTime.now());
        model.setTransactionStatus("PENDING");

        return transactionRepository.save(model);
    }

    public TransactionModel getTransactionById(String id){
        return transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException("Transaction not found for id:"+id));
    }

    public List<TransactionModel> getAllTransactions() {
        return transactionRepository.findAll();
    }

}
