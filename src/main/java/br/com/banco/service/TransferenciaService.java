package br.com.banco.service;

import br.com.banco.domain.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;

    @Autowired
    public TransferenciaService(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    public List<Transferencia> findTransferencias(String nomeOperador, LocalDate dataTransferenciaInicial, LocalDate dataTransferenciaFinal) {
        return transferenciaRepository.findTransferencias(nomeOperador, dataTransferenciaInicial, dataTransferenciaFinal);
    }

}
