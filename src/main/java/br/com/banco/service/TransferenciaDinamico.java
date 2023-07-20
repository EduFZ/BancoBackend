package br.com.banco.service;

import br.com.banco.domain.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.specification.TransferenciaSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class TransferenciaDinamico {

    private final TransferenciaRepository transferenciaRepository;

    public TransferenciaDinamico(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    public void inicial(String nomeOperadorTransacao) {

        List<Transferencia> transferencias = transferenciaRepository.findAll(Specification.where(TransferenciaSpecification.nomeOperadorTransacao(nomeOperadorTransacao)));

    }

}
