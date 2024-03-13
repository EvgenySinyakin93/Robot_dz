package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        Thread[] thread = new Thread[2000];
        for (int i = 0; i < thread.length; i++) {
            thread[i] = new Thread(() ->
                    sumOfR(generateRoute("RLRFR", 100)));
            thread[i].start();
        }
        for (Thread threads : thread) {
            threads.join();
        }
        freqStats(sizeToFreq);
    }

    public static void freqStats(Map<Integer, Integer> sizeToFreq) {
        int maxFreq = 0;
        int freqCount = 0;

        for (int count : sizeToFreq.keySet()) {
            int freq = sizeToFreq.get(count);
            if (freq > maxFreq) {
                maxFreq = freq;
                freqCount = 1;
            } else if (freq == maxFreq) {
                freqCount++;
            }
        }
        System.out.println("Самое частое количество повторений " + maxFreq + " (встретилось " + freqCount + " раз)");
        System.out.println("Другие размеры:");
        for (int count : sizeToFreq.keySet()) {
            int freq = sizeToFreq.get(count);
            if (freq < maxFreq) {
                System.out.println("- " + count + " (" + freq + " раз)");
            }
        }
    }


    private static void sumOfR(String rlrfr) {
        int count = 0;
        for (int i = 0; i < rlrfr.length(); i++) {
            if (rlrfr.charAt(i) == 'R') {
                count++;
            }
            synchronized (sizeToFreq) {
                sizeToFreq.put(count, sizeToFreq.getOrDefault(count, 0) + 1);
            }
        }
    }


        public static String generateRoute (String letters,int length){
            Random random = new Random();
            StringBuilder route = new StringBuilder();
            for (int i = 0; i < length; i++) {
                route.append(letters.charAt(random.nextInt(letters.length())));
            }
            return route.toString();
        }
    }
