package com.projetoweb.trabalhoapi.controller.dto;


// 1. Importar List e ArrayList
import java.util.ArrayList;
import java.util.List;

public class TurmaDto {

    private Long id; // MUDANÇA 1: int para Long

    private String nome;
    private String curso;
    private String periodo;

    // MUDANÇA 2: Adicionar a lista de AlunoDto
    // É isso que vai receber o JSON do Requisito 1
    private List<AlunoDto> alunos = new ArrayList<>();


    // --- Getters e Setters ---
    // (Atualizar o get/set do ID)

    public Long getId() { // MUDANÇA 3
        return id;
    }

    public void setId(Long id) { // MUDANÇA 4
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    // MUDANÇA 5: Adicionar getters e setters para a lista de alunos
    public List<AlunoDto> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoDto> alunos) {
        this.alunos = alunos;
    }
}