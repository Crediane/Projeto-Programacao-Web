package com.api.turmas.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.turmas.domain.Turma;
import com.api.turmas.dto.TurmaDto;
import com.api.turmas.exception.NotFoundException;
import com.api.turmas.mapper.TurmaMapper;
import com.api.turmas.repository.TurmaRepository;

@Service
public class TurmaService {
    private final TurmaRepository repo;
    public TurmaService(TurmaRepository repo){ this.repo = repo; }

    @Transactional
    public TurmaDto criar(TurmaDto dto){
        Turma salvo = repo.save(TurmaMapper.toEntity(dto));
        return TurmaMapper.toDto(salvo);
    }

    @Transactional(readOnly=true)
    public List<TurmaDto> listarTodas(){
        var lista = repo.findAll();
        if(lista.isEmpty()) throw new NotFoundException("Não há turmas cadastradas.");
        return lista.stream().map(TurmaMapper::toDto).toList();
    }

    @Transactional(readOnly=true)
    public TurmaDto buscarPorId(Long id){
        var t = repo.findById(id).orElseThrow(
        () -> new NotFoundException("Turma com id " + id + " não encontrada.")
        );
        return TurmaMapper.toDto(t);
    }

    @Transactional(readOnly=true)
    public List<TurmaDto> buscarPorNome(String nome){
        var lista = repo.findByNomeIgnoreCase(nome);
        if(lista.isEmpty()) throw new NotFoundException("Nenhuma turma com nome '" + nome + "'.");
        return lista.stream().map(TurmaMapper::toDto).toList();
    }

    @Transactional(readOnly=true)
    public List<TurmaDto> buscarPorPeriodo(String periodo){
        var lista = repo.findByPeriodoIgnoreCase(periodo);
        if(lista.isEmpty()) throw new NotFoundException("Nenhuma turma no período '" + periodo + "'.");
        return lista.stream().map(TurmaMapper::toDto).toList();
    }

    @Transactional(readOnly=true)
    public List<TurmaDto> buscarPorCurso(String curso){
        var lista = repo.findByCursoIgnoreCase(curso);
        if(lista.isEmpty()) throw new NotFoundException("Nenhuma turma do curso '" + curso + "'.");
        return lista.stream().map(TurmaMapper::toDto).toList();
    }

    @Transactional
    public void deletarPorId(Long id){
        if(!repo.existsById(id)) throw new NotFoundException("Turma com id " + id + " não encontrada.");
        repo.deleteById(id);
    }
}
