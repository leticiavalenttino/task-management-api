package com.leticia.api_tarefas.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.leticia.api_tarefas.model.Tarefa;
import com.leticia.api_tarefas.repository.TarefaRepository;

@Component
public class TarefaScheduler {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Scheduled(fixedRate = 21600000)
    @Transactional
    public void marcarTarefasAtrasadas() {
        LocalDateTime agora = LocalDateTime.now();

        List<Tarefa> tarefasAtrasadas =
            tarefaRepository.findByConcluidaFalseAndAtrasadaFalseAndDataLimiteBefore(agora);

        for (Tarefa tarefa : tarefasAtrasadas) {
            tarefa.setAtrasada(true);
        }
        tarefaRepository.saveAll(tarefasAtrasadas);

        System.out.println("Verificação de tarefas atrasadas executada em: " + agora
            + " | Tarefas marcadas: " + tarefasAtrasadas.size());
    }
}