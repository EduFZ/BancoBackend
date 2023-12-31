package br.com.banco.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "transferencia")
@Getter
@Setter
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "data_transferencia")
    private LocalDate dataTransferencia;
    private Double valor;
    private String tipo;

    @NotNull
    @NotEmpty(message = "O nome do responsável da conta não pode estar vazio")
    @Column(name = "nome_operador_transacao")
    private String nomeOperadorTransacao;

    @ManyToOne
    @JoinColumn(name = "conta_id", referencedColumnName = "id_conta")
    private Conta contaId;
}
