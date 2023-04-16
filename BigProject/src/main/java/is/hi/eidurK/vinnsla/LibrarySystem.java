package is.hi.eidurK.vinnsla;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {

  private List<Book> books;
  private List<Lending> lendings;

  public void setLendings(List<Lending> lendings) {
    this.lendings = lendings;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  private List<User> users;



  public LibrarySystem() {
    books = new ArrayList<>();
    lendings = new ArrayList<>();
    users = new ArrayList<>();
  }

  public void addBookWithTitleAndAuthorlist(String title, List<Author> authors) throws EmptyAuthorListException {
    if (authors.size() == 0) {
      throw new EmptyAuthorListException("List of authors is empty");
    }
    books.add(new Book(title, authors));
  }

  public void addStudentUser(String name, Boolean feePaid) throws UserOrBookDoesNotExistException {
    users.add(new Student(name, feePaid));
  }

  public void addFacultyMemberUser(String name, String department) throws UserOrBookDoesNotExistException {
    users.add(new FacultyMember(name, department));
  }

  public Book findBookByTitle(String title) throws UserOrBookDoesNotExistException {
    if (books.size() == 0) {
      throw new UserOrBookDoesNotExistException("List of books is empty");
    }
    for (Book book : books) {
      if (book.getTitle().equals(title)) {
        return book;
      }
    }
    throw new UserOrBookDoesNotExistException("Book not found");
  }

  public User findUserByName(String name) throws UserOrBookDoesNotExistException {
    for (User user : users) {
      if (user.getName().equals(name)) {
        return user;
      }
    }
    throw new UserOrBookDoesNotExistException("User not found");
  }

  public void borrowBook(Book book, User user) throws UserOrBookDoesNotExistException {
    if (books.size() == 0) {
      throw new UserOrBookDoesNotExistException("List of books is empty");
    }
    lendings.add(new Lending(book, user));
  }

  public void extendLending(User user, Book book, LocalDate newDueDate){
    for (Lending l : lendings) {
      if (l.getBook().getTitle().equals(book.getTitle()) && l.getUser().getName().equals(user.getName())) {
        l.setDueDate(newDueDate);
      }
    }
  }

  public void returnBook(Book book, User user) throws UserOrBookDoesNotExistException {
    if (books.size() == 0) {
      throw new UserOrBookDoesNotExistException("List of books is empty");
    }
    lendings.removeIf(l -> l.getBook() == book && l.getUser() == user);
  }

  public List<Lending> getLendings() {
    return lendings;
  }

  public boolean listOfAuthorsIsEmpty(List<Author> authors){
    return authors.size() == 0;
  }

  public boolean listOfBooksIsEmpty(){
    return books.size() == 0;
  }

  public boolean listOfLendingsIsEmpty(){
    return lendings.size() == 0;
  }

  public boolean listOfUsersIsEmpty(){
    return users.size() == 0;
  }
  public String[] lendingsListToStringArray(List<Lending> lendings){
    String[] array = new String[lendings.size()];
    for(int i = 0; i < array.length; i++){
      Lending l = lendings.get(i);
      array[i] = l.getBook().getTitle() + ":  " + l.getUser().getName() + ":  " + l.getDueDate();
    }
    return array;
  }

  public String[] lendingsListToStringArray(){
    return lendingsListToStringArray(lendings);
  }
  public List<Lending> getLendingsOfUser(String userName) throws UserOrBookDoesNotExistException{
    User user = findUserByName(userName);
    List<Lending> lendingsOfUser = new ArrayList<>();
    for(Lending l: lendings){
      if(l.getUser().equals(user)){
        lendingsOfUser.add(l);
      }
    }
    return lendingsOfUser;
  }





  public void removeBookFromLibrary(Book b){
    books.remove(b);
  }
  public void setBooks(List<Book> books){
    this.books = books;
  }

}
