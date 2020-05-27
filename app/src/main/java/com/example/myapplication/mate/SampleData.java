package com.example.myapplication.mate;

public class SampleData {
    private String title;
    private String writer;
    private String date;
    private String content;

    public SampleData(String title, String writer, String date, String content){
        this.title = title;
        this.writer = writer;
        this.date = date;
        this.content = content;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getWriter()
    {
        return this.writer;
    }

    public String getDate()
    {
        return this.date;
    }

    public String getContent()
    {
        return this.content;
    }
}
