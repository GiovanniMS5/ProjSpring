/*
 * Repositório JPA para gerenciamento dos agendamentos de aula.
 */
package com.sistemaAutoEscola.autoescola.repository;

import com.sistemaAutoEscola.autoescola.domain.AgendamentoAula;
import com.sistemaAutoEscola.autoescola.domain.Aluno;
import com.sistemaAutoEscola.autoescola.domain.Instrutor;
import enums.SituacaoAgendamento;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoAulaRepository extends JpaRepository<AgendamentoAula, Long> {

    // Encontra todos os agendamentos de um aluno específico
    List<AgendamentoAula> findByAluno(Aluno aluno);

    // Encontra todos os agendamentos de um instrutor específico
    List<AgendamentoAula> findByInstrutor(Instrutor instrutor);

    // Busca agendamentos dentro de um intervalo de data/hora
    List<AgendamentoAula> findByDataAgendamentoBetween(LocalDateTime inicio, LocalDateTime fim);

    // Busca agendamentos por situação (enum)
    List<AgendamentoAula> findBySituacaoAgendamento(SituacaoAgendamento situacaoAgendamento);

    // Busca agendamentos de um aluno específico por situação
    List<AgendamentoAula> findByAlunoAndSituacaoAgendamento(Aluno aluno, SituacaoAgendamento situacaoAgendamento);

    // Busca agendamentos de um instrutor específico por situação
    List<AgendamentoAula> findByInstrutorAndSituacaoAgendamento(Instrutor instrutor, SituacaoAgendamento situacaoAgendamento);
    
    List<AgendamentoAula> findByAluno_PnomeAlunoAndAluno_SnomeAluno(String pnomeAluno, String snomeAluno);

    List<AgendamentoAula> findByAluno_CpfAluno(String cpfAluno);    
}
