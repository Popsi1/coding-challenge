package com.seerbit.codingchallenge.Service.serviceImpl;

import com.seerbit.codingchallenge.Service.TransactionService;
import com.seerbit.codingchallenge.dto.TransactionDto;
import com.seerbit.codingchallenge.dto.TransactionStatisticsDto;
import com.seerbit.codingchallenge.exception.TimeExceededException;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.naming.TimeLimitExceededException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
@Getter
public class TransactionServiceImpl implements TransactionService {

    private BigDecimal sum = BigDecimal.ZERO;
    private BigDecimal max = BigDecimal.ZERO;
    private BigDecimal min = BigDecimal.ZERO;
    private long count = 0;

    private LocalDateTime lastTransactionTime;

    private static final int STATISTICS_INTERVAL_SECONDS = 30;

    @SneakyThrows
    @Override
    public synchronized void createTransaction(TransactionDto request) {

        LocalDateTime now = LocalDateTime.now();

        if (request.getTimestamp().isAfter(now)) {
            throw new TimeExceededException();
        }
        System.out.println(now);

        if (Duration.between(request.getTimestamp(), now).getSeconds() > STATISTICS_INTERVAL_SECONDS) {
            throw new TimeLimitExceededException();
        }
        updateStatistics(request.getAmount());
        lastTransactionTime = request.getTimestamp();
    }

    @SneakyThrows
    @Override
    public synchronized TransactionStatisticsDto getStatistics() {
        if (lastTransactionTime == null || Duration.between(lastTransactionTime, LocalDateTime.now()).getSeconds() > STATISTICS_INTERVAL_SECONDS) {
            deleteTransaction();
            throw new TimeLimitExceededException();
        }
        BigDecimal avg = sum.divide(BigDecimal.valueOf(count), 2, BigDecimal.ROUND_HALF_UP);
        return new TransactionStatisticsDto(sum, avg, max, min, count);
    }

    @Override
    public void deleteTransaction() {
        sum = BigDecimal.ZERO;
        max = BigDecimal.ZERO;
        min = BigDecimal.ZERO;
        count = 0;
        lastTransactionTime = null;
    }

    private void updateStatistics(BigDecimal amount) {
        sum = sum.add(amount);
        count++;

        if (count == 1 || amount.compareTo(max) > 0) {
            max = amount;
        }

        if (count == 1 || amount.compareTo(min) < 0) {
            min = amount;
        }
    }
}
