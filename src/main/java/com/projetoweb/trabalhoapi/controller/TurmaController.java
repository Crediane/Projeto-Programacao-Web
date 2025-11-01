package com.projetoweb.trabalhoapi.controller;


import java.util.List; 

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projetoweb.trabalhoapi.controller.dto.AlunoDto;
import com.projetoweb.trabalhoapi.controller.dto.TurmaDto;
import com.projetoweb.trabalhoapi.service.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TurmaDto cadastrarTurma(@RequestBody TurmaDto turmaDto) {

        return turmaService.cadastrarTurma(turmaDto);
    }


    @PostMapping("/{idTurma}") 
    @ResponseStatus(code = HttpStatus.CREATED)
    public List<AlunoDto> adicionarAlunosNaTurma(
            @PathVariable Long idTurma, 
            @RequestBody List<AlunoDto> novosAlunos) {
        
        return turmaService.adicionarAlunosNaTurma(idTurma, novosAlunos);
    }


    @PostMapping("/{idTurma}/aluno") 
    @ResponseStatus(code = HttpStatus.CREATED)
    public AlunoDto adicionarAlunoNaTurma(
            @PathVariable Long idTurma, 
            @RequestBody AlunoDto novoAluno) {
        
        return turmaService.adicionarAlunoNaTurma(idTurma, novoAluno);
    }


    @GetMapping("/{idTurma}/alunos") 
    @ResponseStatus(code = HttpStatus.OK)
    public List<AlunoDto> buscarAlunosDaTurma(@PathVariable Long idTurma) {
        return turmaService.buscarAlunosDaTurma(idTurma);
    }

    

    @GetMapping("/{turmaId}")
    @ResponseStatus(code = HttpStatus.OK)
    public TurmaDto buscarTurmaPorId(@PathVariable Long turmaId) { 
        return turmaService.findById(turmaId);
    }

    @GetMapping("/nome/{nome}")
    @ResponseStatus(code = HttpStatus.OK)
    public TurmaDto buscarTurmaPorNome(@PathVariable String nome) {
        return turmaService.findByNome(nome);
    }
}