package org.example;

/*
library system

users
books
admins

functions
 - add book to user / remove from available in library
 - run report showing books taken out
 - run report showing books available

Should run in the console select if admin or user.

 */

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        List<Book> list = library.getStock();
        System.out.println(list.get(0).getNumber());
        System.out.println(list.get(0).getGenre());
        System.out.println(list.get(0).getTitle());
        System.out.println(list.get(0).getPublisher());
    }
}