package com.projetoweb.trabalhoapi.controller;


import java.util.List; // NOVO: Importar

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; // NOVO: Importar
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

    /**
     * Requisito 1: Cadastro de Turma (com alunos)
     * (Este método já existia e agora funciona para o Req 1)
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TurmaDto cadastrarTurma(@RequestBody TurmaDto turmaDto) {
        // Chama o service que JÁ modificamos
        return turmaService.cadastrarTurma(turmaDto);
    }

    /**
     * Requisito 2: Cadastro de um ou mais de um aluno em uma turma
     */
    @PostMapping("/{idTurma}") // Endpoint sugerido na descrição
    @ResponseStatus(code = HttpStatus.CREATED)
    public List<AlunoDto> adicionarAlunosNaTurma(
            @PathVariable Long idTurma, // MUDANÇA: de int para Long se aplicável
            @RequestBody List<AlunoDto> novosAlunos) {
        
        return turmaService.adicionarAlunosNaTurma(idTurma, novosAlunos);
    }

    /**
     * Requisito 3: Cadastro de um aluno em uma turma
     */
    @PostMapping("/{idTurma}/aluno") // Endpoint sugerido na descrição
    @ResponseStatus(code = HttpStatus.CREATED)
    public AlunoDto adicionarAlunoNaTurma(
            @PathVariable Long idTurma, 
            @RequestBody AlunoDto novoAluno) {
        
        return turmaService.adicionarAlunoNaTurma(idTurma, novoAluno);
    }

    /**
     * Requisito 4: Busca de todos os alunos da turma
     */
    @GetMapping("/{idTurma}/alunos") // Endpoint lógico para a busca
    @ResponseStatus(code = HttpStatus.OK)
    public List<AlunoDto> buscarAlunosDaTurma(@PathVariable Long idTurma) {
        return turmaService.buscarAlunosDaTurma(idTurma);
    }

    
    // --- MÉTODOS DE BUSCA DA TURMA (do professor) ---

    @GetMapping("/{turmaId}")
    @ResponseStatus(code = HttpStatus.OK)
    public TurmaDto buscarTurmaPorId(@PathVariable Long turmaId) { // MUDANÇA: int para Long
        return turmaService.findById(turmaId);
    }

    @GetMapping("/nome/{nome}")
    @ResponseStatus(code = HttpStatus.OK)
    public TurmaDto buscarTurmaPorNome(@PathVariable String nome) {
        return turmaService.findByNome(nome);
    }
}