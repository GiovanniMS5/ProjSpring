/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistemaAutoEscola.autoescola.service;

import com.sistemaAutoEscola.autoescola.domain.Aluno;
import com.sistemaAutoEscola.autoescola.domain.Exame;
import com.sistemaAutoEscola.autoescola.domain.User;
import com.sistemaAutoEscola.autoescola.repository.AlunoRepository;
import com.sistemaAutoEscola.autoescola.repository.ExameRepository;
import com.sistemaAutoEscola.autoescola.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExameServiceImpl implements ExameService {

    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Exame> findExamesByAlunoLogado() {
        // A lógica é idêntica à do CnhService para encontrar o aluno
        String username = getUsernameDoUsuarioLogado();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Aluno aluno = alunoRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado para o usuário logado"));

        // Retorna a lista de Exames para o aluno encontrado
        return exameRepository.findByAluno(aluno);
    }

    @Override
    public Exame saveExame(Exame exame) {
        // Validações de negócio podem ser adicionadas aqui
        return exameRepository.save(exame);
    }

    private String getUsernameDoUsuarioLogado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}