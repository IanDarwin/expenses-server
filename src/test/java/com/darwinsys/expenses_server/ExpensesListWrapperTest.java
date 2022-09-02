package com.darwinsys.expenses_server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpensesListWrapperTest {
    final static String DATA = """
        {"expense":[
            {"description":"Description0","amount":"10.1", "expenseDate":"1303492691292"},
            {"description":"Description1","amount":123.45, "expenseDate": 1303495291292}
         ]}
         """;
    @Test
    public void testlistWrapper() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExpenseListWrapper listWrapper = mapper.readValue(DATA, ExpenseListWrapper.class);
        assertEquals(2, listWrapper.getExpensesList().size());
    }
}

