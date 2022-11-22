package org.example;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<Book> booksOnLoan = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<String> getBooksOnLoan() {
        List<String> bookTitles = new ArrayList<>();
        for (Book b: booksOnLoan) {
            bookTitles.add(b.getTitle());
        }
        return bookTitles;
    }

    public void loanBook(Book book) {
        booksOnLoan.add(book);
    }
}
