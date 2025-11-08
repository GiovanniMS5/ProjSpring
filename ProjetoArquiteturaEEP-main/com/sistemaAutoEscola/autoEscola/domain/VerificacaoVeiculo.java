/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistemaAutoEscola.autoescola.domain;

import enums.TipoVerificacao;
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "verificacao_veiculo")
public class VerificacaoVeiculo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_verificacao")
    private Long id;

    @Column(name = "is_solucionado", nullable = false)
    private boolean isSolucionado;

    @Column(name = "desc_verificacao", nullable = false, length = 200)
    private String descVerificacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_verificacao", nullable = false)
    private TipoVerificacao tipoVerificacao;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "instrutor_id", nullable = false)
    private Instrutor instrutor;
}

