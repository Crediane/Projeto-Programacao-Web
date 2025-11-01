package com.projetoweb.trabalhoapi.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity; 
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "aluno")
public class AlunoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;


    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turma_id")
    @JsonBackReference
    private TurmaEntity turma;

    

    
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


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TurmaEntity getTurma() {
        return turma;
    }

    public void setTurma(TurmaEntity turma) {
        this.turma = turma;
    }
}