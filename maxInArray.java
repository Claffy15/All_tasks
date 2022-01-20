import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class maxInArray {
    static class Max extends Thread
    {
        public void run() {
            max = 0;
            for(int it = offset; it < offset+length ;it++)
            {
                if (arr.get(it)>max){
                    max=arr.get(it);
                }
            }
        }
        public int max;
        static ArrayList<Integer> arr;
        int offset,length;
    }
    public static void main(String[] args)
    {
        Random rand = new Random();
        ArrayList<Integer> data = new ArrayList<Integer>();
        for(int i = 0;i<10;i++){
            data.add(rand.nextInt(100));
        }
        System.out.println(data);

        int nThreads = 2;
        Thread[] t = new Thread[nThreads];
        LinkedList<Max> list = new LinkedList<Max>();
        int blockSize = data.size() / nThreads;

        for(int i = 0; i<nThreads ;i++) {
            Max cs = new Max();
            cs.offset = blockSize*i;
            cs.length = blockSize;
            cs.arr = data;
            if (i == nThreads-1)
            {
                cs.length+=data.size() % nThreads;
            }

            list.addLast(cs);
            t[i] = new Thread(cs);
        }

        for (int i=0; i<nThreads;i++)
        {
            t[i].start();
        }

        try {
            for (int i = 0; i < nThreads; i++) {
                //System.out.println("Current Thread: " + Thread.currentThread().getName());
                t[i].join();
            }
        }
        catch (Exception e)
        {

        }

        int max = 0;
        for (int i =0; i< nThreads;i++)
        {
            int res = list.get(i).max;
            if (res>max){
                max=res;
            }
        }
        System.out.println(max);
    }

}
