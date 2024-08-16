package com.github.kduferreira.testeSEAP.Application;

import com.github.kduferreira.testeSEAP.Domain.DTO.TarefaDTO;
import com.github.kduferreira.testeSEAP.Domain.Enum.Status;
import com.github.kduferreira.testeSEAP.Domain.Tarefa;
import com.github.kduferreira.testeSEAP.Services.TarefaService;
import com.github.kduferreira.testeSEAP.infra.TarefaRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tarefas")
public class TarefaController {


    private final TarefaRepository tarefaRepository;

    private final TarefaService tarefaService;


    public TarefaController(TarefaRepository tarefaRepository, TarefaService tarefaService) {
        this.tarefaRepository = tarefaRepository;
        this.tarefaService = tarefaService;
    }


    @GetMapping("status")
    public String status(){
        return "ok";
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTodos() {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        return ResponseEntity.ok(tarefas);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tarefa created(@RequestBody Tarefa tarefa) {
        return tarefaService.save(tarefa);
    }


    @GetMapping("{id}")
    public ResponseEntity<TarefaDTO> listarId(@Valid @PathVariable Long id) {
        Optional<Tarefa> optional = tarefaRepository.findById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(new TarefaDTO(optional.get()));
        }

        return ResponseEntity.notFound().build();
    }


    @PutMapping("{id}/concluir")
    public ResponseEntity<Tarefa> atualizar(@Valid @PathVariable Long id, @Valid @RequestBody Tarefa tarefa) {
        if (!tarefaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        tarefa.setId(id);
        tarefa.setStatus(Status.CONCLUIDO);
        tarefa = tarefaRepository.save(tarefa);
        return ResponseEntity.ok(tarefa);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluir (@Valid @PathVariable Long id){
        if (!tarefaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

