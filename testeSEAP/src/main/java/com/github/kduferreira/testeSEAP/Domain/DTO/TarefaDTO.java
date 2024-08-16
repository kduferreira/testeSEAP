package com.github.kduferreira.testeSEAP.Domain.DTO;

import com.github.kduferreira.testeSEAP.Domain.Enum.Status;
import com.github.kduferreira.testeSEAP.Domain.Tarefa;

public class TarefaDTO {

    private String titulo;

    private String descricao;

    private Status status;



    public TarefaDTO(){

    }

    public TarefaDTO (Tarefa tarefa){
        this.titulo = tarefa.getTitulo();
        this.descricao = tarefa.getDescricao();
        this.status = tarefa.getStatus();
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public static TarefaDTO fromEntity(Tarefa tarefa){
        TarefaDTO dto = new TarefaDTO();
        dto.setTitulo(tarefa.getTitulo());
        dto.setDescricao(tarefa.getDescricao());
        dto.setStatus(tarefa.getStatus());
        return dto;
    }
}
