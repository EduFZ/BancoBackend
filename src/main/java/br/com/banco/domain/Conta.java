package br.com.banco.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "conta")
@Getter
@Setter
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private Integer idConta;

    @NotNull
    @NotEmpty(message = "O nome do responsável da conta não pode estar vazio")
    @Column(name = "nome_responsavel")
    private String nomeResponsavel;
}
