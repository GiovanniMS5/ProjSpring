/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistemaAutoEscola.autoescola.service;

import com.sistemaAutoEscola.autoescola.domain.Instrutor;
import com.sistemaAutoEscola.autoescola.repository.InstrutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstrutorServiceImpl implements InstrutorService {

    @Autowired
    private InstrutorRepository instrutorRepository;

    @Override
    public Instrutor saveInstrutor(Instrutor instrutor) {
        // Lógica de negócio pode ser adicionada aqui
        return instrutorRepository.save(instrutor);
    }

    @Override
    public List<Instrutor> getAllInstrutores() {
        return instrutorRepository.findAll();
    }

    @Override
    public Optional<Instrutor> getInstrutorById(Long id) {
        return instrutorRepository.findById(id);
    }

    @Override
    public void deleteInstrutor(Long id) {
        instrutorRepository.deleteById(id);
    }
}
