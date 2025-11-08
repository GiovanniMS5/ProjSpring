package com.sistemaAutoEscola.autoescola.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "cnh")
public class Cnh implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cnh")
    private Long id;

    @Column(name = "tipo_cnh", nullable = false, length = 2)
    private String tipoCnh;

    @Column(name = "data_registro_cnh", nullable = false)
    private LocalDate dataRegistroCnh;

    @OneToOne
    @JoinColumn(name = "aluno_id", unique = true)
     private Aluno aluno;
}

