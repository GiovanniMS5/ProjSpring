package com.sistemaAutoEscola.autoescola.repository;

import com.sistemaAutoEscola.autoescola.domain.Aluno;
import com.sistemaAutoEscola.autoescola.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    // Busca o aluno associado a um usuário específico (JWT)
    Optional<Aluno> findByUser(User user);

    // Busca aluno pelo CPF (caso precise de consultas rápidas)
    Optional<Aluno> findByCpfAluno(String cpf);

    // CORRIGIDO: Busca alunos pelo primeiro nome (busca parcial, ignorando maiúsculas/minúsculas)
    List<Aluno> findByPnomeAlunoContainingIgnoreCase(String pnome);
    
    // CORRIGIDO: Busca alunos pelo sobrenome (busca parcial, ignorando maiúsculas/minúsculas)
    List<Aluno> findBySnomeAlunoContainingIgnoreCase(String snome);
    
}

