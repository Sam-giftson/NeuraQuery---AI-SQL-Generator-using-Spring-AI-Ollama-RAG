package com.example.AI.SQL.Generator.model;

public class SqlResponse {

    private String question;
    private String sql;

    public SqlResponse(String question, String sql) {
        this.question = question;
        this.sql = sql;
    }

    public String getQuestion() {
        return question;
    }

    public String getSql() {
        return sql;
    }
}