package br.com.banco.controller;

import br.com.banco.dto.TransferenciaDto;
import br.com.banco.exceptions.ExceptionMessage;
import br.com.banco.service.TransferenciaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.security.auth.callback.ConfirmationCallback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TransferenciaControllerTest {

    @InjectMocks
    private TransferenciaController transferenciaController;
    @Mock
    private TransferenciaService transferenciaService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByFiltersWithNullParameters() throws ExceptionMessage {
        ResponseEntity<List<TransferenciaDto>> findTransferencias = transferenciaController.findByFilters(null, null, null);

        Assertions.assertEquals(200,findTransferencias.getStatusCodeValue());
        Assertions.assertEquals(0, findTransferencias.getBody().size());

        Mockito.verify(transferenciaService, Mockito.times(1)).findByFilters(null, null, null);
    }

}
