package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private List<Book> stock = new ArrayList<>();
    private List<Book> inStock = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private JSONParser parser = new JSONParser();

    public Library() {
        try {
            JSONArray arr = (JSONArray) parser.parse(new FileReader("src/main/resources/stock.json"));
            for (Object o : arr) {
                JSONObject obj = (JSONObject) o;
                Book book = new Book((String) obj.get("Number"), (String) obj.get("Title"), (String) obj.get("Author"), (String) obj.get("Genre"), (String) obj.get("SubGenre"), (String) obj.get("Publisher"));
                stock.add(book);
                inStock.add(book);
            }
        } catch(IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void getUsers() {
        for (User u: users) {
            System.out.println(u.getName());
        }
    }

    public void addUser(User user) {
        users.add(user);
    }

    // All stock
    public void getAllStockByTitle() {
        for (Book b: stock) {
            System.out.println(b.getTitle());
        }
    }

    // Stock not on loan
    public void getInStockByTitle() {
        for (Book b: inStock) {
            System.out.println(b.getTitle());
        }
    }

    // Search by genre
    public void searchByGenre(String genre) {
        List<Book> booksInGenre = stock
                .stream()
                .filter(book -> book.getGenre().toLowerCase().contains(genre.toLowerCase()))
                .collect(Collectors.toList());

        for (Book b: booksInGenre) {
            System.out.println(b.getTitle());
        }
    }

    // Search by author
    public void searchByAuthor(String name) {
        List<Book> booksByAuthor = stock
                .stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());

        for (Book b: booksByAuthor) {
            System.out.println(b.getTitle());
        }
    }

    // Search by username
    public List<User> findUser(String userName) {
        return users
                .stream()
                .filter(member -> member.getName().toLowerCase().contains(userName.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Search by book title
    public List<Book> findBook(String title) {
        return stock
                .stream()
                .filter((book -> book.getTitle().toLowerCase().contains(title.toLowerCase())))
                .collect(Collectors.toList());
    }

    // Check if in stock
    public void isInStock (String title) {
        List<Book> bookInStock = inStock
                .stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println(bookInStock.size() > 0);
    }

    // Loan a book
    public void loanBook (String userName, String title) {
        List<User> currentUser = findUser(userName);
        List<Book> currentBook = findBook(title);

        if(currentUser.size() == 0) {
            System.out.println("User not found");
        } else if(currentBook.size() == 0) {
            System.out.println("Book not found");
        } else {
            if(inStock.contains(currentBook.get(0))) {
                currentUser.get(0).loanBook(currentBook.get(0));
                inStock.remove(inStock.indexOf(currentBook.get(0)));
                System.out.println(currentUser.get(0).getName() + " has loaned " + currentBook.get(0).getTitle());
            } else {
                System.out.println("Book out on loan");
            }
        }
    }

    // Return a book
    public void returnBook (String userName, String title) {
        List<User> currentUser = findUser(userName);
        List<Book> currentBook = findBook(title);

        if(currentUser.size() == 0) {
            System.out.println("User not found");
        } else if(currentBook.size() == 0) {
            System.out.println("Book not found");
        } else {
            List<String> bookList = currentUser.get(0).getBooksOnLoan();
            for (int i = 0; i < bookList.size(); i++) {
                if(bookList.get(i).toLowerCase().equals(title.toLowerCase())) {
                    currentUser.get(0).returnBook(currentBook.get(0));
                    inStock.add(currentBook.get(0));
                    System.out.println(currentUser.get(0).getName() + " returned " + currentBook.get(0).getTitle());
                }
            }
        }
    }

    // Show users current books
    public void getUsersBooks (String userName) {
        List<User> currentUser = findUser(userName);
        if(currentUser.size() > 0) {
            System.out.println(currentUser.get(0).getBooksOnLoan());
        } else {
            System.out.println("User not found");
        }
    }
}
