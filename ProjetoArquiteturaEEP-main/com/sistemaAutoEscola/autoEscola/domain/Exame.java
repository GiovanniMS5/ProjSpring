/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistemaAutoEscola.autoescola.domain;

import enums.ResultadoExame;
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import lombok.Setter;
import lombok.Getter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "exame")
public class Exame implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exame")
    private Long id;

    @Column(name = "data_exame", nullable = false)
    private LocalDate dataExame;

    @Enumerated(EnumType.STRING)
    @Column(name = "resultado_exame", nullable = false)
    private ResultadoExame resultadoExame;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
  
    private Aluno aluno;
}

