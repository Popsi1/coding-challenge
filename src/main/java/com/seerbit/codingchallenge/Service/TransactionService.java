package com.seerbit.codingchallenge.Service;

import com.seerbit.codingchallenge.dto.TransactionDto;
import com.seerbit.codingchallenge.dto.TransactionStatisticsDto;


public interface TransactionService {

    public void createTransaction(TransactionDto request);

    public TransactionStatisticsDto getStatistics();

    public void deleteTransaction();
}










