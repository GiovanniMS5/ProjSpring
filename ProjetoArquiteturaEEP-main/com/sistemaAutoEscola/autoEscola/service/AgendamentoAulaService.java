package com.sistemaAutoEscola.autoescola.service;

import com.sistemaAutoEscola.autoescola.domain.AgendamentoAula;
import com.sistemaAutoEscola.autoescola.domain.Avaliacao; // <--- ADICIONADO
import java.util.List;
import com.sistemaAutoEscola.autoescola.domain.AgendamentoAula;



public interface AgendamentoAulaService {

    /**
     * Busca os agendamentos do aluno atualmente autenticado.
     * @return Uma lista de AgendamentoAula.
     */
    List<AgendamentoAula> findAgendamentosByAlunoLogado();
    /**
     * Busca os agendamentos do instrutor atualmente autenticado.
     * @return Uma lista de AgendamentoAula.
     */
    List<AgendamentoAula> findAgendamentosByInstrutorLogado();

    /**
     * Conclui um agendamento de aula e associa uma avaliação a ele.
     * Apenas o instrutor responsável pela aula pode concluí-la.
     *
     * @param agendamentoId O ID do agendamento a ser concluído.
     * @param avaliacao A entidade Avaliacao (que será salva e associada).
     * @return O AgendamentoAula atualizado com o status CONCLUIDO e a avaliação.
     */
    AgendamentoAula concluirAgendamento(Long agendamentoId, Avaliacao avaliacao);
    // ==================================================================
    
 
}

