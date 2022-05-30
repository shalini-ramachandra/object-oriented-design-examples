package onlinebookreader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookReaderApplication {

    public static void main(String[] args) {

        List<Book> books = new ArrayList<>();
        books.addAll(Arrays.asList(
                new Book("Twinkle Star", 50),
                new Book("Black Sheep", 60),
                new Book("Johnny Johnny", 40)
        ));

        List<Reader> readers = Arrays.asList(
                new Reader("Abby"),
                new Reader("Barbie"),
                new Reader("Cathy"),
                new Reader("Dolly")
        );

        Book hickoryDickoryBook = new Book("Hickory Dickory", 80);

        Library library = new Library(books);

        // Browse books
        System.out.println("All available books: " + Arrays.toString(library.browseAvailableBooks().toArray()));

        // Borrow book
        library.borrowBook(readers.get(0), books.get(0));

        // Try to borrow the same book again
        library.borrowBook(readers.get(0), books.get(0));

        // Try to borrow a book which is borrowed by another user
        library.borrowBook(readers.get(1), books.get(0));

        // Try to borrow a 2nd book
        library.borrowBook(readers.get(0), books.get(1));

        // Return and borrow
        library.returnBook(readers.get(0));
        library.borrowBook(readers.get(0), books.get(1));

        // Try to borrow a non-existing book
        library.borrowBook(readers.get(0), hickoryDickoryBook);

        // Nothing to return
        library.returnBook(readers.get(1));

        // Browse books
        System.out.println("All available books: " + Arrays.toString(library.browseAvailableBooks().toArray()));

        // Search a book that is existing
        System.out.println("Library has book " + books.get(0) + "? " + library.searchBook(books.get(0)));

        // Search a book that is not existing
        System.out.println("Library has book " + hickoryDickoryBook + "? " + library.searchBook(hickoryDickoryBook));
    }
}
