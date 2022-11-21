package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.Closeable;

public class Library {
        private List<Book> stock = new ArrayList<>();
        JSONParser parser = new JSONParser();

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
        }

        public void addBooks(Book book) {
            stock.add(book);
        }

        public List<Book> getStock() {
            return stock;
        }
    }
