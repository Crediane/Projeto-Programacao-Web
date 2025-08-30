package com.api.turmas.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.turmas.dto.TurmaDto;
import com.api.turmas.exception.BadRequestException;
import com.api.turmas.service.TurmaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/turmas")
@Validated
public class TurmaController {
    private final TurmaService service;
    public TurmaController(TurmaService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<TurmaDto> criar(@Valid @RequestBody TurmaDto dto){
        if(dto.getNome()==null || dto.getNome().isBlank())
            throw new BadRequestException("O campo 'nome' é obrigatório.");
        var salvo = service.criar(dto);
        URI location = URI.create("/turmas/" + salvo.getId());
        return ResponseEntity.created(location).body(salvo);
    }

    @GetMapping("/{turmaId}")
    public ResponseEntity<TurmaDto> buscarPorId(@PathVariable Long turmaId){
        return ResponseEntity.ok(service.buscarPorId(turmaId));
    }

    @GetMapping
    public ResponseEntity<List<TurmaDto>> listarTodas(){
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<TurmaDto>> buscarPorNome(@PathVariable String nome){
        return ResponseEntity.ok(service.buscarPorNome(nome));
    }

    @GetMapping("/periodo/{periodo}")
    public ResponseEntity<List<TurmaDto>> buscarPorPeriodo(@PathVariable String periodo){
        return ResponseEntity.ok(service.buscarPorPeriodo(periodo));
    }

    @GetMapping("/curso/{curso}")
    public ResponseEntity<List<TurmaDto>> buscarPorCurso(@PathVariable String curso){
        return ResponseEntity.ok(service.buscarPorCurso(curso));
    }

    @DeleteMapping("/{turmaId}")
    public ResponseEntity<Void> deletar(@PathVariable Long turmaId){
        service.deletarPorId(turmaId);
        return ResponseEntity.noContent().build();
    }
}
