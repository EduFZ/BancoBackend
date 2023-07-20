package br.com.banco.specification;

import br.com.banco.domain.Transferencia;
import org.springframework.data.jpa.domain.Specification;

public class TransferenciaSpecification {
    public static Specification<Transferencia> nomeOperadorTransacao(String nomeOperadorTransacao) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("nomeOperadorTransacao"), "%" + nomeOperadorTransacao + "%"));
    }
}
