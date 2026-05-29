package com.inclulink.core.entidades;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vacante")
public class Vacante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "foto", length = 255)
    private String foto;

    @Column(name = "requisitos", nullable = false, columnDefinition = "TEXT")
    private String requisitos;

    @Column(name = "salario_maximo", nullable = false, precision = 10, scale = 2)
    private BigDecimal salarioMaximo;

    @Column(name = "salario_minimo", nullable = false, precision = 10, scale = 2)
    private BigDecimal salarioMinimo;

    @Column(name = "anos_experiencia", nullable = false)
    private Integer anosExperiencia;

    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDate fechaPublicacion;

    @Column(name = "fecha_cierre")
    private LocalDate fechaCierre;

    @Column(name = "cupo", nullable = false)
    private Integer cupo;

    @Column(name = "vistas_contador", nullable = false)
    private Integer vistasContador = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_vacante")
    private EstadoVacante estadoVacante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ciudad")
    private Ciudad ciudad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modalidad")
    private Modalidad modalidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jornada")
    private Jornada jornada;

    @OneToMany(mappedBy = "vacante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Postulacion> postulaciones = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "vacante_accesibilidad",
            joinColumns = @JoinColumn(name = "id_vacante"),
            inverseJoinColumns = @JoinColumn(name = "id_accesibilidad")
    )
    private List<Accesibilidad> accesibilidades = new ArrayList<>();

}