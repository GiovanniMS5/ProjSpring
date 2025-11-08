package com.sistemaAutoEscola.autoescola.controller;

import com.sistemaAutoEscola.autoescola.domain.Avaliacao;
import com.sistemaAutoEscola.autoescola.domain.AgendamentoAula;
import com.sistemaAutoEscola.autoescola.service.AvaliacaoServiceImpl;
import com.sistemaAutoEscola.autoescola.service.AgendamentoAulaServiceImpl;
import enums.SituacaoAgendamento;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoServiceImpl avaliacaoService;

    @Autowired
    private AgendamentoAulaServiceImpl agendamentoService;

    // ============================================================
    //  Somente INSTRUTORES podem criar, ver, editar ou deletar avaliações
    // ============================================================

    /*
      Cria uma nova avaliação e vincula ao agendamento correspondente Também marca o agendamento como CONCLUÍDO.
    */
    @PostMapping("/agendamento/{agendamentoId}")
    @PreAuthorize("hasRole('INSTRUTOR')")
    public ResponseEntity<Avaliacao> criarAvaliacao(
            @PathVariable Long agendamentoId,
            @RequestBody Avaliacao avaliacao) {

        // Salva a avaliação no banco
        Avaliacao novaAvaliacao = avaliacaoService.saveAvaliacao(avaliacao);

        // Atualiza o agendamento com a avaliação (1:1)
        AgendamentoAula agendamento = agendamentoService.concluirAgendamento(agendamentoId, novaAvaliacao);

        // Retorna a nova avaliação com URI
        return ResponseEntity.created(URI.create("/api/avaliacoes/" + novaAvaliacao.getId()))
                .body(novaAvaliacao);
    }

    
     // Lista todas as avaliações (somente instrutores têm acesso).
     
    @GetMapping
    @PreAuthorize("hasRole('INSTRUTOR')")
    public ResponseEntity<List<Avaliacao>> listarAvaliacoes() {
        List<Avaliacao> avaliacoes = avaliacaoService.getAllAvaliacaos();
        return ResponseEntity.ok(avaliacoes);
    }

    
     //Busca uma avaliação pelo ID.
     
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('INSTRUTOR')")
    public ResponseEntity<Avaliacao> buscarPorId(@PathVariable Long id) {
        Avaliacao avaliacao = avaliacaoService.getAvaliacaoById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação com ID " + id + " não encontrada."));
        return ResponseEntity.ok(avaliacao);
    }

    /**
     * Atualiza uma avaliação existente (nota, tipo e descrição).
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('INSTRUTOR')")
    public ResponseEntity<Avaliacao> atualizarAvaliacao(
            @PathVariable Long id,
            @RequestBody Avaliacao novaAvaliacao) {

        Avaliacao existente = avaliacaoService.getAvaliacaoById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação com ID " + id + " não encontrada."));

        existente.setNotaAvaliacao(novaAvaliacao.getNotaAvaliacao());
        existente.setTipoAvaliacao(novaAvaliacao.getTipoAvaliacao());
        existente.setDescAvaliacao(novaAvaliacao.getDescAvaliacao());

        Avaliacao atualizada = avaliacaoService.saveAvaliacao(existente);
        return ResponseEntity.ok(atualizada);
    }

    
   
}
