package com.anish.transaction.TxnCapture.service;

import com.anish.transaction.TxnCapture.dto.CreateTransactionRequest;
import com.anish.transaction.TxnCapture.model.TransactionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TransactionService {

    private final Map<String, TransactionModel> map = new ConcurrentHashMap<>();

    public TransactionModel postTransaction(CreateTransactionRequest request){
        String txnId = request.getTransactionUUID();
        if(map.containsKey(txnId)) return map.get(txnId);

        TransactionModel model = new TransactionModel();
        model.setTransactionUUID(txnId);
        model.setTransactionAmount(request.getTransactionAmount());
        model.setCreatedAt(LocalDateTime.now());
        model.setTransactionStatus("PENDING");

        map.put(txnId, model);

        return model;
    }

    public TransactionModel getTransactionById(String id){
        if(map.containsKey(id)){
            return map.get(id);
        }
        return null;
    }

    public List<TransactionModel> getAllTransactions() {
        return map.values().stream().toList();
    }

}
