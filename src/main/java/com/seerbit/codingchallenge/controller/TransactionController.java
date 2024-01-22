package com.seerbit.codingchallenge.controller;

import com.seerbit.codingchallenge.Service.TransactionService;
import com.seerbit.codingchallenge.dto.TransactionDto;
import com.seerbit.codingchallenge.dto.TransactionStatisticsDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@NoArgsConstructor
@Setter
@AllArgsConstructor
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> createTransaction(@Valid @RequestBody TransactionDto request) {
        transactionService.createTransaction(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/statistics")
    public ResponseEntity<TransactionStatisticsDto> getTransactionStatistics() {
        return ResponseEntity.ok(transactionService.getStatistics());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTransaction() {
        transactionService.deleteTransaction();
        return ResponseEntity.noContent().build();
    }
}
