import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Person{
    String surname;
    int age;
    Person (String surname, int age) {
        this.age = age;
        this.surname = surname;
    }
}

public class Stream_task {
    public static void main(String[] args) {
        List list = Stream.of("My", "name", "is", "John", "Doe").map(string -> string.toUpperCase()).collect(Collectors.toList());
        System.out.println(list);

        list = Stream.of("My", "name", "is", "John", "Doe").filter(data -> data.length() < 4).collect(Collectors.toList());
        System.out.println(list);

        ArrayList<Person> people = new ArrayList<Person>();
        people.add(new Person("Sara", 4));
        people.add(new Person("Viktor", 40));
        people.add(new Person("Eva", 42));

        var resSum = people.stream().map(age -> age.age).reduce(0,(sum, elem) -> sum+elem);
        var count = people.stream().map(age -> age.age).reduce(0,(sum, elem) -> sum+1);
        int mins = 1000;
        var max = people.stream().map(age -> age.age).filter(data -> data < mins);
        var min = people.stream().map(age -> age.age).reduce(0,(sum, elem) -> sum+1);
        System.out.println(resSum/count);


    }
}
