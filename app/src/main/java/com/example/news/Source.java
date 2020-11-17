package com.example.news;

public class Source {
    private String author;
    private String name;
    private String id;

    public Source(String author, String name, String id) {
        this.author = author;
        this.name = name;
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
