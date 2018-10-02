package com.company;
public class randomizer {
    public void getRandomized() {
        double d = Math.random();
        System.out.println(d);
        d= d*10000;
        System.out.println(d);
        int n = (int)d;
        System.out.println(n);
    }
}
