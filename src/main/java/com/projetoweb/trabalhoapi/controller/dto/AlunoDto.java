package com.projetoweb.trabalhoapi.controller.dto;


public class AlunoDto {

    private Long id; // MUDANÇA 1: int para Long
    private String nome;
    private String email;

    // --- Getters e Setters ---

    public Long getId() { // MUDANÇA 2
        return id;
    }

    public void setId(Long id) { // MUDANÇA 3
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
}