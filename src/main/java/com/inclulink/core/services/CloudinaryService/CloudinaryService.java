package com.inclulink.core.services.CloudinaryService;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface CloudinaryService {
    String subirArchivo(MultipartFile archivo) throws IOException;
}