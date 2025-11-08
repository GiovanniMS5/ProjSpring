package com.sistemaAutoEscola.autoescola.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority; 

import java.io.Serializable;

/**
 * Entidade que representa os papéis (roles) de um usuário.
 * AGORA IMPLEMENTA GrantedAuthority, essencial para o Spring Security.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@EqualsAndHashCode(of = "id")
public class Role implements GrantedAuthority, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Integer id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name; // Ex: ROLE_ALUNO, ROLE_INSTRUTOR, ROLE_ADMIN

    /**
     * Retorna o nome do papel (ex: "ROLE_ALUNO") como a autoridade.
     * Método obrigatório de GrantedAuthority.
     */
    @Override
    public String getAuthority() {
        return this.name;
    }
}