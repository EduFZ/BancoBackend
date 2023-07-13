package br.com.banco.repository;

import br.com.banco.domain.Transferencia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransferenciaRepository extends PagingAndSortingRepository<Transferencia, Integer> {

    @Query("SELECT f FROM Transferencia f WHERE " +
            "(:nomeOperadorTransacao IS NULL OR f.nomeOperadorTransacao = :nomeOperadorTransacao)" +
            " AND (:dataTransferenciaInicial IS NULL OR f.dataTransferencia >= :dataTransferenciaInicial)" +
            " AND (:dataTransferenciaFinal IS NULL OR f.dataTransferencia <= :dataTransferenciaFinal)")
    List<Transferencia> findTransferencias(@Param("nomeOperadorTransacao") String nomeOperadorTransacao,
                                           @Param("dataTransferenciaInicial") LocalDate dataTransferenciaInicial,
                                           @Param("dataTransferenciaFinal") LocalDate dataTransferenciaFinal);


}
