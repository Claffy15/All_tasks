package Library;

import java.io.File;
import java.util.Scanner;
import java.lang.String;

public class LibMain {
    public static String[] makeArr(String str){
        String[] arr = str.split(",");
        return arr;
    }

    public static void main(String[] args) throws Exception{
        Library lib = new Library();
        /*
        Book book1 = new Book("G","author1",20,100);
        Book book2 = new Book("D","author2",65,100);
        String[] castArr = new String[2];
        castArr[0]=new String("actor1");
        castArr[1]=new String("actor2");
        Film film = new Film("T","name3",2022,castArr);

        lib.add(book1);
        lib.add(book2);
        lib.add(film);
        lib.sortByName();
        lib.print();
        System.out.println();
        lib.sortByYear();
        lib.print();
         */

        File file = new File("C:\\Артур\\Программирования\\Java\\Homework\\INFO.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            var a = sc.next();
            if (a.equals("Book")) {
                Book book = new Book(sc.next(), sc.next(), Integer.parseInt(sc.next()), Integer.parseInt(sc.next()));
                lib.add(book);
            }else if (a.equals("Film")) {
                Film film = new Film(sc.next(), sc.next(), Integer.parseInt(sc.next()), makeArr(sc.next()));
                lib.add(film);
            }else if (a.equals("Music")) {
                Music music = new Music(sc.next(), sc.next(), Integer.parseInt(sc.next()), sc.next());
                lib.add(music);
            }
        }
        lib.print();
        lib.write();
    }
}
