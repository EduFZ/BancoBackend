package br.com.banco.service;

import br.com.banco.domain.Transferencia;
import br.com.banco.dto.ConvertDto;
import br.com.banco.dto.TransferenciaDto;
import br.com.banco.exceptions.ExceptionMessage;
import br.com.banco.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<TransferenciaDto> findByFilters(String nomeOperador, String dataInicial, String dataFinal) throws ExceptionMessage {
        List<Transferencia> transferencias;

        LocalDate dataInicial1 = (dataInicial != null) ? LocalDate.parse(dataInicial) : null;
        LocalDate dataFinal1 = (dataFinal != null) ? LocalDate.parse(dataFinal) : null;

        transferencias = transferenciaRepository.findByFilters(nomeOperador, dataInicial1, dataFinal1);

        if (transferencias.isEmpty()) {
            throw new ExceptionMessage("Nenhum parâmetro encontrado");
        }

        ConvertDto converter = new ConvertDto();

        return transferencias.stream().map(converter::convertToDto).collect(Collectors.toList());
    }

}
