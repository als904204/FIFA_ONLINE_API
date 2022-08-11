package com.toy.fifa.JavaCodeTest;

import com.toy.fifa.Entity.User;

import javax.persistence.EntityExistsException;
import java.util.HashSet;

public class setTest {


    public static void main(String[] args) {
        HashSet<User> hs1 = new HashSet<>();
        HashSet<String> hs2 = new HashSet<>();

        User user = new User();
        boolean hong = hs1.add(user);

        System.out.println(hs1.contains(user));
        if (hs1.contains(user)) {
            throw new EntityExistsException("예외");
        } else hs1.add(user);

        for (User e : hs1) {
            System.out.println(e + " ");
        }
    }

}
