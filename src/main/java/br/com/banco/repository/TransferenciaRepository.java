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

    List<Transferencia> findAll();

    @Query("SELECT f FROM Transferencia f WHERE f.nomeOperadorTransacao = :nomeOperadorTransacao")
    List<Transferencia> findByNomeOperadorTransacao(@Param("nomeOperadorTransacao") String nomeOperadorTransacao);

    @Query("SELECT f FROM Transferencia f WHERE f.dataTransferencia >= :dataTransferenciaInicial")
    List<Transferencia> findTransferenciaFromDataInicial(@Param("dataTransferenciaInicial") LocalDate dataTransferenciaInicial);

    @Query("SELECT f FROM Transferencia f WHERE f.dataTransferencia <= :dataTransferenciaFinal")
    List<Transferencia> findTransferenciaUntilDataFinal(@Param("dataTransferenciaFinal") LocalDate dataTrasferenciaFinal);

    @Query("SELECT f FROM Transferencia f WHERE " +
            "(:nomeOperadorTransacao IS NULL OR f.nomeOperadorTransacao = :nomeOperadorTransacao)" +
            " AND (:dataTransferenciaInicial IS NULL OR f.dataTransferencia >= :dataTransferenciaInicial)" +
            " AND (:dataTransferenciaFinal IS NULL OR f.dataTransferencia <= :dataTransferenciaFinal)")
    List<Transferencia> findTransferenciasComTodosOsFiltros(@Param("nomeOperadorTransacao") String nomeOperadorTransacao,
                                                            @Param("dataTransferenciaInicial") LocalDate dataTransferenciaInicial,
                                                            @Param("dataTransferenciaFinal") LocalDate dataTransferenciaFinal);


}
