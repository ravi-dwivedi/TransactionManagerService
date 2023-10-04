package com.transaction.manager.service.controllers;

import com.transaction.manager.service.dto.Transaction;
import com.transaction.manager.service.service.TransactionService;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction-manager/transaction")
@CrossOrigin(origins = "*")
@Log4j2
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(@NonNull final TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getTransactions(
            @RequestParam int page,
            @RequestParam int size) {
        return transactionService.getTransactions(page, size);
    }
}
