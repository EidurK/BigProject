package is.hi.eidurK;
import is.hi.eidurK.vinnsla.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
/**
 * Unit test for not simple App.
 */
public class AppTest {
  private Student student;
  private Book book;
  private ArrayList<Book> books;
  private Lending lending;
  private List<Lending> lendings;
  private List<User> users;
  private ArrayList<Author> authors;
  private ArrayList<Borrowable> borrowables;
  private Author author;
  private User user;
  private LibrarySystem system;
  @Before
  public void makeStackTestObject()throws EmptyAuthorListException {
    student = new Student("John Doe", true);
    system = new LibrarySystem();
    books = new ArrayList<>();
    lendings = new ArrayList<>();
    authors = new ArrayList<>();
    users = new ArrayList<>();
    borrowables = new ArrayList<>();
    authors.add(new Author("bubbi"));
    book = new Book("book", authors);
    system.setUsers(users);
    system.setBorrowables(borrowables);
    system.setLendings(lendings);
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
  public void isListofBorrowablesEmpty(){
    assertTrue(system.listOfBorrowablesIsEmpty());
  }
  @Test
  public void isListofBooksNotEmpty(){
    books.add(book);
    system.setBorrowables(borrowables);
    for(Book b: books){
      borrowables.add(b);
    }
    assertFalse(system.listOfBorrowablesIsEmpty());
  }
  @Test
  public void isListofLendingsEmpty(){
    assertTrue(system.listOfLendingsIsEmpty());
  }
  @Test
  public void isListofLendingsNotEmpty(){
    lendings.add(lending);
    assertFalse(system.listOfLendingsIsEmpty());
  }
  @Test
  public void isListofUsersEmpty(){
    assertTrue(system.listOfUsersIsEmpty());
  }
  @Test
  public void isListofUsersNotEmpty(){
    users.add(user);
    assertFalse(system.listOfUsersIsEmpty());
  }
  @Test
  public void isListofAuthorsNotEmpty(){
    assertFalse(system.listOfAuthorsIsEmpty(authors));
  }
  @Test
  public void addBooksMethod() throws EmptyAuthorListException {
    authors.add(new Author("john"));
    system.addBookWithTitleAndAuthorlist("book", authors);
    assertFalse(system.listOfBorrowablesIsEmpty());
  }
  @Test
  public void addStudent() throws UserOrBookDoesNotExistException {
    system.addStudentUser("name", false);
    assertFalse(system.listOfUsersIsEmpty());
  }
  @Test
  public void addLendings() throws UserOrBookDoesNotExistException {
    borrowables.add(book);
    users.add(user);
    system.borrowBorrowable(book, user);
    assertFalse(system.getLendings().isEmpty());
  }
  @Test
  public void findUser() throws UserOrBookDoesNotExistException {
    users.add(student);
    assertEquals(system.findUserByName(student.getName()), student);
  }
  @Test
  public void findBook() throws UserOrBookDoesNotExistException, EmptyAuthorListException {
    authors.add(new Author("author"));
    book = new Book("title", authors);
    borrowables.add(book);
    assertEquals(system.findBorrowableByTitle("title") , book);
  }
}
