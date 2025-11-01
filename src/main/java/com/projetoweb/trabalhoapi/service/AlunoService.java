package com.projetoweb.trabalhoapi.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.projetoweb.trabalhoapi.controller.dto.AlunoDto;
import com.projetoweb.trabalhoapi.model.entity.AlunoEntity;
import com.projetoweb.trabalhoapi.model.repository.AlunoRepository;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    /**
     * Requisito 5: Busca de um aluno por Id
     */
    public AlunoDto findById(Long alunoId) {
        AlunoEntity aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno nao encontrado"));
        return paraAlunoDto(aluno);
    }

    /**
     * Requisito 6: Busca de um aluno por nome
     */
    public List<AlunoDto> findByNome(String nome) {
        List<AlunoEntity> alunos = alunoRepository.findByNome(nome);
        // Retorna a lista (mesmo que vazia), não um erro
        return alunos.stream()
                .map(this::paraAlunoDto)
                .collect(Collectors.toList());
    }

    // --- MÉTODO AJUDANTE (HELPER) ---
    private AlunoDto paraAlunoDto(AlunoEntity entidade) {
        AlunoDto dto = new AlunoDto();
        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        dto.setEmail(entidade.getEmail());
        return dto;
    }
}