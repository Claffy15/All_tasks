import java.util.ArrayList;

public class igra_mezdu_potokami {

    static ArrayList<String> array = new ArrayList<String>();
    static int total;

    static class Count extends Thread
    {
        public void run() {
            while (total<20) {
                synchronized (array) {
                    if (array.size()<20) {
                        array.add(this.getName());
                        total += 1;
                    }
                }
            }
        }
    }

    public static Thread[] createThreads(int nThreads, Thread[] t){
        for (int i =0; i < nThreads; i++){
            t[i] = new Count();
        }
        return t;
    }

    public static void main(String[] args) throws InterruptedException {
        int nThreads = 5;
        Thread[] t = new Thread[nThreads];

        t = createThreads(nThreads, t);;
        for (int i=0; i<nThreads;i++)
        {
            t[i].start();
        }

        for (int i=0; i<nThreads;i++)
        {
            try{
                t[i].join();
            }catch (Exception e){}
        }
        System.out.println(array + " " + array.size());
    }
}

