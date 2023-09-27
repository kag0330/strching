package com.stretching.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("./src/json/" + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (Exception e) {
        	System.out.println(e);
            return "An error occurred";
        }
        return "File successfully uploaded";
    }
}
