package Library;

import java.io.*;

public class Music extends Materials {
    public String genre;

    public Music(String name, String author, int year, String genre) {
        super(name, author, year);
        this.genre = genre;
    }

    public String getGenre() {return genre;}

    void print(){
        System.out.println("Name: " + name);
        System.out.println("Author: " + author);
        System.out.println("Year: " + year);
        System.out.println("Genre: " + genre);
    }

    void write(FileWriter writer) throws IOException{
        writer.write("Music "+name+" "+author+" "+year+" "+genre);
        writer.append('\n');
    }

    @Override
    public boolean equals(Object m) {
        if (m instanceof Music) {
            Music otherMusic = (Music)m;
            return name.equals(otherMusic.getName()) && author.equals(otherMusic.getAuthor()) && year == otherMusic.getYear() && genre == otherMusic.getGenre();
        } else {
            return false;
        }
    }
}
