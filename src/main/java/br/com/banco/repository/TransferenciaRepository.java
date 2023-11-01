package br.com.banco.repository;

import br.com.banco.domain.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {

    List<Transferencia> findAll();

    @Query("SELECT t FROM Transferencia t WHERE" +
            "(:nomeOperadorTransacao IS NULL OR t.nomeOperadorTransacao = :nomeOperadorTransacao) AND" +
            "(:dataTransferenciaInicial IS NULL OR t.dataTransferencia >= :dataTransferenciaInicial) AND" +
            "(:dataTransferenciaFinal IS NULL OR t.dataTransferencia <= :dataTransferenciaFinal)")
    List<Transferencia> findByFilters(@Param("nomeOperadorTransacao") String nomeOperadorTransacao,
                                      @Param("dataTransferenciaInicial") LocalDate dataTransferenciaInicial,
                                      @Param("dataTransferenciaFinal") LocalDate dataTransferenciaFinal);

}
