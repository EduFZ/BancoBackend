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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
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
        //List<TransferenciaDto> listTransferencia = new ArrayList<>(List.of(TransferenciaCreator.createTransferencia()));
        List<TransferenciaDto> listaTransf = Arrays.asList(TransferenciaCreator.createTransferenciaDto());
        BDDMockito.when(transferenciaServiceMock.findByFilters(any(), any(), any())).thenReturn(listaTransf);
    }

    @Test
    public void testFindByFiltersWithNullParameters() throws ExceptionMessage {
        String expectedName = TransferenciaCreator.createTransferenciaDto().getNomeOperador();

        List<TransferenciaDto> findTransferencias = transferenciaController.findByFilters(null, null, null).getBody();

        Assertions.assertEquals(expectedName, findTransferencias.get(0).getNomeOperador());
        Assertions.assertNotNull(findTransferencias);
        Assertions.assertNotNull(findTransferencias);
        Assertions.assertFalse(findTransferencias.isEmpty());

    }

}
