package com.sistemaAutoEscola.autoescola.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;
import lombok.Setter;
import lombok.Getter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "veiculo")
public class Veiculo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_veiculo")
    private Long id;

    @Column(name = "tipo_veiculo", nullable = false, length = 15)
    private String tipoVeiculo;

    @Column(name = "marca_veiculo", nullable = false, length = 15)
    private String marcaVeiculo;

    @Column(name = "placa_veiculo", nullable = false, unique = true, length = 15)
    private String placaVeiculo;

    @Column(name = "vencimento_documento", nullable = false)
    private LocalDate vencimentoDocumento;
    
    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VerificacaoVeiculo> verificacoes;
}
