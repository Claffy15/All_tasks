import java.util.ArrayList;
import java.util.Scanner;

public class iosifFlavij {
    public static ArrayList<Integer> Action(int step, ArrayList<Integer> array){
        int end = array.size();
        step--;
        for (int i = step; i < end + 4 ; i+=step){
            if (i==array.size()-1){
                array.remove(array.get(i));
                break;
            }
            if (i>= array.size()-1){
                array.remove(array.get(i-array.size()));
                break;
            }
            array.remove(array.get(i));
        }
        return array;
    }

    public static ArrayList<Integer> Move(int step, ArrayList<Integer> array){
        int end = array.size();
        for (int i = 0;i<end*2;i++){
            array = Action(step, array);
            if (array.size()==1){
                break;
            }
        }
        return array;
    }

    public static void main(String[] args){
        ArrayList<Integer> array = new ArrayList<Integer>();

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter people number:");
        int n = scan.nextInt();
        for (int i = 0;i < n;i++){
            array.add(i+1);
        }

        System.out.print("Enter step:");
        int step = scan.nextInt();
        if (step>n || step<2){
            step = 2;
        }
        System.out.print(array + " ");
        System.out.println(step);
        System.out.println(Move(step, array));
    }
}
