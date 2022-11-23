package org.example;

/*
library system - methods

    - addUser()
    - getAllStockByTitle()
    - getInStockByTitle()
    - searchByAuthor()
    - searchByGenre()
    - findUser()
    - findBook()
    - isInStock()
    - loanBook()
    - returnBook()
    - getUsersBooks()
    - getLoanCount()
    - searchBookStatus() - checks if the library owns the book and if its on loan
    - userReport() - returns list of users and the books they have on loan
 */

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.addUser(new User("Andy Bowerman"));
        library.addUser(new User("Ben Smith"));
        library.addUser(new User("John Turner"));
        library.addUser(new User("Person 1"));
        library.addUser(new User("Person 2"));
        library.addUser(new User("Person 3"));
        library.addUser(new User("Person 4"));
        library.addUser(new User("Person 5"));

        library.loanBook("person 1", "Data Smart");
        library.loanBook("person 2", "Drunkard's Walk, The");
        library.loanBook("person 3", "Python for Data Analysis");
        library.loanBook("person 4", "Return of the Primitive");
        library.loanBook("person 5", "Complete Mastermind, The");
        library.loanBook("person 1", "Argumentative Indian, The");
        library.loanBook("person 1", "Idea of Justice, The");

        library.userReport();
    }
}