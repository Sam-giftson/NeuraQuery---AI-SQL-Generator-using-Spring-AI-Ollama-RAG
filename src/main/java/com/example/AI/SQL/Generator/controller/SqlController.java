package com.example.AI.SQL.Generator.controller;

import com.example.AI.SQL.Generator.model.SqlRequest;
import com.example.AI.SQL.Generator.model.SqlResponse;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.*;
import com.example.AI.SQL.Generator.service.SchemaService;

@RestController
@RequestMapping("/api/sql")
@CrossOrigin("*")
public class SqlController {

//    private final ChatModel chatModel;
    private final ChatModel chatModel;
    private final SchemaService schemaService;

    public SqlController(ChatModel chatModel, SchemaService schemaService) {
        this.chatModel = chatModel;
        this.schemaService = schemaService;
    }

    @PostMapping("/generate")
    public SqlResponse generateSql(@RequestBody SqlRequest request) {

        String schema = schemaService.loadSchema();

        String prompt = """
        You are an SQL generator.

        Return ONLY one SQL query.
        No markdown.
        No explanation.
        No comments.

        IMPORTANT RULES:
        - Use ONLY the tables and columns from the given schema.
        - Do NOT create imaginary tables.
        - Do NOT create imaginary columns.
        - Generate query ONLY for the exact user request.
        - Do not add extra conditions.
        - Generate ONLY SELECT queries.

        DATABASE SCHEMA:
        %s

        USER QUESTION:
        %s
        """.formatted(schema, request.getQuestion());

        String sql = chatModel.call(prompt);

        sql = sql
                .replace("```sql", "")
                .replace("```", "")
                .replace("\n", " ")
                .replace("\r", " ")
                .trim();

        return new SqlResponse(request.getQuestion(), sql);
    }
}