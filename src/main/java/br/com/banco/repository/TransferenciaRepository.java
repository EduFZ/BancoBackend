package br.com.banco.repository;

import br.com.banco.domain.Transferencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {

    List<Transferencia> findAll();

    @Query("SELECT t FROM Transferencia t WHERE t.nomeOperadorTransacao = :nomeOperadorTransacao AND t.dataTransferencia BETWEEN :dataInicial AND :dataFinal")
    List<Transferencia> findByOperadorTransacaoAndDataBetween(@Param("nomeOperadorTransacao") String nomeOperadorTransacao, @Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal, Pageable pageable);

    @Query("SELECT f FROM Transferencia f WHERE f.nomeOperadorTransacao = :nomeOperadorTransacao")
    List<Transferencia> findByNomeOperadorTransacao(@Param("nomeOperadorTransacao") String nomeOperadorTransacao, Pageable pageable);

    @Query("SELECT f FROM Transferencia f WHERE f.dataTransferencia >= :dataTransferenciaInicial")
    List<Transferencia> findTransferenciaFromDataInicial(@Param("dataTransferenciaInicial") LocalDate dataTransferenciaInicial, Pageable pageable);

    @Query("SELECT f FROM Transferencia f WHERE f.dataTransferencia <= :dataTransferenciaFinal")
    List<Transferencia> findTransferenciaUntilDataFinal(@Param("dataTransferenciaFinal") LocalDate dataTrasferenciaFinal, Pageable pageable);

    @Query("SELECT t FROM Transferencia t WHERE t.dataTransferencia BETWEEN :dataTransferenciaInicial AND :dataTransferenciaFinal")
    List<Transferencia> findByDataBetween(@Param("dataTransferenciaInicial") LocalDate dataTransferenciaInicial, @Param("dataTransferenciaFinal") LocalDate dataTransferenciaFinal, Pageable pageable);

//    @Query("SELECT f FROM Transferencia f WHERE " +
//            "(:nomeOperadorTransacao IS NULL OR f.nomeOperadorTransacao = :nomeOperadorTransacao)" +
//            " AND (:dataTransferenciaInicial IS NULL OR f.dataTransferencia >= :dataTransferenciaInicial)" +
//            " AND (:dataTransferenciaFinal IS NULL OR f.dataTransferencia <= :dataTransferenciaFinal)")
//    List<Transferencia> findTransferenciasComTodosOsFiltros(@Param("nomeOperadorTransacao") String nomeOperadorTransacao,
//                                                            @Param("dataTransferenciaInicial") LocalDate dataTransferenciaInicial,
//                                                            @Param("dataTransferenciaFinal") LocalDate dataTransferenciaFinal);


}
