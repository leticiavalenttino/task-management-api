package com.leticia.api_tarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leticia.api_tarefas.model.Prioridade;
import com.leticia.api_tarefas.model.Tarefa;
import com.leticia.api_tarefas.model.TarefaResumo;
import com.leticia.api_tarefas.service.TarefaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<Tarefa> listar() {
        return tarefaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
        return tarefaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        try {
            return ResponseEntity.ok(tarefaService.atualizar(id, tarefa));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Tarefa> concluir(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(tarefaService.marcarComoConcluida(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        tarefaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/prioridade/{prioridade}")
    public List<Tarefa> buscarPorPrioridade(@PathVariable Prioridade prioridade) {
    return tarefaService.buscarPorPrioridade(prioridade);
    }

    @GetMapping("/categoria/{categoria}")
    public List<Tarefa> buscarPorCategoria(@PathVariable String categoria) {
    return tarefaService.buscarPorCategoria(categoria);
    }
    @PostMapping
    public ResponseEntity<Tarefa> criar(@Valid @RequestBody Tarefa tarefa) {
    Tarefa novaTarefa = tarefaService.criar(tarefa);
    return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
    }
    @GetMapping("/resumo")
    public TarefaResumo resumo() {
    return tarefaService.gerarResumo();
    }
    @GetMapping("/buscar")
    public List<Tarefa> buscarPorTitulo(@RequestParam String titulo) {
    return tarefaService.buscarPorTitulo(titulo);
    }
}