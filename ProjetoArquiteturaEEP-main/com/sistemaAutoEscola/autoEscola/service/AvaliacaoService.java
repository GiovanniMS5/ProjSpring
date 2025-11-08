/*
 * Novo arquivo de interface para AvaliacaoService
 */
package com.sistemaAutoEscola.autoescola.service;

import com.sistemaAutoEscola.autoescola.domain.Avaliacao;
import java.util.List;
import java.util.Optional;

/**
 * Interface de serviço para gerenciar Avaliações.
 */
public interface AvaliacaoService {

    /**
     * Salva uma nova avaliação.
     * @param avaliacao A entidade Avaliacao a ser salva.
     * @return A entidade salva.
     */
    Avaliacao saveAvaliacao(Avaliacao avaliacao);

    /**
     * Busca uma avaliação pelo seu ID.
     * @param id O ID da avaliação.
     * @return Um Optional contendo a avaliação, se encontrada.
     */
    Optional<Avaliacao> getAvaliacaoById(Long id);

    /**
     * Busca todas as avaliações cadastradas.
     * @return Uma lista de todas as avaliações.
     */
    List<Avaliacao> getAllAvaliacaos();
    
    /**
     * Deleta uma avaliação pelo seu ID.
     * @param id O ID da avaliação a ser deletada.
     */
    void deleteAvaliacao(Long id);
}