/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistemaAutoEscola.autoescola.repository;

import com.sistemaAutoEscola.autoescola.domain.Instrutor;
import com.sistemaAutoEscola.autoescola.domain.Veiculo;
import com.sistemaAutoEscola.autoescola.domain.VerificacaoVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerificacaoVeiculoRepository extends JpaRepository<VerificacaoVeiculo, Long> {

    // Encontra todas as verificações para um veículo específico
    List<VerificacaoVeiculo> findByVeiculo(Veiculo veiculo);

    // Encontra todas as verificações registradas por um instrutor
    List<VerificacaoVeiculo> findByInstrutor(Instrutor instrutor);
}
