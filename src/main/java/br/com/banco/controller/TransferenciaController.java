package br.com.banco.controller;

import br.com.banco.domain.Transferencia;
import br.com.banco.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    @Autowired
    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    public ResponseEntity<List<Transferencia>>getTransferencias(@PathVariable String nomeOperador, @PathVariable String dataTransferencia) {
        LocalDate parseDate = LocalDate.parse(dataTransferencia);
        List<Transferencia> transferencias = transferenciaService.findTransferencias(nomeOperador, parseDate);
        return ResponseEntity.ok(transferencias);
    }

}
