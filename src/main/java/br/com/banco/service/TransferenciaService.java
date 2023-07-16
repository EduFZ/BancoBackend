package br.com.banco.service;

import br.com.banco.domain.Transferencia;
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

    public List<TransferenciaDto> findByFilters(String nomeOperador, LocalDate dataInicial, LocalDate dataFinal, Pageable pageable) {
        List<Transferencia> transferencias = transferenciaRepository.findByOperadorTransacaoAndDataBetween(nomeOperador, dataInicial, dataFinal, pageable);
        return transferencias.stream().map(this::convertToDto).collect(Collectors.toList());
    }



    public TransferenciaDto convertToDto(Transferencia transferencia){
        TransferenciaDto transferenciaDto = new TransferenciaDto();
        transferenciaDto.setId(transferencia.getId());
        transferenciaDto.setNomeOperador(transferencia.getNomeOperadorTransacao());
        transferenciaDto.setDataTransferencia(transferencia.getDataTransferencia());
        return transferenciaDto;
    }

//    public List<Transferencia> findByNomeOperadorTransacao(String nomeOperadorTransacao) {
//        return transferenciaRepository.findByNomeOperadorTransacao(nomeOperadorTransacao);
//    }
//
//    public List<Transferencia> findTransferenciaFromDataInicial(LocalDate dataTransferenciaInicial){
//        return transferenciaRepository.findTransferenciaFromDataInicial(dataTransferenciaInicial);
//    }
//
//    public List<Transferencia> findTransferenciaUntilDataFinal(LocalDate dataTransferenciaFinal){
//        return transferenciaRepository.findTransferenciaUntilDataFinal(dataTransferenciaFinal);
//    }
//
//    public List<Transferencia> findTransferenciasComTodosOsFiltros(String nomeOperador, LocalDate dataTransferenciaInicial, LocalDate dataTransferenciaFinal) {
//        return transferenciaRepository.findTransferenciasComTodosOsFiltros(nomeOperador, dataTransferenciaInicial, dataTransferenciaFinal);
//    }


}
