/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistemaAutoEscola.autoescola.service;

import com.sistemaAutoEscola.autoescola.domain.Aluno;
import com.sistemaAutoEscola.autoescola.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public Aluno saveAluno(Aluno aluno) {
        // Adicionar lógica de negócio aqui (ex: validar CPF, etc.)
        return alunoRepository.save(aluno);
    }

    @Override
    public List<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }

    @Override
    public Optional<Aluno> getAlunoById(Long id) {
        return alunoRepository.findById(id);
    }
    
    @Override
    public Optional<Aluno> getAlunoByCpf(String cpf) {
        return alunoRepository.findByCpfAluno(cpf);
    }

    @Override
    public void deleteAluno(Long id) {
        alunoRepository.deleteById(id);
    }
}