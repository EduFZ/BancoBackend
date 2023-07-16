package br.com.banco.controller;

import br.com.banco.domain.Transferencia;
import br.com.banco.dto.TransferenciaDto;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.service.TransferenciaService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    @Autowired
    public TransferenciaController(TransferenciaService transferenciaService,
                                   TransferenciaRepository transferenciaRepository) {
        this.transferenciaService = transferenciaService;
        this.transferenciaRepository = transferenciaRepository;
    }

    @Autowired
    private TransferenciaDto transferenciaDto;
    private final TransferenciaRepository transferenciaRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Transferencia>> getTransferencias(){
        List<Transferencia> transferencias = transferenciaService.findTransferencia();
        return ResponseEntity.ok(transferencias);
    }

    @GetMapping("findByFilters/{nomeOperador}/dataInicial/{dataInicial}/dataFinal/{dataFinal}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista com as Transferências de acordo com os filtros repassados"),
            @ApiResponse(code = 404, message = "Registros não encontrados com esses parâmetros")
    })
    public ResponseEntity<List<TransferenciaDto>> findByFilters(@RequestParam(value = "nomeOperador", required = false) String nomeOperador,
                                                                @RequestParam(value = "dataInicial", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial,
                                                                @RequestParam(value = "dataFinal", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFinal,
                                                                @PageableDefault(size = 10) Pageable pageable){

        if (nomeOperador == null && dataInicial == null && dataFinal == null){
            List<Transferencia> transferencias = transferenciaService.findTransferencia();
        } else {
            List<Transferencia> transferencias;

            if (nomeOperador != null && dataInicial != null && dataFinal != null){
                transferencias = transferenciaRepository.findByOperadorTransacaoAndDataBetween(nomeOperador, dataInicial, dataFinal, pageable);
            } else if (nomeOperador != null) {
                transferencias = transferenciaRepository.findByNomeOperadorTransacao(nomeOperador, pageable);
            } else if (dataInicial != null) {
                transferencias = transferenciaRepository.findTransferenciaFromDataInicial(dataInicial, pageable);
            } else if (dataFinal != null) {
                transferencias = transferenciaRepository.findTransferenciaUntilDataFinal(dataFinal, pageable);
            }else{
                transferencias = transferenciaRepository.findByDataBetween(dataInicial, dataFinal, pageable);
            }
            List<TransferenciaDto> transferenciaDto = transferencias.stream().map(this::convertToDto).collect(Collectors.toList());

            return ResponseEntity.ok(transferenciaDto);
        }

        return ResponseEntity.ok((List<TransferenciaDto>) transferenciaService.findByFilters(nomeOperador, dataInicial, dataFinal, pageable));
    }

    public TransferenciaDto convertToDto(Transferencia transferencia){
        TransferenciaDto transferenciaDto = new TransferenciaDto();
        transferenciaDto.setId(transferencia.getId());
        transferenciaDto.setNomeOperador(transferencia.getNomeOperadorTransacao());
        transferenciaDto.setDataTransferencia(transferencia.getDataTransferencia());
        return transferenciaDto;
    }


}
