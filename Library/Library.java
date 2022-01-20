package Library;

import java.io.*;

public class Library {
    protected Materials[] materials;
    private int size;

    public Library() {
        size = 0;
        materials = new Materials[0];
    }

    public void add(Materials material) {
        Materials[] tmpArr = new Materials[size+1];
        for (int i=0; i<size;i++)
            tmpArr[i] = materials[i];
        tmpArr[size]=material;
        size++;
        materials = tmpArr;
    }

    public Materials getByName(String name) {
        for (Materials m: materials) {
            if (m.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }

    public void sortByName(){
        Materials[] arr = new Materials[materials.length];
        int place = materials.length-1;
        for (int i = 0; i < materials.length; i++) {
            for (int j = 0; j < materials.length; j++) {
                if ((materials[i].name).charAt(0) < (materials[j].name).charAt(0)){
                    place--;
                }
            }
            arr[place] = materials[i];
            place = materials.length-1;
        }
        materials = arr;
    }

    public void sortByYear(){
        Materials[] arr = new Materials[materials.length];
        int place = materials.length-1;
        for (int i = 0; i < materials.length; i++) {
            for (int j = 0; j < materials.length; j++) {
                if (materials[i].year < materials[j].year){
                    place--;
                }
            }
            arr[place] = materials[i];
            place = materials.length-1;
        }
        materials = arr;
    }


    public void print() {
        for (Materials m: materials) {
            m.print();
            System.out.println();
        }
    }

    public void write() throws IOException {
        FileWriter writer = new FileWriter("writed_file.txt");
        for (Materials m: materials) {
            m.write(writer);
        }
        writer.flush();
    }

}
