/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistemaAutoEscola.autoescola.service;

import com.sistemaAutoEscola.autoescola.domain.Cnh;
import java.util.List;

public interface CnhService {

    /**
     * Busca as CNHs associadas ao aluno que está autenticado na sessão atual.
     * @return Uma lista de CNHs do aluno logado.
     */
    List<Cnh> findCnhByAlunoLogado();

    /**
     * Salva uma nova CNH no sistema.
     * @param cnh A entidade Cnh a ser salva.
     * @return A entidade Cnh salva com o ID gerado.
     */
    Cnh saveCnh(Cnh cnh);
}