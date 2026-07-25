package com.leticia.api_tarefas.model;

public class TarefaResumo {

    private long total;
    private long concluidas;
    private long pendentes;
    private long atrasadas;

    public TarefaResumo(long total, long concluidas, long pendentes, long atrasadas) {
        this.total = total;
        this.concluidas = concluidas;
        this.pendentes = pendentes;
        this.atrasadas = atrasadas;
    }

    public long getTotal() { return total; }
    public void setTotal(long total) { this.total = total; }

    public long getConcluidas() { return concluidas; }
    public void setConcluidas(long concluidas) { this.concluidas = concluidas; }

    public long getPendentes() { return pendentes; }
    public void setPendentes(long pendentes) { this.pendentes = pendentes; }

    public long getAtrasadas() { return atrasadas; }
    public void setAtrasadas(long atrasadas) { this.atrasadas = atrasadas; }
}