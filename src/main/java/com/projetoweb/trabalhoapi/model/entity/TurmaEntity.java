package com.projetoweb.trabalhoapi.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference; 

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "turma")
public class TurmaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; 

    @Column(name = "nome_da_turma")
    private String nome;

    @Column(name = "curso")
    private String curso;

    @Column(name = "perido_da_turma") 
    private String periodo;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference 
    private List<AlunoEntity> alunos = new ArrayList<>(); 

    

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

    public List<AlunoEntity> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoEntity> alunos) {
        this.alunos = alunos;
    }
}