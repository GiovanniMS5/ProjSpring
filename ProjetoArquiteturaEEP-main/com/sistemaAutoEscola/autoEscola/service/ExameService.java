/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistemaAutoEscola.autoescola.service;

import com.sistemaAutoEscola.autoescola.domain.Exame;
import java.util.List;

public interface ExameService {

    /**
     * Busca os exames associados ao aluno que está autenticado na sessão atual.
     * @return Uma lista de Exames do aluno logado.
     */
    List<Exame> findExamesByAlunoLogado();

    /**
     * Salva um novo exame no sistema.
     * @param exame A entidade Exame a ser salva.
     * @return A entidade Exame salva com o ID gerado.
     */
    Exame saveExame(Exame exame);
}