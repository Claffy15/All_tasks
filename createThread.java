public class createThread {
    static class Print extends Thread
    {
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static Thread[] createThreads(int nThreads, Thread[] t){
        if (nThreads>=0) {
            Print cs = new Print();
            t[nThreads] = new Thread(cs);
            t = createThreads(nThreads-1, t);
        }
        return t;
    }

    public static void main(String[] args) throws InterruptedException {
        int nThreads = 5;
        Thread[] t = new Thread[nThreads];

        t = createThreads(nThreads-1, t);

        for (int i=0; i<nThreads;i++)
        {
            t[i].start();
            t[i].join();
        }
    }
}
