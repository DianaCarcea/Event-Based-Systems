package org.example;

import org.example.config.Config;
import org.example.utility.Utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {



    public static void main(String[] args) throws IOException, InterruptedException {


        Utility.createFolder("./generated_sets");
        Utility.createFolder("./generated_sets/publications");
        Utility.createFolder("./generated_sets/subscriptions");

        Publication publication = new Publication();
        Subscription subscription = new Subscription();

        long startTime;
        long endTime;
        long duration;

        File pubFile = Utility.createEmptyFile("./generated_sets/publications/PUBLICATIONS.txt");
        BufferedWriter pubWriter = new BufferedWriter(new FileWriter(pubFile, true));

        startTime = System.nanoTime();

        CountDownLatch pubLatch = new CountDownLatch(Config.NUM_THREADS);
        try (ExecutorService pubExecutor = Executors.newFixedThreadPool(Config.NUM_THREADS)) {

            for (int i = 0; i < Config.NUM_THREADS; i++) {

                int count;

                if (i == Config.NUM_THREADS - 1) {
                    count = Config.CHUNK_SIZE_PUBLICATIONS + Config.REMAINDER_PUBLICATIONS;
                } else {
                    count = Config.CHUNK_SIZE_PUBLICATIONS;
                }

                pubExecutor.submit(() -> {
                    publication.generatePublications(count, pubWriter);
                    pubLatch.countDown();
                });
            }
        }

        pubLatch.await();
        pubWriter.close();

        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Timpul de generare pentru publicații: " + duration / 1_000_000 + " ms");




        File subFile = Utility.createEmptyFile("./generated_sets/subscriptions/SUBSCRIPTIONS.txt");
        BufferedWriter subWriter = new BufferedWriter(new FileWriter(subFile, true));

        startTime = System.nanoTime();

        CountDownLatch subLatch = new CountDownLatch(Config.NUM_THREADS);
        try (ExecutorService subExecutor = Executors.newFixedThreadPool(Config.NUM_THREADS)) {

            for (int i = 0; i < Config.NUM_THREADS; i++) {

                int count;

                if (i == Config.NUM_THREADS - 1) {
                    count = Config.CHUNK_SIZE_SUBSCRIPTIONS + Config.REMAINDER_SUBSCRIPTIONS;
                } else {
                    count = Config.CHUNK_SIZE_SUBSCRIPTIONS;
                }

                subExecutor.submit(() -> {
                    subscription.generateSubscriptions(count, subWriter);
                    subLatch.countDown();
                });
            }
        }

        subLatch.await();
        subWriter.close();

        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Timpul de generare pentru subscripții: " + duration / 1_000_000 + " ms");

    }
}