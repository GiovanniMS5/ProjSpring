/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistemaAutoEscola.autoescola.service;

import com.sistemaAutoEscola.autoescola.domain.Aluno;
import java.util.List;
import java.util.Optional;

public interface AlunoService {
    Aluno saveAluno(Aluno aluno);
    List<Aluno> getAllAlunos();
    Optional<Aluno> getAlunoById(Long id);
    Optional<Aluno> getAlunoByCpf(String cpf);
    void deleteAluno(Long id);
}