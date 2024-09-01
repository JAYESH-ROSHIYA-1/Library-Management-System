import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class LibraryTest {

    Library library = new Library("LDCE");

    @Test
    void isValidName(){
        assertDoesNotThrow(()->library.isValidName());

        Library library1 = new Library("LD");
        Exception ex1 = assertThrows(Exception.class, library1::isValidName);
        assertEquals("Library name must be more than 3 letters",ex1.getMessage());

        Library library2 = new Library("");
        Exception ex2 = assertThrows(Exception.class, library2::isValidName);
        assertEquals("Please Enter library name", ex2.getMessage());

    }



    /* --------------------- Test cases for Add Book  -------------------- */
    @Test
    void TheBookDetailsAreNotComplete(){
        Book book1 = new Book("","Vevishal","zaverchand meghani", Year.of(1955));
        Exception ex1 = assertThrows(Exception.class, () -> library.addBook(book1));
        assertEquals("Please fill all the details", ex1.getMessage());
    }

    @Test
    void allDetailsAreCompleted(){
        Book book2 = new Book("9781981876037","Vevishal","zaverchand meghani",Year.of(1955));
        assertDoesNotThrow(() -> library.addBook(book2));
    }

    @Test
    void bookIsAlreadyAdded() throws Exception {
        Book book2 = new Book("9781981876037","Vevishal","zaverchand meghani",Year.of(1955));
        library.addBook(book2);
        Exception ex2 = assertThrows(Exception.class, () -> library.addBook(book2));
        assertEquals("A book has already added",ex2.getMessage());
    }



    /* --------------------- Test cases for borrow Book  -------------------- */
    @Test
    void allDetailsAreCorrect() throws Exception {
        User user = new User(123,"Himanshu");
        Book book = new Book("9781981876037","Vevishal","zaverchand meghani",Year.of(1955));
        library.addBook(book);

        assertDoesNotThrow(() -> library.borrowBook(user,"9781981876037"));
    }

    @Test
    void bookIsAlreadyBorrow() throws Exception {
        User user = new User(123,"Himanshu");
        Book book = new Book("9781981876037","Vevishal","zaverchand meghani",Year.of(1955));
        library.addBook(book);

        Exception ex = assertThrows(Exception.class, () -> library.borrowBook(user,"1234567891011"));
        assertEquals("Book not found", ex.getMessage());
    }



    /* --------------------- Test cases for return Book  -------------------- */
    @Test
    void checkUserIsValidate() throws Exception {
        User user = new User(123,"Himanshu");
        Book book = new Book("9781981876037","Vevishal","zaverchand meghani",Year.of(1955));
        User user2 = new User(456,"Shyam");
        library.addBook(book);
        library.borrowBook(user, book.ISBN);

        Exception ex2 = assertThrows(Exception.class, () -> library.returnBook(user2,book.ISBN));
        assertEquals("User not Found", ex2.getMessage());
    }

    @Test
    void bookIsNotBorrow() throws Exception {
        User user = new User(123,"Himanshu");
        Book book = new Book("9781981876037","Vevishal","zaverchand meghani",Year.of(1955));
        library.addBook(book);
        library.borrowBook(user,book.ISBN);

        Exception ex = assertThrows(Exception.class, () -> library.returnBook(user,"1234567891011"));
        assertEquals("This book is not borrowed", ex.getMessage());
    }

    @Test
    void returnBook() throws Exception {
        User user = new User(123,"Himanshu");
        Book book = new Book("9781981876037","Vevishal","zaverchand meghani",Year.of(1955));
        library.addBook(book);
        library.borrowBook(user,book.ISBN);

        assertDoesNotThrow(() ->  library.returnBook(user,"9781981876037"));
    }



    /* ---------------- Test cases for view available books  ------------------ */
    @Test
    void viewAvailableBooks() throws Exception {
        Book book = new Book("9781981876037","Vevishal","zaverchand meghani",Year.of(1955));
        Book book2 = new Book("9780684839349","Gitanjali","Rabindranath Tagore", Year.of(1910));
        library.addBook(book);
        library.addBook(book2);

        // for comparison purpose
        ArrayList<Book> availableList = new ArrayList<>();
        availableList.add(book);
        availableList.add(book2);
        availableList.sort((x,y) -> (y.ISBN.compareTo(x.ISBN)));

        assertIterableEquals(availableList,library.viewBook());
    }

    @Test
    void viewAvailableBooksWithBorrowSomeBook() throws Exception {
        User user = new User(123,"Himanshu");
        Book book = new Book("9781981876037","Vevishal","zaverchand meghani",Year.of(1955));
        Book book2 = new Book("9780684839349","Gitanjali","Rabindranath Tagore", Year.of(1910));
        library.addBook(book);
        library.addBook(book2);
        library.borrowBook(user,book.ISBN);

        /* ----- for comparison purpose ----- */
        ArrayList<Book> availableList = new ArrayList<>();
        availableList.add(book2);
        availableList.sort((x,y) -> (y.ISBN.compareTo(x.ISBN)));

        assertIterableEquals(availableList,library.viewBook());
    }

    @Test
    void noOneBookIsAvailable(){
        Exception ex = assertThrows(Exception.class, () -> library.viewBook());
        assertEquals("No one book is available", ex.getMessage());
    }

}
