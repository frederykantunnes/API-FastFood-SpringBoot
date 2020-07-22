package br.com.atdsistemas.fastfood.util;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class FilesUpload {

    public static String upload(MultipartFile file, String directory){

        String response = "";
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        List<String> tempFileNames = new ArrayList<>();
        String tempFileName;
        FileOutputStream fo;
        try {
            tempFileName = file.getOriginalFilename();
            tempFileNames.add(tempFileName);
            fo = new FileOutputStream(tempFileName);
            fo.write(file.getBytes());
            fo.close();
            map.add("image", new FileSystemResource(tempFileName));
            map.add("directory", Directories.FOODSSERVICES.toString());
            RestTemplate restTemplate = new RestTemplate();
            URI uri = new URI("http://foods.atdsistemas.com.br/upload.php");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
            response = restTemplate.postForObject(uri, requestEntity, String.class);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        for (String fileName : tempFileNames) {
            File f = new File(fileName);
            f.delete();
        }
        return response;
    }

}
