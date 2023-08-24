package br.com.banco.repository;

import br.com.banco.domain.Transferencia;
import br.com.banco.util.TransferenciaCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class TransferenciaRepositoryTest {

    @Autowired
    TransferenciaRepository transferenciaRepository;

    @Test
    void findAll(){
        Transferencia transferencia = TransferenciaCreator.createTransferencia();

        String name = transferencia.getNomeOperadorTransacao();
        Integer id = transferencia.getId();

        List<Transferencia> transferenciaAll = this.transferenciaRepository.findAll();

        String nomeOperadorTransacao = transferenciaAll.get(0).getNomeOperadorTransacao();
        Integer idTransac = transferenciaAll.get(0).getId();

        Assertions.assertNotNull(transferenciaAll);
        Assertions.assertEquals(name, nomeOperadorTransacao);
        Assertions.assertEquals(id, idTransac);
        //Criar método save no Repository?
    }

}
