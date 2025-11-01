package com.projetoweb.trabalhoapi.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoweb.trabalhoapi.model.entity.AlunoEntity;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {

    // Requisito 6: Busca de um aluno por nome
    // O Spring Data JPA entende o nome do método e cria a query:
    // "SELECT * FROM aluno WHERE nome = ?"
    // Usamos List<> porque vários alunos podem ter o mesmo nome.
    List<AlunoEntity> findByNome(String nome);

}