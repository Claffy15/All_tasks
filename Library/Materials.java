package Library;

import java.io.FileWriter;
import java.io.IOException;

public abstract class Materials {
    protected String name, author;
    protected int year;

    public Materials(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public String getName() { return name; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }

    abstract void print();
    abstract void write(FileWriter writer) throws IOException;
}
