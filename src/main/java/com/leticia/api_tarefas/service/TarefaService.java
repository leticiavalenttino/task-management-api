package com.leticia.api_tarefas.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leticia.api_tarefas.model.Prioridade;
import com.leticia.api_tarefas.model.Tarefa;
import com.leticia.api_tarefas.model.TarefaResumo;
import com.leticia.api_tarefas.repository.TarefaRepository;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> buscarPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    public Tarefa criar(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizar(Long id, Tarefa tarefaAtualizada) {
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefa.setTitulo(tarefaAtualizada.getTitulo());
            tarefa.setDescricao(tarefaAtualizada.getDescricao());
            tarefa.setConcluida(tarefaAtualizada.isConcluida());
            tarefa.setPrioridade(tarefaAtualizada.getPrioridade());
            tarefa.setCategoria(tarefaAtualizada.getCategoria());
            tarefa.setDataLimite(tarefaAtualizada.getDataLimite());

            if (tarefaAtualizada.isConcluida()
                || tarefaAtualizada.getDataLimite() == null
                || tarefaAtualizada.getDataLimite().isAfter(LocalDateTime.now())) {
                tarefa.setAtrasada(false);
            }

            return tarefaRepository.save(tarefa);
        }).orElseThrow(() -> new RuntimeException("Tarefa não encontrada com id: " + id));
    }

    public Tarefa marcarComoConcluida(Long id) {
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefa.setConcluida(true);
            tarefa.setAtrasada(false);
            return tarefaRepository.save(tarefa);
        }).orElseThrow(() -> new RuntimeException("Tarefa não encontrada com id: " + id));
    }

    public void excluir(Long id) {
        tarefaRepository.deleteById(id);
    }

    public List<Tarefa> buscarPorPrioridade(Prioridade prioridade) {
        return tarefaRepository.findByPrioridade(prioridade);
    }

    public List<Tarefa> buscarPorCategoria(String categoria) {
        return tarefaRepository.findByCategoriaIgnoreCase(categoria);
    }

    public TarefaResumo gerarResumo() {
        long total = tarefaRepository.count();
        long concluidas = tarefaRepository.countByConcluidaTrue();
        long pendentes = tarefaRepository.countByConcluidaFalse();
        long atrasadas = tarefaRepository.countByAtrasadaTrue();

        return new TarefaResumo(total, concluidas, pendentes, atrasadas);
    }

    public List<Tarefa> buscarPorTitulo(String titulo) {
        return tarefaRepository.findByTituloContainingIgnoreCase(titulo);
    }
}