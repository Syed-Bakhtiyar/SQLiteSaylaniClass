package com.example.syedinkisarahmed.sqlitesample;

/**
 * Created by Syed Bakhtiyar on 11/6/2016.
 */
public class Record {
    String name,fName;
    int marks;

    public Record(String name, String fName, int marks) {
        this.name = name;
        this.fName = fName;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public String getfName() {
        return fName;
    }

    public int getMarks() {
        return marks;
    }
}
