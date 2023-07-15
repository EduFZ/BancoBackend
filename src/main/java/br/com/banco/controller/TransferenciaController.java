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
    public ResponseEntity<Page<TransferenciaDto>> findByFilters(@RequestParam(value = "nomeOperador", required = false) String nomeOperador,
                                                                @RequestParam(value = "dataInicial", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial,
                                                                @RequestParam(value = "dataFinal", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFinal,
                                                                @PageableDefault(size = 10) Pageable pageable){

        if (nomeOperador == null && dataInicial == null && dataFinal == null){
            List<Transferencia> transferencias = transferenciaService.findTransferencia();
        } else {
            Page<Transferencia> transferencias;

            if (nomeOperador != null && dataInicial != null && dataFinal != null){
                transferencias = transferenciaRepository.findByOperadorTransacaoAndDataBetween(nomeOperador, dataInicial, dataFinal, pageable);
            } else if (nomeOperador != null) {
                transferencias = transferenciaRepository.findByNomeOperadorTransacao(nomeOperador);
            } else if (dataInicial != null) {
                transferencias = transferenciaRepository.findTransferenciaFromDataInicial(dataInicial);
            } else if (dataFinal != null) {

            }

        }

        return ResponseEntity.ok(transferenciaService.findByFilters(nomeOperador, dataInicial, dataFinal, pageable));
    }



//    @GetMapping("/operador/{nomeOperadorTransacao}")
//    public ResponseEntity<List<Transferencia>> findByNomeOperadorTransacao (@PathVariable String nomeOperadorTransacao) {
//        List<Transferencia> transferencias = transferenciaService.findByNomeOperadorTransacao(nomeOperadorTransacao);
//        return ResponseEntity.ok(transferencias);
//    }
//
//    @GetMapping("/dataInicial/{dataTransferenciaInicial}")
//    public ResponseEntity<List<Transferencia>> getTransferenciaFromDataInicial(@PathVariable String dataTransferenciaInicial) {
//        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        LocalDate parseDateInicial = LocalDate.parse(dataTransferenciaInicial, inputFormatter);
//
//        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String formatteDateInicial = parseDateInicial.format(outputFormatter);
//
//        LocalDate dateInicialFormatted = LocalDate.parse(formatteDateInicial, outputFormatter);
//
//        List<Transferencia> transferencias = transferenciaService.findTransferenciaFromDataInicial(dateInicialFormatted);
//        return ResponseEntity.ok(transferencias);
//    }
//
//    public ResponseEntity<List<Transferencia>> getTransferenciaUntilDataFinal(@PathVariable String dataTransferenciaFinal) {
//        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        LocalDate parseDataFinal = LocalDate.parse(dataTransferenciaFinal, inputFormatter);
//
//        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String formatteDateFinal = parseDataFinal.format(outputFormatter);
//
//        LocalDate dateFinalFormatted = LocalDate.parse(formatteDateFinal, outputFormatter);
//
//        List<Transferencia> transferencias = transferenciaService.findTransferenciaUntilDataFinal(dateFinalFormatted);
//        return ResponseEntity.ok(transferencias);
//    }
//
//    @GetMapping("/{nomeOperador}/{dataTransferenciaInicial}/{dataTransferenciaFinal}")
//    public ResponseEntity<List<Transferencia>>getTransferenciasComTodosOsFiltros(@PathVariable String nomeOperador,
//                                                                @PathVariable String dataTransferenciaInicial,
//                                                                @PathVariable String dataTransferenciaFinal) {
//        LocalDate parseDate = LocalDate.parse(dataTransferenciaInicial);
//        LocalDate parseDateFinal = LocalDate.parse(dataTransferenciaFinal);
//        List<Transferencia> transferencias = transferenciaService.findTransferenciasComTodosOsFiltros(nomeOperador, parseDate, parseDateFinal);
//        return ResponseEntity.ok(transferencias);
//    }







}
