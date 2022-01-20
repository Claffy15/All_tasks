package Library;

import java.io.FileWriter;
import java.io.IOException;

public class Film extends Materials{
    public String[] actors;

    public Film(String name, String author, int year, String[] actors) {
        super(name, author, year);
        this.actors = actors;
    }

    public String[] getActors() {
        return actors;
    }

    void print(){
        System.out.println("Name: " + name);
        System.out.println("Author: " + author);
        System.out.println("Year: " + year);
        System.out.print("Actors: ");
        for (int i = 0; i< actors.length; i++) {
            System.out.print(actors[i] + " ");
        }
        System.out.println();
    }

    void write(FileWriter writer) throws IOException {
        writer.write("Film "+name+" "+author+" "+year+" ");
        for (int i = 0; i< actors.length; i++) {
            if (i == actors.length-1){
                writer.write(actors[i]);
                break;
            }
            writer.write(actors[i]+",");
        }
        writer.append('\n');
    }

    @Override
    public boolean equals(Object f) {
        if (f instanceof Film) {
            Film otherFilm = (Film)f;
            return name.equals(otherFilm.getName()) && author.equals(otherFilm.getAuthor()) && year == otherFilm.getYear() && actors.equals(otherFilm.getActors());
        } else {
            return false;
        }
    }
}
