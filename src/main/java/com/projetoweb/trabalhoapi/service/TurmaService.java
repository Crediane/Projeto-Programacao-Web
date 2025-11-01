package com.projetoweb.trabalhoapi.service;


import java.util.List; // NOVO: Importar
import java.util.stream.Collectors;

import org.springframework.stereotype.Service; // NOVO: Importar

import com.projetoweb.trabalhoapi.controller.dto.AlunoDto;
import com.projetoweb.trabalhoapi.controller.dto.TurmaDto; // NOVO: Importar
import com.projetoweb.trabalhoapi.model.entity.AlunoEntity;
import com.projetoweb.trabalhoapi.model.entity.TurmaEntity;
import com.projetoweb.trabalhoapi.model.repository.AlunoRepository; // NOVO: Importar
import com.projetoweb.trabalhoapi.model.repository.TurmaRepository;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final AlunoRepository alunoRepository; // NOVO (Req #2, #3)

    // MUDANÇA: Adicionar AlunoRepository no construtor
    public TurmaService(TurmaRepository turmaRepository, AlunoRepository alunoRepository) {
        this.turmaRepository = turmaRepository;
        this.alunoRepository = alunoRepository; // NOVO
    }

    /**
     * Requisito 1: Cadastro de Turma (com alunos)
     */
    public TurmaDto cadastrarTurma(TurmaDto turmaDto) {
        final TurmaEntity turmaEntity = new TurmaEntity();
        turmaEntity.setNome(turmaDto.getNome());
        turmaEntity.setCurso(turmaDto.getCurso());
        turmaEntity.setPeriodo(turmaDto.getPeriodo());

        // --- MUDANÇA PRINCIPAL (Req #1) ---
        // Se o DTO tiver uma lista de alunos, processa ela
        if (turmaDto.getAlunos() != null && !turmaDto.getAlunos().isEmpty()) {
            for (AlunoDto alunoDto : turmaDto.getAlunos()) {
                AlunoEntity alunoEntity = new AlunoEntity();
                alunoEntity.setNome(alunoDto.getNome());
                alunoEntity.setEmail(alunoDto.getEmail());
                
                // Esta é a linha mais importante:
                // Ela "amarra" o aluno à turma que estamos criando.
                alunoEntity.setTurma(turmaEntity); 
                
                // Adiciona o aluno na lista da turma
                turmaEntity.getAlunos().add(alunoEntity);
            }
        }
        // --- Fim da Mudança ---

        // Graças ao CascadeType.ALL, isso salva a Turma E os Alunos
        final TurmaEntity turmaCadastrada = turmaRepository.save(turmaEntity);

        // Converte a entidade salva (com alunos) de volta para DTO
        return paraTurmaDto(turmaCadastrada);
    }

    /**
     * Requisito 2: Cadastro de um ou mais de um aluno em uma turma
     */
    public List<AlunoDto> adicionarAlunosNaTurma(Long idTurma, List<AlunoDto> novosAlunosDto) {
        // 1. Busca a turma ou lança erro
        TurmaEntity turma = buscarTurmaPorIdOuLancarErro(idTurma);

        // 2. Converte a lista de DTOs em lista de Entidades
        List<AlunoEntity> novosAlunos = novosAlunosDto.stream().map(dto -> {
            AlunoEntity aluno = new AlunoEntity();
            aluno.setNome(dto.getNome());
            aluno.setEmail(dto.getEmail());
            aluno.setTurma(turma); // Amarra os novos alunos à turma existente
            return aluno;
        }).collect(Collectors.toList());

        // 3. Salva todos os novos alunos no banco
        List<AlunoEntity> alunosSalvos = alunoRepository.saveAll(novosAlunos);

        // 4. Converte as entidades salvas em DTOs e retorna
        return alunosSalvos.stream()
                .map(this::paraAlunoDto) // Usando helper
                .collect(Collectors.toList());
    }

    /**
     * Requisito 3: Cadastro de um aluno em uma turma
     */
    public AlunoDto adicionarAlunoNaTurma(Long idTurma, AlunoDto novoAlunoDto) {
        // 1. Busca a turma ou lança erro
        TurmaEntity turma = buscarTurmaPorIdOuLancarErro(idTurma);

        // 2. Converte o DTO em Entidade
        AlunoEntity novoAluno = new AlunoEntity();
        novoAluno.setNome(novoAlunoDto.getNome());
        novoAluno.setEmail(novoAlunoDto.getEmail());
        novoAluno.setTurma(turma); // Amarra o novo aluno à turma existente

        // 3. Salva o novo aluno
        AlunoEntity alunoSalvo = alunoRepository.save(novoAluno);

        // 4. Converte para DTO e retorna
        return paraAlunoDto(alunoSalvo); // Usando helper
    }

    /**
     * Requisito 4: Busca de todos os alunos da turma
     */
    public List<AlunoDto> buscarAlunosDaTurma(Long idTurma) {
        // 1. Busca a turma ou lança erro
        TurmaEntity turma = buscarTurmaPorIdOuLancarErro(idTurma);

        // 2. Pega a lista de alunos da entidade (vem do @OneToMany)
        List<AlunoEntity> alunosDaTurma = turma.getAlunos();

        // 3. Converte a lista de AlunoEntity para AlunoDto e retorna
        return alunosDaTurma.stream()
                .map(this::paraAlunoDto) // Usando helper
                .collect(Collectors.toList());
    }


    // --- MÉTODOS DE BUSCA (Ajustados) ---

    public TurmaDto findById(Long turmaId) { // MUDANÇA: int para Long
        TurmaEntity turmaEntity = buscarTurmaPorIdOuLancarErro(turmaId);
        return paraTurmaDto(turmaEntity); // MUDANÇA: Usando helper
    }

    public TurmaDto findByNome(String nome) {
        final TurmaEntity turma = turmaRepository.findByNome(nome);
        if (turma == null) {
            throw new RuntimeException("Turma nao encontrada");
        }
        return paraTurmaDto(turma); // MUDANÇA: Usando helper
    }

    // --- MÉTODOS AJUDANTES (HELPERS) PRIVADOS ---
    
    // NOVO: Método privado para buscar Turma ou lançar erro
    private TurmaEntity buscarTurmaPorIdOuLancarErro(Long turmaId) {
        return turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma nao encontrada"));
    }

    // NOVO: Converte Entidade Turma para DTO Turma
    private TurmaDto paraTurmaDto(TurmaEntity entidade) {
        final TurmaDto dto = new TurmaDto();
        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        dto.setCurso(entidade.getCurso());
        dto.setPeriodo(entidade.getPeriodo());
        
        // Converte também a lista de alunos interna
        if (entidade.getAlunos() != null) {
            dto.setAlunos(entidade.getAlunos().stream()
                    .map(this::paraAlunoDto) // Reutiliza o helper de aluno
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    // NOVO: Converte Entidade Aluno para DTO Aluno
    private AlunoDto paraAlunoDto(AlunoEntity entidade) {
        AlunoDto dto = new AlunoDto();
        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        dto.setEmail(entidade.getEmail());
        // Note: NÃO colocamos a turma aqui para não criar um loop
        return dto;
    }
}
