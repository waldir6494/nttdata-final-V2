package com.example.product.controller;

import com.example.product.client.customer.model.TypeCustomer;
import com.example.product.model.TypeProduct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TypeProductControllerTest {

    @Mock
    TypeProductController typeProductController = Mockito.mock(TypeProductController.class);
    TypeProduct typeProduct;

    @BeforeEach
    void setUp() {
        typeProduct = TypeProduct.builder()
                .id(1L)
                .isCredit(false)
                .name("Debito")
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void all() {
        Mockito.when(typeProductController.all()).thenReturn(Arrays.asList(typeProduct));
        assertNotNull(typeProductController.all());
    }

    @Test
    void show() {
        Mockito.when(typeProductController.show(1L)).thenReturn(typeProduct);
        assertNotNull(typeProductController.show(1L));
    }
}