package br.com.banco.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransferenciaDto {
    private Integer id;
    private LocalDate dataTransferencia;
    private String nomeOperadorTransacao;
    private BigDecimal valor;
}
