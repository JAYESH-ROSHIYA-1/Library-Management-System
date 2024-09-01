import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Year;

public class BookTest {

    @Test
    void verifyBook(){
        Book book = new Book("9780684839349","Gitanjali","Rabindranath Tagore", Year.of(1910));
        assertEquals("9780684839349",book.ISBN);
        assertEquals("Gitanjali",book.title);
        assertEquals("Rabindranath Tagore",book.author);
        assertEquals(Year.of(1910),book.publicationYear);
    }

    @Test
    void bookDetailsWithNullAndEmpty(){
        Book book1 = new Book("","Gitanjali","Rabindranath Tagore",Year.of(1910));
        assertFalse(book1.isValid());

        Book book2 = new Book("9780684839349","","Rabindranath Tagore",Year.of(1910));
        assertFalse(book2.isValid());

        Book book3 = new Book("9780684839349","Gitanjali","",Year.of(1910));
        assertFalse(book3.isValid());

        Book book4 = new Book("9780684839349","Gitanjali","Rabindranath Tagore",null);
        assertFalse(book4.isValid());

        Book book5 = new Book("9780684839349","Gitanjali","Rabindranath Tagore", Year.of(1910));
        assertTrue(book5.isValid());
    }

}
