package com.example.AI.SQL.Generator.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;

@Service
public class SchemaService {

    public String loadSchema() {

        try {

            ClassPathResource resource =
                    new ClassPathResource("schema-docs/employee-schema.txt");

            return Files.readString(resource.getFile().toPath());

        } catch (IOException e) {

            throw new RuntimeException("Failed to load schema file");
        }
    }
}