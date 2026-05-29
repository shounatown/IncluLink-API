package com.inclulink.core.entidades;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "habilidad")
public class Habilidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(name = "es_verificada", nullable = false)
    private Boolean esVerificada = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_habilidad", nullable = false)
    private TipoHabilidad tipoHabilidad;

    @OneToMany(mappedBy = "habilidad", orphanRemoval = true)
    private List<CandidatoHabilidad> candidatos= new ArrayList<>();
}