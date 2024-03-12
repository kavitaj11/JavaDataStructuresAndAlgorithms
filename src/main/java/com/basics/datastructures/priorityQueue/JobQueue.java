package com.basics.datastructures.priorityQueue;//package com.basics.datastructures;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobsNaive() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        long[] nextFreeTime = new long[numWorkers];
        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            int bestWorker = 0;
            for (int j = 0; j < numWorkers; ++j) {
                if (nextFreeTime[j] < nextFreeTime[bestWorker])
                    bestWorker = j;
            }
            assignedWorker[i] = bestWorker;
            startTime[i] = nextFreeTime[bestWorker];
            nextFreeTime[bestWorker] += duration;
        }
    }

    /**
     * Key Idea:
     * Process each job by the first free thread.
     * Maintain: a Priority Queue of threads,
     *           sorted by its next free time;
     *           if same free time, order by id.
     * Pop out the first available thread.
     * Record assigned worker and start_time for job i.
     * Update the thread's next free time and push back
     */
    private void assignJobs() {
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        // Build a PQ of m Workers sorted by free time
        PriorityQueue<Worker> pq = new PriorityQueue<Worker>(numWorkers,
                new Comparator<Worker>(){
                    @Override
                    public int compare (Worker w1, Worker w2) {
                        return w1.nextFreeTime == w2.nextFreeTime ? w1.id - w2.id :
                                (int) (w1.nextFreeTime - w2.nextFreeTime);
                    }
                });
        // Push all new threads with initial free time 0
        for (int i = 0; i < numWorkers; i++)
            pq.offer(new Worker(i));
        // Process each job by the 1st free thread
        for (int i = 0; i < jobs.length; i++) {
            Worker freeThread = pq.poll();
            // Record job i's assigned worker and start_time
            assignedWorker[i] = freeThread.id;
            startTime[i] = freeThread.nextFreeTime;
            // Update next free time and offer back
            freeThread.nextFreeTime += jobs[i];
            pq.offer(freeThread);
            // This thread will be sorted again according to
            // its next free time, by next job to be processed
        }
    }
    private static class Worker {
        int id;
        long nextFreeTime;
        public Worker (int id) {
            this.id = id;
            nextFreeTime = 0;
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
