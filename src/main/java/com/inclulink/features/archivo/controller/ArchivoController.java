package com.inclulink.features.archivo.controller;

import com.inclulink.core.services.CloudinaryService.CloudinaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/archivos")
@CrossOrigin(origins = "*")
public class ArchivoController {

    private final CloudinaryService cloudinaryService;

    public ArchivoController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping("/subir")
    public ResponseEntity<?> subirArchivo(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Por favor, selecciona un archivo válido."));
        }

        try {
            String urlArchivo = cloudinaryService.subirArchivo(file);

            return ResponseEntity.ok(Map.of("url", urlArchivo));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error al subir a Cloudinary: " + e.getMessage()));
        }
    }
}