package com.nttdata.app.customer.controller;

import com.nttdata.app.customer.model.entity.TypeCustomer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TypeCustomerControllerTest {

    @Mock
    TypeCustomerController typeCustomerController = Mockito.mock(TypeCustomerController.class);

    TypeCustomer typeCustomer;

    @BeforeEach
    void setUp() {
        typeCustomer = TypeCustomer.builder()
                .id(2L)
                .description("Empresarial")
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void all() {
        Mockito.when(typeCustomerController.all()).thenReturn(Arrays.asList(typeCustomer));
        assertNotNull(typeCustomerController.all());
    }

    @Test
    void show() {
        Mockito.when(typeCustomerController.show(2L)).thenReturn(typeCustomer);
        assertNotNull(typeCustomerController.show(2L));
    }
}