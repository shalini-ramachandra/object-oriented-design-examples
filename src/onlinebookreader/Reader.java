package onlinebookreader;

import java.util.UUID;

public class Reader {

    private final UUID id;
    private String name;
    private Book currentBook;

    public Reader(String name) {
        this.name = name;
        id = UUID.randomUUID();
    }

    public boolean isReadingBook() {
        return currentBook != null;
    }

    public boolean isReadingBook(Book book) {
        return isReadingBook() && currentBook == book;
    }

    public void returnBook() {
        setCurrentBook(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book getCurrentBook() {
        return currentBook;
    }

    public void setCurrentBook(Book book) {
        currentBook = book;
    }

    public String toString() {
        return "\"" + this.getName() + "\"";
    }
}
