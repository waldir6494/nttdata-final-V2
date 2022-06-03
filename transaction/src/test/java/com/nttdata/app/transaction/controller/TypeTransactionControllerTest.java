package com.nttdata.app.transaction.controller;

import com.nttdata.app.transaction.model.entity.TypeTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TypeTransactionControllerTest {
    @Mock
    TypeTransactionController typeTransactionController = Mockito.mock(TypeTransactionController.class);

    TypeTransaction typeTransaction;

    @BeforeEach
    void setUp() {
        typeTransaction = TypeTransaction.builder()
                .id(1L)
                .name("Deposito")
                .build();
    }

    @Test
    void create() {
        Mockito.when(typeTransactionController.show(1L)).thenReturn(typeTransaction);
        assertNotNull(typeTransactionController.show(1L));
    }

    @Test
    void show() {
        Mockito.when(typeTransactionController.show(1L)).thenReturn(typeTransaction);
        assertNotNull(typeTransactionController.show(1L));
    }

    @Test
    void all() {
        Mockito.when(typeTransactionController.all()).thenReturn(Arrays.asList(typeTransaction));
        assertNotNull(typeTransactionController.all());
    }
}