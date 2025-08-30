package com.api.turmas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TurmaDto {
    private Long id;

    @NotBlank(message = "O nome da turma é obrigatório.")
    @Size(max = 100, message = "Nome pode ter no máximo 100 caracteres.")
    private String nome;

    @NotBlank(message = "O período é obrigatório. Ex.: matutino, vespertino, noturno.")
    private String periodo;

    @NotBlank(message = "O curso é obrigatório.")
    private String curso;

    public TurmaDto() {}

    public TurmaDto(Long id, String nome, String periodo, String curso) {
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
