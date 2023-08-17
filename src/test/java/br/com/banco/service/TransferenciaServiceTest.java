package br.com.banco.service;

import br.com.banco.domain.Transferencia;
import br.com.banco.dto.TransferenciaDto;
import br.com.banco.exceptions.ExceptionMessage;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.util.TransferenciaCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
public class TransferenciaServiceTest {

    @InjectMocks
    private TransferenciaService transferenciaService;
    @Mock
    private TransferenciaRepository transferenciaRepositoryMock;

    @BeforeEach
    void setUp() throws ExceptionMessage {
        List<Transferencia> listaTransf = Arrays.asList(TransferenciaCreator.createTransferencia());
        BDDMockito.when(transferenciaRepositoryMock.findAll()).thenReturn(listaTransf);

        BDDMockito.when(transferenciaRepositoryMock.findByOperadorTransacaoAndDataBetween(any(), any(), any())).thenReturn(listaTransf);

        BDDMockito.when(transferenciaRepositoryMock.findByNomeOperadorTransacao(any())).thenReturn(listaTransf);

        BDDMockito.when(transferenciaRepositoryMock.findTransferenciaFromDataInicial(any())).thenReturn(listaTransf);

        BDDMockito.when(transferenciaRepositoryMock.findTransferenciaUntilDataFinal(any())).thenReturn(listaTransf);

        BDDMockito.when(transferenciaRepositoryMock.findByDataBetween(any(), any())).thenReturn(listaTransf);
    }

    @Test
    public void testFindByFiltersWithNullParameters() throws ExceptionMessage {
        String expectedName = TransferenciaCreator.createTransferencia().getNomeOperadorTransacao();

        List<Transferencia> findTransferencias = transferenciaRepositoryMock.findAll();

        System.out.println("Nome esperado: " + expectedName);
        System.out.println("Nome retornado: " + findTransferencias.get(0).getNomeOperadorTransacao());

        Assertions.assertEquals(expectedName, findTransferencias.get(0).getNomeOperadorTransacao());
        Assertions.assertNotNull(findTransferencias);
        Assertions.assertNotNull(findTransferencias);
        Assertions.assertFalse(findTransferencias.isEmpty());

    }

    @Test
    public void testFindByFiltersWithAllParameters() throws ExceptionMessage {
        String expectedName = TransferenciaCreator.createTransferencia().getNomeOperadorTransacao();

        List<Transferencia> findTransferencias = transferenciaRepositoryMock
                .findByOperadorTransacaoAndDataBetween("Ciclano", LocalDate.of(2019, 05, 15),
                        LocalDate.of(2020, 03, 18));

        System.out.println("Nome esperado: " + expectedName);
        System.out.println("Nome retornado: " + findTransferencias.get(0).getNomeOperadorTransacao());

        Assertions.assertEquals(expectedName, findTransferencias.get(0).getNomeOperadorTransacao());
        Assertions.assertNotNull(findTransferencias);
        Assertions.assertNotNull(findTransferencias);
        Assertions.assertFalse(findTransferencias.isEmpty());

    }

    @Test
    public void testFindByFiltersWithNameParameter() throws ExceptionMessage {
        String expectedName = TransferenciaCreator.createTransferencia().getNomeOperadorTransacao();

        List<Transferencia> findTransferencias = transferenciaRepositoryMock
                .findByNomeOperadorTransacao("Ciclano");

        String nomeColetado = findTransferencias.toString();

        System.out.println("Nome esperado: " + expectedName);
        System.out.println("Nome retornado: " + transferenciaRepositoryMock.findByNomeOperadorTransacao("Ciclano"));

        Assertions.assertEquals(expectedName, nomeColetado);
        Assertions.assertNotNull(findTransferencias);
        Assertions.assertNotNull(findTransferencias);
        Assertions.assertFalse(findTransferencias.isEmpty());

    }

    @Test
    public void testFindByFiltersWithDataInicialParameter() throws ExceptionMessage {
        String expectedName = TransferenciaCreator.createTransferencia().getNomeOperadorTransacao();

        List<Transferencia> findTransferencias = transferenciaRepositoryMock
                .findTransferenciaFromDataInicial(LocalDate.of(2019, 05, 15));

        System.out.println("Nome esperado: " + expectedName);
        System.out.println("Nome retornado: " + findTransferencias.get(0).getNomeOperadorTransacao());

        Assertions.assertEquals(expectedName, findTransferencias.get(0).getNomeOperadorTransacao());
        Assertions.assertNotNull(findTransferencias);
        Assertions.assertNotNull(findTransferencias);
        Assertions.assertFalse(findTransferencias.isEmpty());

    }

    @Test
    public void testFindByFiltersUntilDataFinalParameter() throws ExceptionMessage {
        String expectedName = TransferenciaCreator.createTransferencia().getNomeOperadorTransacao();

        List<Transferencia> findTransferencias = transferenciaRepositoryMock
                .findTransferenciaFromDataInicial(LocalDate.of(2020, 03, 18));

        System.out.println("Nome esperado: " + expectedName);
        System.out.println("Nome retornado: " + findTransferencias.get(0).getNomeOperadorTransacao());

        Assertions.assertEquals(expectedName, findTransferencias.get(0).getNomeOperadorTransacao());
        Assertions.assertNotNull(findTransferencias);
        Assertions.assertNotNull(findTransferencias);
        Assertions.assertFalse(findTransferencias.isEmpty());

    }

    @Test
    public void testFindByFiltersWithAllDataParameters() throws ExceptionMessage {
        String expectedName = TransferenciaCreator.createTransferencia().getNomeOperadorTransacao();

        List<Transferencia> findTransferencias = transferenciaRepositoryMock
                .findByDataBetween(LocalDate.of(2019, 05, 15), LocalDate.of(2020, 03, 18));

        System.out.println("Nome esperado: " + expectedName);
        System.out.println("Nome retornado: " + findTransferencias.get(0).getNomeOperadorTransacao());

        Assertions.assertEquals(expectedName, findTransferencias.get(0).getNomeOperadorTransacao());
        Assertions.assertNotNull(findTransferencias);
        Assertions.assertNotNull(findTransferencias);
        Assertions.assertFalse(findTransferencias.isEmpty());

    }



}
