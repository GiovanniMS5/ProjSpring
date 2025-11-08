/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistemaAutoEscola.autoescola.service;

import com.sistemaAutoEscola.autoescola.domain.Instrutor;
import java.util.List;
import java.util.Optional;

public interface InstrutorService {
    Instrutor saveInstrutor(Instrutor instrutor);
    List<Instrutor> getAllInstrutores();
    Optional<Instrutor> getInstrutorById(Long id);
    void deleteInstrutor(Long id);
}