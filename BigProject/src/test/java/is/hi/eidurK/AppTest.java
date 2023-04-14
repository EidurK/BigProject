package is.hi.eidurK;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import is.hi.eidurK.vinnsla.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {


    private Student student;
    private Book book;
    private List<Book> books;
    private Lending lending;
    private List<Lending> lendings;
    private User user;
    private List<User> users;
    private List<Author> authors;
    private Author author;
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

    @Test
    public void isListofBooksNotEmpty(){
        system = new LibrarySystem();
        books = new ArrayList<>();
        books.add(book);
        assertFalse(system.listOfBooksIsEmpty(books));
    }

    @Test
    public void isListofLendingsEmpty(){
        system = new LibrarySystem();
        lendings = new ArrayList<>();
        assertTrue(system.listOfLendingsIsEmpty(lendings));
    }

    @Test
    public void isListofLendingsNotEmpty(){
        system = new LibrarySystem();
        lendings = new ArrayList<>();
        lendings.add(lending);
        assertFalse(system.listOfLendingsIsEmpty(lendings));
    }

    @Test
    public void isListofUsersEmpty(){
        system = new LibrarySystem();
        users = new ArrayList<>();
        assertTrue(system.listOfUsersIsEmpty(users));
    }

    @Test
    public void isListofUsersNotEmpty(){
        system = new LibrarySystem();
        users = new ArrayList<>();
        users.add(user);
        assertFalse(system.listOfUsersIsEmpty(users));
    }

    @Test
    public void isListofAuthorsEmpty(){
        system = new LibrarySystem();
        authors = new ArrayList<>();
        assertTrue(system.listOfAuthorsIsEmpty(authors));
    }

    @Test
    public void isListofAuthorsNotEmpty(){
        system = new LibrarySystem();
        authors = new ArrayList<>();
        authors.add(author);
        assertFalse(system.listOfAuthorsIsEmpty(authors));
    }


    }
