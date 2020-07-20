package br.com.atdsistemas.fastfood.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageUploadService {

    @Value("${path.raiz}")
    private String raiz;

    public String upload(String directory, MultipartFile image){
        Path directoryPath = Paths.get(this.raiz, directory);
        Path filePath = directoryPath.resolve(image.getOriginalFilename());

        try {
            Files.createDirectories(directoryPath);
            image.transferTo(filePath.toFile());
        }catch (IOException e){
            throw new RuntimeException("Problem file save");
        }
        return "";
    }

}
