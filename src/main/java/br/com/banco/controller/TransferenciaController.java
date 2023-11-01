package br.com.banco.controller;

import br.com.banco.dto.TransferenciaDto;
import br.com.banco.exceptions.ExceptionMessage;
import br.com.banco.service.TransferenciaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    @Autowired
    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    private static final Logger logger = LoggerFactory.getLogger(TransferenciaController.class);

    @GetMapping("/filter")
    public ResponseEntity<List<TransferenciaDto>> findByFilters(@RequestParam(value = "nomeOperador", required = false) String nomeOperador,
                                                                @RequestParam(value = "dataInicial",  required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String dataInicial,
                                                                @RequestParam(value = "dataFinal",    required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String dataFinal) throws ExceptionMessage {

        logger.info("nomeOperador: {}", nomeOperador);
        logger.info("dataInicial: {}", dataInicial);
        logger.info("dataFinal: {}", dataFinal);

            return ResponseEntity.ok(transferenciaService.findByFilters(nomeOperador, dataInicial, dataFinal));

        }

}
