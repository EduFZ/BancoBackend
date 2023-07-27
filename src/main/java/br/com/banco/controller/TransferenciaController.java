package br.com.banco.controller;

import br.com.banco.domain.Transferencia;
import br.com.banco.dto.TransferenciaDto;
import br.com.banco.exceptions.ExceptionMessage;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.service.TransferenciaService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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

    private static final Logger logger = LoggerFactory.getLogger(TransferenciaController.class);

    @GetMapping("/filter")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista com as Transferências de acordo com os filtros repassados"),
            @ApiResponse(code = 404, message = "Registros não encontrados com esses parâmetros")
    })
    public ResponseEntity<List<TransferenciaDto>> findByFilters(@RequestParam(value = "nomeOperador", required = false) String nomeOperador,
                                                                @RequestParam(value = "dataInicial",  required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String dataInicial,
                                                                @RequestParam(value = "dataFinal",    required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String dataFinal) {

        logger.info("nomeOperador: {}", nomeOperador);
        logger.info("dataInicial: {}", dataInicial);
        logger.info("dataFinal", dataFinal);

        List<Transferencia> transferencias;

        if (nomeOperador == null && dataInicial == null && dataFinal == null){
            transferencias = transferenciaService.findTransferencia();
        } else if (nomeOperador != null && dataInicial != null && dataFinal != null){

                LocalDate dataInicial1 = LocalDate.parse(dataInicial);
                LocalDate dataFinal1   = LocalDate.parse(dataFinal);

                transferencias = transferenciaRepository.findByOperadorTransacaoAndDataBetween(nomeOperador, dataInicial1 , dataFinal1);


            } else if (nomeOperador != null) {
                transferencias = transferenciaRepository.findByNomeOperadorTransacao(nomeOperador);
            } else if (dataInicial != null) {
                LocalDate dataInicial1 = LocalDate.parse(dataInicial);
                transferencias = transferenciaRepository.findTransferenciaFromDataInicial(dataInicial1);
            } else if (dataFinal != null) {
                LocalDate dataFinal1 = LocalDate.parse(dataFinal);
                transferencias = transferenciaRepository.findTransferenciaUntilDataFinal(dataFinal1);
            }else{
                LocalDate dataInicial1 = LocalDate.parse(dataInicial);
                LocalDate dataFinal1 = LocalDate.parse(dataFinal);
                transferencias = transferenciaRepository.findByDataBetween(dataInicial1, dataFinal1);
            }

            if (transferencias.isEmpty()) {
                //return ResponseEntity.notFound().build(); // Responde com status 404 se a lista estiver vazia
                throw new ExceptionMessage("Nenhum parâmetro encontrado");
            }

            List<TransferenciaDto> transferenciaDto = transferencias.stream().map(this::convertToDto).collect(Collectors.toList());



            return ResponseEntity.ok(transferenciaDto);
        }


        //return ResponseEntity.ok((List<TransferenciaDto>) transferenciaService.findByFilters(nomeOperador, dataInicial1, dataFinal1));


    public TransferenciaDto convertToDto(Transferencia transferencia){
        TransferenciaDto transferenciaDto = new TransferenciaDto();
        transferenciaDto.setId(transferencia.getId());
        transferenciaDto.setNomeOperador(transferencia.getNomeOperadorTransacao());
        transferenciaDto.setDataTransferencia(transferencia.getDataTransferencia());
        return transferenciaDto;
    }

}
