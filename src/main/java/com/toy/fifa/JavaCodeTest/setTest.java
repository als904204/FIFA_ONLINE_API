package com.toy.fifa.JavaCodeTest;

import java.util.HashSet;

public class setTest {


    public static void main(String[] args) {
        HashSet<String> hs1 = new HashSet<>();
        HashSet<String> hs2 = new HashSet<>();

        hs1.add("hong");
        hs1.add("kim");
        for (String e : hs1) {
            System.out.println(e + " ");
        }
    }

}
