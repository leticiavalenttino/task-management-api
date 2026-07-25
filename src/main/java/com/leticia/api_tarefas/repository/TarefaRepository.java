package com.leticia.api_tarefas.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leticia.api_tarefas.model.Prioridade;
import com.leticia.api_tarefas.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByConcluidaFalseAndAtrasadaFalseAndDataLimiteBefore(LocalDateTime agora);

    List<Tarefa> findByPrioridade(Prioridade prioridade);

    List<Tarefa> findByCategoriaIgnoreCase(String categoria);

    List<Tarefa> findByTituloContainingIgnoreCase(String titulo);

    long countByConcluidaTrue();

    long countByConcluidaFalse();

    long countByAtrasadaTrue();
}