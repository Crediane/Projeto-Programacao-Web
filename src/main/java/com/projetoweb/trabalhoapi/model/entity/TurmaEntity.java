package com.projetoweb.trabalhoapi.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference; // 1. Importar
import jakarta.persistence.*;
import java.util.ArrayList; // 2. Importar
import java.util.List;

@Entity
@Table(name = "turma")
public class TurmaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MUDANÇA 1
    private Long id; // MUDANÇA 2

    @Column(name = "nome_da_turma")
    private String nome;

    @Column(name = "curso")
    private String curso;

    @Column(name = "perido_da_turma") // Corrigindo: 'periodo_da_turma'
    private String periodo;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // 3. Adicionar para evitar loop no JSON
    private List<AlunoEntity> alunos = new ArrayList<>(); // MUDANÇA 3

    
    // --- Getters e Setters ---
    // (Lembre-se de atualizar o get/set do ID para Long)

    public Long getId() { // MUDANÇA 4
        return id;
    }

    public void setId(Long id) { // MUDANÇA 5
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