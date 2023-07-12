package br.com.banco.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "conta")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id_conta")
    private Integer idConta;

    @NotNull
    @NotEmpty(message = "O nome do responsável da conta não pode estar vazio")
    @JoinColumn(name = "nome_responsavel")
    private String nomeResponsavel;
}
