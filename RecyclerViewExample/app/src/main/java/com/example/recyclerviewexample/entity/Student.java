package com.example.recyclerviewexample.entity;

public class Student {

    private String name;
    private String _class;
    private int id;

    public Student(String name, String _class) {
        this.name = name;
        this._class = _class;
    }

    public Student(int id, String name, String _class) {
        this.id = id;
        this.name = name;
        this._class = _class;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String get_class() {
        return _class;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClass(String _class) {
        this._class = _class;
    }

    public void setId(int id) {
        this.id = id;
    }
}
