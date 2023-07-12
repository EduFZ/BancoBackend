package br.com.banco.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "transferencia")
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate data_transferencia;
    private Double valor;
    private String tipo;

    @NotNull
    @NotEmpty(message = "O nome do responsável da conta não pode estar vazio")
    @ManyToOne
    @JoinColumn(name = "nome_operador_transacao", referencedColumnName = "nome_responsavel")
    private Conta nomeOperadorTransacao;

    @OneToOne(mappedBy = "conta_id", cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_id", referencedColumnName = "id_conta")
    private Conta contaId;
}
