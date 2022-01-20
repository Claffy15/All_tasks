package Library;

import java.io.*;

public class Book extends Materials{
    protected int page;

    public Book(String name, String author, int year, int page) {
        super(name, author, year);
        this.page = page;
    }

    public int getPage() {return page;}

    void print(){
        System.out.println("Name: " + name);
        System.out.println("Author: " + author);
        System.out.println("Year: " + year);
        System.out.println("Page: " + page);
    }

    void write(FileWriter writer) throws IOException{
        writer.write("Book "+name+" "+author+" "+year+" "+page);
        writer.append('\n');
    }

    @Override
    public boolean equals(Object b) {
        if (b instanceof Book) {
            Book otherBook = (Book)b;
            return name.equals(otherBook.getName()) && author.equals(otherBook.getAuthor()) && year == otherBook.getYear() && page == otherBook.getPage();
        } else {
            return false;
        }
    }
}
