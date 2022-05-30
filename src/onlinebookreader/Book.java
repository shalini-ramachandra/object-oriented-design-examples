package onlinebookreader;

import java.util.UUID;

public class Book {

    private final UUID id;
    private String name;
    private int numPages;
    private Reader currentReader;
    private int currentPage;

    public Book(String name, int numPages) {
        id = UUID.randomUUID();
        this.name = name;
        this.numPages = numPages;
        currentPage = 1;
    }

    public boolean isAvailable() {
        return currentReader == null;
    }

    public void makeAvailable() {
        setCurrentReader(null);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public Reader getCurrentReader() {
        return currentReader;
    }

    public void setCurrentReader(Reader reader) {
        currentReader = reader;
    }

    public int turnPageForward() {
        if (currentPage == numPages) {
            System.out.println("CONGRATULATIONS!! You've finished this book");
        } else {
            currentPage++;
        }

        return currentPage;
    }

    public int turnPageBackward() {
        if (currentPage == 1) {
            System.out.println("You've not yet started reading the book. Do you want to start now?");
        } else {
            currentPage--;
        }

        return currentPage;
    }

    public String toString() {
        return "\"" + this.getName() + "\"";
    }
}
