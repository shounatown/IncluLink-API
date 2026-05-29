package com.inclulink.core.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ciudad")
public class Ciudad implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name="nombre", nullable = false, length = 100)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pais", nullable = false)
    @JsonIgnoreProperties("pais")
    private Pais pais;

    @OneToMany(mappedBy = "ciudad")
    private List<Empresa> empresas;

    @OneToMany(mappedBy = "ciudad")
    private List<Vacante> vacantes;

    @OneToMany(mappedBy = "ciudad")
    private List<Candidato> candidatos;

}

