package com.projetoweb.trabalhoapi.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projetoweb.trabalhoapi.controller.dto.AlunoDto;
import com.projetoweb.trabalhoapi.service.AlunoService;

@RestController
@RequestMapping("/alunos") // Todos endpoints de aluno começam com /alunos
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    /**
     * Requisito 5: Busca de um aluno por Id
     */
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public AlunoDto buscarAlunoPorId(@PathVariable Long id) {
        return alunoService.findById(id);
    }

    /**
     * Requisito 6: Busca de um aluno por nome
     * (Seguindo o padrão do professor de usar @PathVariable)
     */
    @GetMapping("/nome/{nome}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<AlunoDto> buscarAlunoPorNome(@PathVariable String nome) {
        // Chama o service que criamos no Passo 8
        return alunoService.findByNome(nome);
    }
}
