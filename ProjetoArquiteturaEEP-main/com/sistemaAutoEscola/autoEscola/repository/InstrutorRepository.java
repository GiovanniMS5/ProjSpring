package com.sistemaAutoEscola.autoescola.repository;

import com.sistemaAutoEscola.autoescola.domain.Instrutor;
import com.sistemaAutoEscola.autoescola.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {

    // Busca o instrutor associado a um usuário específico (JWT)
    Optional<Instrutor> findByUser(User user);

    // CORRIGIDO: Busca instrutores pelo primeiro nome (busca parcial, ignorando maiúsculas/minúsculas)
    List<Instrutor> findByPnomeInstrutorContainingIgnoreCase(String pnome);
    
    // CORRIGIDO: Busca instrutores pelo sobrenome (busca parcial, ignorando maiúsculas/minúsculas)
    List<Instrutor> findBySnomeInstrutorContainingIgnoreCase(String snome);

}
