package com.github.kduferreira.testeSEAP.Services;

import com.github.kduferreira.testeSEAP.Domain.DTO.TarefaDTO;
import com.github.kduferreira.testeSEAP.Domain.Enum.Status;
import com.github.kduferreira.testeSEAP.Domain.Tarefa;
import com.github.kduferreira.testeSEAP.infra.TarefaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }


    public Tarefa buscar (Long id){
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não Encontrado!!"));
    }


    public List<TarefaDTO> listar (){
        List<Tarefa> tarefas = tarefaRepository.findAll();
        return tarefas.stream()
                .map(TarefaDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public Tarefa save (Tarefa tarefa){

        tarefa.setStatus(Status.PEDENTE);
        return tarefaRepository.save(tarefa);
    }

    @Transactional
    public void deletar (Long id){
        tarefaRepository.deleteById(id);
    }


}
