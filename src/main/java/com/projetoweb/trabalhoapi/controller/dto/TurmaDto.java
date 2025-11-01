package com.projetoweb.trabalhoapi.controller.dto;



import java.util.ArrayList;
import java.util.List;

public class TurmaDto {

    private Long id; 

    private String nome;
    private String curso;
    private String periodo;


    private List<AlunoDto> alunos = new ArrayList<>();




    public Long getId() { 
        return id;
    }

    public void setId(Long id) { 
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


    public List<AlunoDto> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoDto> alunos) {
        this.alunos = alunos;
    }
}