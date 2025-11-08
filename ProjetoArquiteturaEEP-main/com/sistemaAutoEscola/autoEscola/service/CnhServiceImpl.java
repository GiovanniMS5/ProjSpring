package com.sistemaAutoEscola.autoescola.service;

import com.sistemaAutoEscola.autoescola.domain.Aluno;
import com.sistemaAutoEscola.autoescola.domain.Cnh;
import com.sistemaAutoEscola.autoescola.domain.User;
import com.sistemaAutoEscola.autoescola.repository.AlunoRepository;
import com.sistemaAutoEscola.autoescola.repository.CnhRepository;
import com.sistemaAutoEscola.autoescola.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CnhServiceImpl implements CnhService {

    @Autowired
    private CnhRepository cnhRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Cnh> findCnhByAlunoLogado() {
        // 1. Pega o username do usuário autenticado no contexto de segurança
        String username = getUsernameDoUsuarioLogado();

        // 2. Busca o usuário no banco de dados
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        // 3. Busca o aluno associado a esse usuário
        Aluno aluno;
        aluno = alunoRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado para o usuário logado"));

        // 4. Retorna a lista de CNHs para o aluno encontrado
        return cnhRepository.findByAluno(aluno);
    }

    @Override
    public Cnh saveCnh(Cnh cnh) {
        // Adicionar validações de negócio aqui se necessário
        // Ex: verificar se o aluno referenciado na CNH existe
        return cnhRepository.save(cnh);
    }

    /**
     * Método auxiliar para extrair o nome de usuário do contexto de segurança do Spring.
     */
    private String getUsernameDoUsuarioLogado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}