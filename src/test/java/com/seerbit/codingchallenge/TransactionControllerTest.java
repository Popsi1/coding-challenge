package com.seerbit.codingchallenge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seerbit.codingchallenge.Service.TransactionService;
import com.seerbit.codingchallenge.dto.TransactionDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TransactionControllerTest {

    final String basePath = "/api/transactions";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createTransaction() throws Exception {

        TransactionDto request = new TransactionDto();
        request.setAmount(new BigDecimal("1000"));
        request.setTimestamp(LocalDateTime.now());

        ResultActions response = mockMvc.perform(post(basePath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        response.andDo(print()).
                andExpect(status().isCreated());

    }

    @Test
    public void getTransactionStatistics() throws Exception{

        ResultActions response = mockMvc.perform(get(basePath+ "/statistics"));

        response.andExpect(status().isOk());
    }

    @Test
    public void deleteTransaction() throws Exception{

        ResultActions response = mockMvc.perform(delete(basePath));

        response.andExpect(status().isNoContent());
    }
}
