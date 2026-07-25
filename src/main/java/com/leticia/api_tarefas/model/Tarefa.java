package com.leticia.api_tarefas.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
    @Column(nullable = false)
    private String titulo;

    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String descricao;

    private boolean concluida = false;

    private LocalDateTime dataLimite;

    private boolean atrasada = false;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade = Prioridade.MEDIA;

    private String categoria;

    // Construtores
    public Tarefa() {}

    public Tarefa(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    // Getters e Setters (iguais aos que já existem, sem mudança)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public boolean isConcluida() { return concluida; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; }

    public LocalDateTime getDataLimite() { return dataLimite; }
    public void setDataLimite(LocalDateTime dataLimite) { this.dataLimite = dataLimite; }

    public boolean isAtrasada() { return atrasada; }
    public void setAtrasada(boolean atrasada) { this.atrasada = atrasada; }

    public Prioridade getPrioridade() { return prioridade; }
    public void setPrioridade(Prioridade prioridade) { this.prioridade = prioridade; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}