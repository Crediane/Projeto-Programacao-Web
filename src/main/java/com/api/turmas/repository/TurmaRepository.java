package com.api.turmas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.turmas.domain.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    List<Turma> findByNomeIgnoreCase(String nome);
    List<Turma> findByPeriodoIgnoreCase(String periodo);
    List<Turma> findByCursoIgnoreCase(String curso);
}
