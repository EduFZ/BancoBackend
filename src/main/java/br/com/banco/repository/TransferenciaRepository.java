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

    @Query("SELECT f FROM Transferencia f WHERE (:nomeOperadorTransacao IS NULL OR f.nomeOperadorTransacao = :nomeOperadorTransacao) AND (:dataTransferencia IS NULL OR f.dataTransferencia >= :dataTransferencia) AND (:dataTransferencia IS NULL OR f.dataTransferencia <= :dataTransferencia)")
    List<Transferencia> findTransferencias(@Param("nomeOperadorTransacao") String nomeOperadorTransacao, @Param("dataTransferencia") LocalDate dataTransferencia);


}
