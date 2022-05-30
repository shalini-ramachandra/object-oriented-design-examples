package onlinebookreader;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Library {

    Map<UUID, Book> books = new HashMap<>();
    Set<Reader> readers = new HashSet<>();

    public Library(List<Book> books) {
        books.stream().forEach(book -> this.books.put(book.getId(), book));
    }

    public void borrowBook(Reader reader, Book book) {

        if (searchBook(book)) {

            if (book.isAvailable() && !reader.isReadingBook()) {
                // Book should be available and reader should not already be
                // reading a book
                book.setCurrentReader(reader);
                reader.setCurrentBook(book);
                System.out.println("CONGRATULATIONS " + reader + "." +
                        " You have borrowed the book " + book);
            } else if (book.getCurrentReader() != null) {

                // The book has a current reader and hence cannot be borrowed.
                // Give appropriate messages back.

                Reader currentReader = book.getCurrentReader();

                if (currentReader.equals(reader)) {
                    // If the book's current borrower is the same reader, then
                    // just tell them they have the book
                    System.out.println("[WARN] >>> Dear " + reader + " " +
                            "You can't borrow the book " + book + " " +
                            "as you already have it");
                } else {
                    // The book is borrowed by someone else. Hence can't be
                    // borrowed
                    System.out.println("[ERROR] >>> Dear " + reader + " " +
                            "You can't borrow the book " + book + " " +
                            "as someone else has borrowed it");
                }
            } else if (reader.isReadingBook() && !reader.getCurrentBook().equals(book)) {
                // The book is borrowed by someone else. Hence can't be borrowed
                System.out.println("[ERROR] >>> Dear " + reader + " " +
                        "You can't borrow the book " + book + " " +
                        "as you already have another borrowed book. Please " +
                        "return it before borrowing another one");
            }

        } else {
            System.out.println("[ERROR] >>> Book " + book + " isn't" +
                    " currently available in our library. We plan to get it " +
                    "for you soon!");
        }
    }

    public void returnBook(Reader reader) {
        if (reader.isReadingBook()) {
            Book book = reader.getCurrentBook();
            reader.returnBook();
            book.makeAvailable();
        } else {
            System.out.println("[WARN] >>> Dear " + reader + ". You have no " +
                    "book to return!");
        }
    }

    public Set<Book> browseAvailableBooks() {
        return books.values().stream().filter(book -> book.isAvailable()).collect(Collectors.toSet());
    }

    public boolean searchBook(Book book) {
        return books.containsKey(book.getId());
    }

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    public void removeBook(Book book) {
        books.remove(book.getId());
    }

    public void addReader(Reader reader) {
        readers.add(reader);
    }

    public void removeReader(Reader reader) {
        if (reader.isReadingBook()) {
            System.out.println("[ERROR] >>> You can't remove the reader " + reader + ". He/she has an unreturned book!! " + reader.getCurrentBook());
        } else {
            readers.remove(reader);
        }
    }
}
