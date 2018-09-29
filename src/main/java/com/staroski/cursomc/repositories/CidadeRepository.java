package com.staroski.cursomc.repositories;

import com.staroski.cursomc.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Cidade obj WHERE obj.estado.id = :estadoId ORDER BY obj.nome")
    List<Cidade> findCidades(@Param("estadoId") Integer estadoId);
}
  