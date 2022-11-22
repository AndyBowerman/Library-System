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
                }
            } catch(IOException | ParseException e) {
                e.printStackTrace();
            }
            inStock = stock;
        }

        public void addBooks(Book book) {
            stock.add(book);
        }

        public List<String> getStockByTitle() {
            List<String> stockNames = new ArrayList<>();
            for (Book b: stock) {
                stockNames.add(b.getTitle());
            }
            return stockNames;
        }

    public List<String> getInStockByTitle() {
        List<String> stockNames = new ArrayList<>();
        for (Book b: inStock) {
            stockNames.add(b.getTitle());
        }
        return stockNames;
    }

    public List<String> getUsers() {
        List<String> userNames = new ArrayList<>();
        for (User u: users) {
            userNames.add(u.getName());
        }
        return userNames;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> findUser(String userName) {
        return users
            .stream()
            .filter(user -> user.getName().toLowerCase().contains(userName.toLowerCase()))
            .collect(Collectors.toList());
    }

    public List<Book> findBook(String title) {
        return stock
                .stream()
                .filter((book -> book.getTitle().toLowerCase().contains(title.toLowerCase())))
                .collect(Collectors.toList());
    }

    public void loanBook (String userName, String title) {
        try {
            User currentUser = findUser(userName).get(0);
        } catch {
            
        }

        Book currentBook = findBook(title).get(0);

        if(!stock.contains(currentBook)) {
            System.out.println("Book not found");
        } else if(!users.contains(currentUser)) {
            System.out.println("User not found");
        } else if(!inStock.contains(currentBook)) {
            System.out.println("Book unavailable");
        } else {
            currentUser.loanBook(currentBook);
            inStock.remove(inStock.indexOf(currentBook));
            System.out.println(currentUser.name() + " has loaned " + currentBook.getTitle());
        }
    }
}
