package org.example;

/*
library system

check who has a book on loan

reports
    - all books - done
    - all in stock - done
    - all on loan
 */

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.addUser(new User("Andy Bowerman"));
        library.addUser(new User("Ben Smith"));
        library.addUser(new User("John Turner"));

        library.loanBook("bowerman", "Fundamentals of Wavelets");
        library.loanBook("bowerman", "data smart");
        library.isInStock("data smart");
        library.returnBook("bowerman", "Data Smart");
        library.getUsersBooks("bowerman");
        library.isInStock("data smart");
    }
}