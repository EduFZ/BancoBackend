package br.com.banco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransferenciaDto {
    private Integer id;
    private LocalDate dataTransferencia;
    private String nomeOperador;

}
