package com.jexp3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class MainSeminar {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
//        CounterN cc;
        try (CounterN c = new CounterN()) {
            c.add();
        }
        CounterN c2 = new CounterN();
        c2.close();
        c2.add();

//        try {
//            Counter c = new Counter();
//            c.add();
//            System.out.println(c.getNumberCreation());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

//        try {
//            int n = 1;
//            int f = Factorial.getFactorial(n);
//            System.out.println("Factorial " + n + " == " + f);
//        } catch (FactorialException e) {
//            System.out.println(e.getMessage());
//            System.out.println(e.getNumber());
//        }
    }

    public static class FactorialException extends Exception implements Closeable {
        private int number;
        public int getNumber() {
            return number;
        }
        public FactorialException(String message, int number) {
            super(message);
            this.number = number;
        }

        @Override
        public void close() throws IOException {

        }
    }

    public static class Factorial {
        public static int getFactorial(int num) throws FactorialException {
            int result = 1;
            if (num < 1) throw new FactorialException("Number less then 1", num);
            for (int i = 1; i <= num; i++) {
                result *=i;
            }
            return result;
        }
    }



    public void rwLine(Path pathRead, Path pathWrite) throws IOException {
        try (BufferedReader in = Files.newBufferedReader(pathRead); BufferedWriter out = Files.newBufferedWriter(pathWrite);) {
            out.write(in.readLine());
        }
    }

    public static class Counter extends Exception {
        private static int grow;
        private static int numberCreation;

        public void add() throws Exception {
            grow++;
            this.numberCreation = getGrow(grow);
        }

        private int getGrow(int number) throws Exception {
            if (number != grow) throw new Exception("Counter doesn't equal of self");
            return grow;
        }

        public int getNumberCreation() {
            return numberCreation;
        }
    }

    public static class CounterN implements Closeable {
        private int count = 0;
        private boolean close = false;

        public int add() throws IOException {
            if (close) throw new IOException("Counter is closed.");
            count++;
            return count;
        }


        @Override
        public void close() throws IOException {
            System.out.println("Close counter.");
            this.close = true;
        }
    }
}