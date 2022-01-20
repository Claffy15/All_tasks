import java.util.ArrayList;

public class stackMain {
    public static void main(String[] args){
        Stack mStack = new Stack(new ArrayList<>());
        mStack.push("A");
        mStack.push("B");
        mStack.push("A");
        //mStack.pop();
        mStack.printStack();
        Stack reversed = new Stack((ArrayList) mStack.reverse());
        reversed.printStack();
        System.out.println(mStack.isSymmetric());
    }
}
