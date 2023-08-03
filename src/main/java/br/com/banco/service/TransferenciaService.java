package br.com.banco.service;

import br.com.banco.domain.Transferencia;
import br.com.banco.dto.ConvertDto;
import br.com.banco.dto.TransferenciaDto;
import br.com.banco.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;

    @Autowired
    public TransferenciaService(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    public List<Transferencia> findTransferencia(){
        return transferenciaRepository.findAll();
    }

    ConvertDto converter = new ConvertDto();

    public List<TransferenciaDto> findByFilters(String nomeOperador, LocalDate dataInicial, LocalDate dataFinal) {
        List<Transferencia> transferencias = transferenciaRepository.findByOperadorTransacaoAndDataBetween(nomeOperador, dataInicial, dataFinal);
        return transferencias.stream().map(converter::convertToDto).collect(Collectors.toList());
    }

}
