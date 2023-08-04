package br.com.banco.controller;

import br.com.banco.domain.Transferencia;
import br.com.banco.dto.TransferenciaDto;
import br.com.banco.exceptions.ExceptionMessage;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.service.TransferenciaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest
public class TransferenciaControllerTest {

    @Autowired
    private TransferenciaController transferenciaController;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Test
    @DisplayName("Retornar lista de transferencias quando não receber nenhum parâmetro")
    public void testFindByFiltersWithNullParameters() throws ExceptionMessage {

        ResponseEntity<List<TransferenciaDto>> findTransferenciaNull = transferenciaController.findByFilters(null, null, null);

        Assertions.assertEquals(HttpStatus.OK, findTransferenciaNull.getStatusCode());
        Assertions.assertNotNull(findTransferenciaNull.getBody());

    }

}
