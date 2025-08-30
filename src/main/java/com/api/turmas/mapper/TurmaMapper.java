package com.api.turmas.mapper;

import com.api.turmas.dto.TurmaDto;
import com.api.turmas.domain.Turma;


public class TurmaMapper {
    public static Turma toEntity(TurmaDto d) {
        if (d == null) return null;
        Turma t = new Turma();
        t.setId(d.getId());
        t.setNome(d.getNome());
        t.setPeriodo(d.getPeriodo());
        t.setCurso(d.getCurso());
        return t;
    }
    public static TurmaDto toDto(Turma e) {
        if (e == null) return null;
        return new TurmaDto(e.getId(), e.getNome(), e.getPeriodo(), e.getCurso());
    }
}
