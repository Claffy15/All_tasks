import java.util.ArrayList;
import java.util.List;

public class Stack <T>{
    private List<T> stack;

    public Stack(List<T> stack) {
        this.stack = stack;
    }

    public void push(T element) {
        stack.add(element);
    }

    public void pop(){
        if (stack.size()!=0){
            stack.remove(stack.get(stack.size()-1));
        }
    }

    public void printStack(){
        System.out.print("Stack: ");
        for (T elem : stack){
            System.out.print(elem+" ");
        }
        System.out.println();
    }

    public List<T> reverse(){
        List<T> reversedStack = new ArrayList<>();
        for (int i = stack.size()-1; i>=0;i--){
            reversedStack.add(stack.get(i));
        }
        return reversedStack;
    }

    private int size(){
        return stack.size();
    }

    public T getElem(){
        if (stack.size()!=0) {
            return stack.get(stack.size()-1);
        }
        return null;
    }

    public boolean isSymmetric(){
        List<T> reversedStack = reverse();
        for (int i = 0; i < stack.size() - 1; i++){
            if (stack.get(i).equals(reversedStack.get(i)) == false){
                return false;
            }
        }
        return true;
    }
}
