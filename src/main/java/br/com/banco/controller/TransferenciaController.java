package br.com.banco.controller;

import br.com.banco.domain.Transferencia;
import br.com.banco.dto.TransferenciaDto;
import br.com.banco.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    @Autowired
    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @Autowired
    private TransferenciaDto transferenciaDto;

    @GetMapping("/all")
    public ResponseEntity<List<Transferencia>> getTransferencias(){
        List<Transferencia> transferencias = transferenciaService.findTransferencia();
        return ResponseEntity.ok(transferencias);
    }

    @GetMapping("/operador/{nomeOperadorTransacao}")
    public ResponseEntity<List<Transferencia>> findByNomeOperadorTransacao (@PathVariable String nomeOperadorTransacao) {
        List<Transferencia> transferencias = transferenciaService.findByNomeOperadorTransacao(nomeOperadorTransacao);
        return ResponseEntity.ok(transferencias);
    }

    @GetMapping("/dataInicial/{dataTransferenciaInicial}")
    public ResponseEntity<List<Transferencia>> getTransferenciaFromDataInicial(@PathVariable String dataTransferenciaInicial) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parseDateInicial = LocalDate.parse(dataTransferenciaInicial, inputFormatter);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatteDateInicial = parseDateInicial.format(outputFormatter);

        LocalDate dateInicialFormatted = LocalDate.parse(formatteDateInicial, outputFormatter);

        List<Transferencia> transferencias = transferenciaService.findTransferenciaFromDataInicial(dateInicialFormatted);
        return ResponseEntity.ok(transferencias);
    }

    public ResponseEntity<List<Transferencia>> getTransferenciaUntilDataFinal(@PathVariable String dataTransferenciaFinal) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parseDataFinal = LocalDate.parse(dataTransferenciaFinal, inputFormatter);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatteDateFinal = parseDataFinal.format(outputFormatter);

        LocalDate dateFinalFormatted = LocalDate.parse(formatteDateFinal, outputFormatter);

        List<Transferencia> transferencias = transferenciaService.findTransferenciaUntilDataFinal(dateFinalFormatted);
        return ResponseEntity.ok(transferencias);
    }

    @GetMapping("/{nomeOperador}/{dataTransferenciaInicial}/{dataTransferenciaFinal}")
    public ResponseEntity<List<Transferencia>>getTransferenciasComTodosOsFiltros(@PathVariable String nomeOperador,
                                                                @PathVariable String dataTransferenciaInicial,
                                                                @PathVariable String dataTransferenciaFinal) {
        LocalDate parseDate = LocalDate.parse(dataTransferenciaInicial);
        LocalDate parseDateFinal = LocalDate.parse(dataTransferenciaFinal);
        List<Transferencia> transferencias = transferenciaService.findTransferenciasComTodosOsFiltros(nomeOperador, parseDate, parseDateFinal);
        return ResponseEntity.ok(transferencias);
    }







}
