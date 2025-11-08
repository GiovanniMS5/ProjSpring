/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistemaAutoEscola.autoescola.repository;

import com.sistemaAutoEscola.autoescola.domain.Aluno;
import com.sistemaAutoEscola.autoescola.domain.Exame;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Long> {

    public List<Exame> findByAluno(Aluno aluno);
}
