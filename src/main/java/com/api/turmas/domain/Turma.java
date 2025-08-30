package com.api.turmas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "turmas")
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=100)
    private String nome;

    @Column(nullable=false, length=30)
    private String periodo;

    @Column(nullable=false, length=100)
    private String curso;

    public Turma() {}

    public Turma(Long id, String nome, String periodo, String curso) {
        this.id = id;
        this.nome = nome;
        this.periodo = periodo;
        this.curso = curso;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
}
