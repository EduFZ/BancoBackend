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
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
class TransferenciaServiceTest {

    @InjectMocks
    private TransferenciaService transferenciaService;
    @Mock
    private TransferenciaRepository transferenciaRepositoryMock;

    @BeforeEach
    void setUp() throws ExceptionMessage {
        List<Transferencia> listaTransf = Arrays.asList(TransferenciaCreator.createTransferencia());

        TransferenciaRepository transferenciaRepositoryMock = Mockito.mock(TransferenciaRepository.class);

        BDDMockito.when(transferenciaRepositoryMock.findByFilters(null, null, null)).thenReturn(listaTransf);

        BDDMockito.when(transferenciaRepositoryMock.findByFilters(any(), any(), any())).thenReturn(listaTransf);

        BDDMockito.when(transferenciaRepositoryMock.findByFilters("Ciclano", null, null)).thenReturn(listaTransf);

        BDDMockito.when(transferenciaRepositoryMock.findByFilters(null, LocalDate.of(2019, 05, 15), null)).thenReturn(listaTransf);

        BDDMockito.when(transferenciaRepositoryMock.findByFilters(null, null, LocalDate.of(2020, 03, 18))).thenReturn(listaTransf);

        BDDMockito.when(transferenciaRepositoryMock.findByFilters(null, LocalDate.of(2019, 05, 15), LocalDate.of(2020, 03, 18))).thenReturn(listaTransf);

        this.transferenciaRepositoryMock = transferenciaRepositoryMock;

    }

    @Test
    void testFindByFiltersWithNullParameters() throws ExceptionMessage {

        List<Transferencia> findTransferencias = transferenciaRepositoryMock.findByFilters(null, null, null);

        Assertions.assertNotNull(findTransferencias);
        Assertions.assertFalse(findTransferencias.isEmpty());

    }

    @Test
    void testFindByFiltersWithAllParameters() throws ExceptionMessage {
        String expectedName = TransferenciaCreator.createTransferencia().getNomeOperadorTransacao();

        List<Transferencia> findTransferencias = transferenciaRepositoryMock
                .findByFilters("Ciclano", LocalDate.of(2019, 05, 15),
                        LocalDate.of(2020, 03, 18));

        System.out.println("Nome esperado: " + expectedName);
        System.out.println("Nome retornado: " + findTransferencias.get(0).getNomeOperadorTransacao());

        Assertions.assertEquals(expectedName, findTransferencias.get(0).getNomeOperadorTransacao());
        Assertions.assertNotNull(findTransferencias);
        Assertions.assertFalse(findTransferencias.isEmpty());

    }

    @Test
    void testFindByFiltersWithNameParameter() throws ExceptionMessage {
        String expectedName = TransferenciaCreator.createTransferencia().getNomeOperadorTransacao();

        List<Transferencia> findTransferencias = transferenciaRepositoryMock
                .findByFilters("Ciclano", null, null);

        Transferencia nomeColetado = findTransferencias.get(0);

        System.out.println("Nome esperado: " + expectedName);
        System.out.println("Nome retornado: " + nomeColetado.getNomeOperadorTransacao());

        Assertions.assertEquals(expectedName, nomeColetado.getNomeOperadorTransacao());
        Assertions.assertNotNull(findTransferencias);
        Assertions.assertFalse(findTransferencias.isEmpty());

    }

    @Test
    void testFindByFiltersWithDataInicialParameter() throws ExceptionMessage {
        String expectedName = TransferenciaCreator.createTransferencia().getNomeOperadorTransacao();

        List<Transferencia> findTransferencias = transferenciaRepositoryMock
                .findByFilters(null, LocalDate.of(2019, 05, 15), null);

        System.out.println("Nome esperado: " + expectedName);
        System.out.println("Nome retornado: " + findTransferencias.get(0).getNomeOperadorTransacao());

        Assertions.assertEquals(expectedName, findTransferencias.get(0).getNomeOperadorTransacao());
        Assertions.assertNotNull(findTransferencias);
        Assertions.assertFalse(findTransferencias.isEmpty());

    }

    @Test
    void testFindByFiltersUntilDataFinalParameter() throws ExceptionMessage {
        String expectedName = TransferenciaCreator.createTransferencia().getNomeOperadorTransacao();

        List<Transferencia> findTransferencias = transferenciaRepositoryMock
                .findByFilters(null, null, LocalDate.of(2020, 03, 18));

        System.out.println("Nome esperado: " + expectedName);
        System.out.println("Nome retornado: " + findTransferencias.get(0).getNomeOperadorTransacao());

        Assertions.assertEquals(expectedName, findTransferencias.get(0).getNomeOperadorTransacao());
        Assertions.assertNotNull(findTransferencias);
        Assertions.assertFalse(findTransferencias.isEmpty());

    }

    @Test
    void testFindByFiltersWithAllDataParameters() throws ExceptionMessage {
        String expectedName = TransferenciaCreator.createTransferencia().getNomeOperadorTransacao();

        List<Transferencia> findTransferencias = transferenciaRepositoryMock
                .findByFilters(null, LocalDate.of(2019, 05, 15), LocalDate.of(2020, 03, 18));

        System.out.println("Nome esperado: " + expectedName);
        System.out.println("Nome retornado: " + findTransferencias.get(0).getNomeOperadorTransacao());

        Assertions.assertEquals(expectedName, findTransferencias.get(0).getNomeOperadorTransacao());
        Assertions.assertNotNull(findTransferencias);
        Assertions.assertFalse(findTransferencias.isEmpty());

    }

    @Test
    void testFindByFiltersWithNameParameterDoesNotExist() throws ExceptionMessage {

        BDDMockito.when(transferenciaRepositoryMock.findByFilters("Ciclano", null, null)).thenReturn(new ArrayList<>());

        Assertions.assertThrows(ExceptionMessage.class, () -> transferenciaService.findByFilters("Ciclano", null, null));

    }



}
