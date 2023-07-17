package br.com.banco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class TransferenciaDto {
    private Integer id;
    private Double valor;
    private String tipo;
    private LocalDate dataTransferencia;
    private String nomeOperador;

}
