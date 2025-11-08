/*
 * Nova classe de implementação para AvaliacaoService
 */
package com.sistemaAutoEscola.autoescola.service;

import com.sistemaAutoEscola.autoescola.domain.Avaliacao;
import com.sistemaAutoEscola.autoescola.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Override
    public Avaliacao saveAvaliacao(Avaliacao avaliacao) {
        // Lógica de negócio (ex: validar nota) pode ser adicionada aqui.
        return avaliacaoRepository.save(avaliacao);
    }

    @Override
    public Optional<Avaliacao> getAvaliacaoById(Long id) {
        return avaliacaoRepository.findById(id);
    }

    @Override
    public List<Avaliacao> getAllAvaliacaos() {
        return avaliacaoRepository.findAll();
    }

    @Override
    public void deleteAvaliacao(Long id) {
        avaliacaoRepository.deleteById(id);
    }
}