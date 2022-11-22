package org.example;

/*
library system

add books to users & remove from stock
    - find user by name
    - find book by name or number
list in stock - done
list out on loan - books in the users book array within the users array
return books - add to in stock list
search for books by genre - all and in stock

 */

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        System.out.println(library.getInStockByTitle());

        library.addUser(new User("Andy Bowerman"));
        library.addUser(new User("Ben Smith"));
        System.out.println(library.getUsers());

        System.out.println(library.findUser("bower").get(0));

        System.out.println(library.findBook("data smart").get(0).getTitle());

        library.loanBook("jones", "Drunkard's Walk, The");
    }
}