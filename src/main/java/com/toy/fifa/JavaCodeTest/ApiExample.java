package com.toy.fifa.JavaCodeTest;

import java.io.*;
import java.util.Arrays;

public class ApiExample {

    public static void main(String[] args) {

    }
}

class Bakery {
    public class Bread{
        void eat() {
            System.out.println("eat Bread");
        }
    }

    public class Cake{
        void eat() {
            System.out.println("eat Cake");
        }
    }

    public Bread getBread() {
        return new Bread();
    }

    public Cake getCake() {
        return new Cake();
    }
}
