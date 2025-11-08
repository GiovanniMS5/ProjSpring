package com.sistemaAutoEscola.autoescola.service;

import com.sistemaAutoEscola.autoescola.domain.*;
import com.sistemaAutoEscola.autoescola.repository.AgendamentoAulaRepository;
import com.sistemaAutoEscola.autoescola.repository.AlunoRepository;
import com.sistemaAutoEscola.autoescola.repository.AvaliacaoRepository;
import com.sistemaAutoEscola.autoescola.repository.InstrutorRepository;
import com.sistemaAutoEscola.autoescola.repository.UserRepository;
import enums.SituacaoAgendamento;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AgendamentoAulaServiceImpl implements AgendamentoAulaService {

    // --- DEPENDÊNCIAS INJETADAS VIA CONSTRUTOR ---
    // Agora são 'final', garantindo imutabilidade após a inicialização.
    private final AgendamentoAulaRepository agendamentoRepository;
    private final AlunoRepository alunoRepository;
    private final InstrutorRepository instrutorRepository;
    private final UserRepository userRepository;
    private final AvaliacaoRepository avaliacaoRepository;

    /**
     * Injeção de dependência via construtor.
     * O Spring automaticamente fornecerá as instâncias (Beans)
     * dos repositories necessários.
     */
    @Autowired
    public AgendamentoAulaServiceImpl(AgendamentoAulaRepository agendamentoRepository,
                                      AlunoRepository alunoRepository,
                                      InstrutorRepository instrutorRepository,
                                      UserRepository userRepository,
                                      AvaliacaoRepository avaliacaoRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.alunoRepository = alunoRepository;
        this.instrutorRepository = instrutorRepository;
        this.userRepository = userRepository;
        this.avaliacaoRepository = avaliacaoRepository;
    }

 

    @Override
    public List<AgendamentoAula> findAgendamentosByAlunoLogado() {
        Aluno alunoLogado = getAlunoLogado();
        return agendamentoRepository.findByAluno(alunoLogado);
    }

    @Override
    public List<AgendamentoAula> findAgendamentosByInstrutorLogado() {
        Instrutor instrutorLogado = getInstrutorLogado();
        return agendamentoRepository.findByInstrutor(instrutorLogado);
    }

    @Override
    @Transactional
    public AgendamentoAula concluirAgendamento(Long agendamentoId, Avaliacao avaliacao) {
        Instrutor instrutorLogado = getInstrutorLogado();
        AgendamentoAula agendamento = agendamentoRepository.findById(agendamentoId)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento com ID " + agendamentoId + " não encontrado."));

        // Lógica de segurança: Apenas o instrutor da aula pode concluí-la.
        if (!agendamento.getInstrutor().getId().equals(instrutorLogado.getId())) {
            throw new AccessDeniedException("Você não tem permissão para concluir este agendamento.");
        }

        // Garante que a avaliação seja salva no banco ANTES de ser associada
        Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacao);

        // Atualiza o agendamento
        agendamento.setSituacaoAgendamento(SituacaoAgendamento.CONCLUIDO);
        agendamento.setAvaliacao(avaliacaoSalva);

        return agendamentoRepository.save(agendamento);
    }

    //Recebe lista de aulas em um intervalo de tempo
    public List<AgendamentoAula> buscarPorIntervalo(LocalDateTime inicio, LocalDateTime fim) {
        if (inicio == null || fim == null) {
            throw new IllegalArgumentException("As datas de início e fim não podem ser nulas.");
        }

        if (fim.isBefore(inicio)) {
            throw new IllegalArgumentException("A data final não pode ser anterior à data inicial.");
        }

        return agendamentoRepository.findByDataAgendamentoBetween(inicio, fim);
    }

    /**
     * Sobrecarga — recebe apenas uma data e busca as aulas daquele dia.
     * Converte a data para início e fim do dia automaticamente.
     */
    public List<AgendamentoAula> buscarPorIntervalo(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("A data não pode ser nula.");
        }

        LocalDateTime inicioDoDia = data.atStartOfDay();
        LocalDateTime fimDoDia = data.atTime(LocalTime.MAX);

        return buscarPorIntervalo(inicioDoDia, fimDoDia);
    }

    @Transactional
    public AgendamentoAula alterarStatus(long id, SituacaoAgendamento statusAgendamento){
        AgendamentoAula agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento com ID " + id + " não encontrado."));

        agendamento.setSituacaoAgendamento(statusAgendamento);

        return agendamentoRepository.save(agendamento);
    }

    @Transactional(readOnly = true)
    public List<AgendamentoAula> buscarAgendamentosPorAlunoPorCpf(String cpf) {
        //  Verifica se o usuário logado é um instrutor
        Instrutor instrutorLogado = getInstrutorLogado();
        if (instrutorLogado == null) {
            // Nota: Se getInstrutorLogado() já lança exceção se não encontrar,
            // esta verificação pode não ser estritamente necessária, mas é uma boa defesa.
            throw new AccessDeniedException("Apenas instrutores podem consultar agendamentos de alunos.");
        }

        // 🔍 Se CPF foi informado, busca por CPF
        if (cpf != null && !cpf.isBlank()) {
            return agendamentoRepository.findByAluno_CpfAluno(cpf);
        }
        
         throw new IllegalArgumentException("Informe CPF.");
        }
        
        @Transactional(readOnly = true)
        public List<AgendamentoAula> buscarAgendamentosPorAlunoPorNomes(String pnome, String snome){
        
        //  Verifica se o usuário logado é um instrutor
        Instrutor instrutorLogado = getInstrutorLogado();
        if (instrutorLogado == null) {
            // Nota: Se getInstrutorLogado() já lança exceção se não encontrar,
            // esta verificação pode não ser estritamente necessária, mas é uma boa defesa.
            throw new AccessDeniedException("Apenas instrutores podem consultar agendamentos de alunos.");
        }        
            
        // 🔍 Se nome e sobrenome foram informados, busca por ambos
        if (pnome != null && snome != null && !pnome.isBlank() && !snome.isBlank()) {
            return agendamentoRepository.findByAluno_PnomeAlunoAndAluno_SnomeAluno(pnome, snome);
        }

        // ⚠️ Caso nenhum dado válido tenha sido enviado
        throw new IllegalArgumentException("nome completo (primeiro e segundo nome) para buscar.");
    }


    // ==================================================================
    // MÉTODOS AUXILIARES PRIVADOS
    // ==================================================================

    private String getUsernameDoUsuarioLogado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    private Aluno getAlunoLogado() {
        String username = getUsernameDoUsuarioLogado();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuário '" + username + "' não encontrado."));
        return alunoRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum aluno associado ao usuário '" + username + "'."));
    }

    private Instrutor getInstrutorLogado() {
        String username = getUsernameDoUsuarioLogado();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuário '" + username + "' não encontrado."));
        return instrutorRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum instrutor associado ao usuário '" + username + "'."));
    }
}