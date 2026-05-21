package com.inclulink.features.catalogo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoDependienteDTO {
    private Long id;
    private String nombre;
    private Long padreId;
}