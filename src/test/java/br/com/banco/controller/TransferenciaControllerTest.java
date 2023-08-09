package br.com.banco.controller;

import br.com.banco.dto.TransferenciaDto;
import br.com.banco.exceptions.ExceptionMessage;
import br.com.banco.service.TransferenciaService;
import br.com.banco.util.TransferenciaCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class TransferenciaControllerTest {

    @InjectMocks
    private TransferenciaController transferenciaController;
    @Mock
    private TransferenciaService transferenciaServiceMock;

    @BeforeEach
    void setUp() throws ExceptionMessage {
        List<TransferenciaDto> listTransferencia = new ArrayList<>(List.of(TransferenciaCreator.createTransferencia()));
        BDDMockito.when(transferenciaServiceMock.findByFilters(any(), any(), any())).thenReturn(listTransferencia);
    }

    @Test
    public void testFindByFiltersWithNullParameters() throws ExceptionMessage {

        ResponseEntity<List<TransferenciaDto>> findTransferencias = transferenciaController.findByFilters(null, null, null);

        List<TransferenciaDto> listTransferencia = findTransferencias.getBody();

        Assertions.assertNotNull(findTransferencias);
        Assertions.assertNotNull(listTransferencia);
        Assertions.assertFalse(listTransferencia.isEmpty());

    }

}
