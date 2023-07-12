package br.com.banco.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "conta")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToOne(cascade = CascadeType.ALL)
    private int id_conta;

    @NotNull
    @NotEmpty(message = "O nome do responsável da conta não pode estar vazio")
    @OneToOne(cascade = CascadeType.ALL)
    private String nome_responsavel;
}
