import java.util.Scanner;
import java.util.Arrays;

public class krestiki_noliki {
    public static boolean win(String arr[], String symbol) {
        if ((arr[0]==symbol && arr[0] == arr[1] && arr[1] == arr[2]) || (arr[3]==symbol && arr[3] == arr[4] && arr[4] == arr[5]) || (arr[6]==symbol && arr[6] == arr[7] && arr[7] == arr[8])) {
            return true;
        } else if (((arr[0]==symbol && arr[0] == arr[3] && arr[3] == arr[6]) || (arr[1]==symbol && arr[1] == arr[4] && arr[4] == arr[7]) || (arr[2]==symbol && arr[2] == arr[5] && arr[5] == arr[8]))){
            return true;
        } else if (((arr[0]==symbol && arr[0] == arr[4] && arr[4] == arr[8]) || (arr[2]==symbol && arr[2] == arr[4] && arr[4] == arr[6]))) {
            return true;
        }
        return false;
    }

    public static String[] put(String arr[], String symbol, String who){
        System.out.print(who + " enter pos: ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int i = Integer.parseInt(s);
        arr[i] = symbol;
        return arr;
    }

    public static void draw(String arr[]){
        System.out.println();
        int count = 1;
        for (String i : arr){
            if (count%3==0 && count!=0) {
                System.out.println(i);
            }else {
                System.out.print(i);
            }
            count++;
        }
        System.out.println();
    }

    public static void main(String args[]){
        String arr[] = {" "," "," ",
                        " "," "," ",
                        " "," "," ",};
        while (1>0){
            arr = put(arr, "x", "x");
            draw(arr);
            if (win(arr,"x") == true){
                System.out.print("x won");
                break;
            }
            arr = put(arr, "o", "o");
            draw(arr);
            if (win(arr,"x") == true){
                System.out.print("o won");
                break;
            }
        }
    }
}
