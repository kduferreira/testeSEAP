package com.github.kduferreira.testeSEAP.infra;

import com.github.kduferreira.testeSEAP.Domain.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
