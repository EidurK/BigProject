package is.hi.eidurK;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import is.hi.eidurK.vinnsla.Book;
import is.hi.eidurK.vinnsla.LibrarySystem;
import is.hi.eidurK.vinnsla.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private Book book;
    private Student student;

    private List<Book> books;

    private LibrarySystem system;

    @Before
    public void makeStackTestObject() {
        student = new Student("John Doe", true);
    }

    @Test
    public void feeIsPaid() {
        student.setFeePaid(true);
        assertTrue(student.isFeePaid());
    }

    @Test
    public void feeIsNotPaid(){
        student.setFeePaid(false);
        assertFalse(student.isFeePaid());
    }

    @Test
    public void isListofBooksEmpty(){
        system = new LibrarySystem();
        books = new ArrayList<>();
        assertTrue(system.listOfBooksIsEmpty(books));
    }



    }
