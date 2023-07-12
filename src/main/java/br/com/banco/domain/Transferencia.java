package br.com.banco.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "transferencia")
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date data_transferencia;
    private Double valor;
    private String tipo;
    @NotNull
    @NotEmpty(message = "O nome do responsável da conta não pode estar vazio")
    @JoinColumn(referencedColumnName = "nome_responsavel")
    @OneToOne(mappedBy = "nome_responsavel")
    private Conta nome_operador_transacao;
    @OneToOne(mappedBy = "id_conta", cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id_conta")
    private Conta conta_id;
}
