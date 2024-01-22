package com.seerbit.codingchallenge;

import com.seerbit.codingchallenge.Service.serviceImpl.TransactionServiceImpl;
import com.seerbit.codingchallenge.dto.TransactionDto;
import com.seerbit.codingchallenge.dto.TransactionStatisticsDto;
import com.seerbit.codingchallenge.exception.TimeExceededException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.naming.TimeLimitExceededException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionServiceImpl transactionService1;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @BeforeEach
    public void setup(){

    }

    @Test
    public void createTransaction_thenThrowNoException(){
        TransactionDto request = new TransactionDto();
        request.setAmount(new BigDecimal("1000"));
        request.setTimestamp(LocalDateTime.now());

        transactionService.createTransaction(request);
    }

    @Test
    public void createTransaction_thenThrowTimeExceededException(){
        String dateString = "2026-01-15T12:30:45";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);

        TransactionDto request = new TransactionDto();
        request.setAmount(new BigDecimal("1000"));
        request.setTimestamp(dateTime);

        org.junit.jupiter.api.Assertions.assertThrows(TimeExceededException.class, () -> {
            transactionService.createTransaction(request);
        });
    }

    @Test
    public void createTransaction_thenThrowTimeLimitExceededException(){
        String dateString = "2023-01-15T12:30:45";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);

        TransactionDto request = new TransactionDto();
        request.setAmount(new BigDecimal("1000"));
        request.setTimestamp(dateTime);

        org.junit.jupiter.api.Assertions.assertThrows(TimeLimitExceededException.class, () -> {
            transactionService.createTransaction(request);
        });
    }

    @Test
    public void getStatistics_thenThrowTimeLimitExceededException(){

        org.junit.jupiter.api.Assertions.assertThrows(TimeLimitExceededException.class, () -> {
            transactionService.getStatistics();
        });
    }

    @Test
    public void getStatistics(){

        given(transactionService1.getLastTransactionTime()).willReturn(LocalDateTime.now());

        TransactionStatisticsDto transactionStatisticsDto = transactionService1.getStatistics();

        assertThat(transactionStatisticsDto).isEqualTo(null);
    }
}
