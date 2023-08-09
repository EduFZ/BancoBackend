package br.com.banco.dto;

import lombok.*;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
@Builder
public class TransferenciaDto {
    private Integer id;
    private Double valor;
    private String tipo;
    private LocalDate dataTransferencia;
    private String nomeOperador;

}
