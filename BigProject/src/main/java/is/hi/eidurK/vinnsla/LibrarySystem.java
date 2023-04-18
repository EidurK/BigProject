package is.hi.eidurK.vinnsla;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LibrarySystem {

  //private List<Borrowable> books;
  private ArrayList<Borrowable> borrowables;
  private List<Lending> lendings;

  public void setLendings(List<Lending> lendings) {
    this.lendings = lendings;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public List<User> getUsers() {
    return users;
  }

  private List<User> users;



  public LibrarySystem() {
    borrowables = new ArrayList<>();
    lendings = new ArrayList<>();
    users = new ArrayList<>();
  }

  public void addBookWithTitleAndAuthorlist(String title, ArrayList<Author> authors) throws EmptyAuthorListException {
    if (authors.size() == 0) {
      throw new EmptyAuthorListException("List of authors is empty");
    }
    borrowables.add(new Book(title, authors));
  }

  public void addOmnibus(String title, ArrayList<Book> book) throws EmptyAuthorListException{
    for (Book book1 : book){
      if (book1.getAuthors().isEmpty()){
	    throw new EmptyAuthorListException("List of authors is empty");
      }
    }
    borrowables.add(new Omnibus(title, book));
  }

  public void addStudentUser(String name, Boolean feePaid) throws UserOrBookDoesNotExistException {
    users.add(new Student(name, feePaid));
  }

  public void addFacultyMemberUser(String name, String department) throws UserOrBookDoesNotExistException {
    users.add(new FacultyMember(name, department));
  }

  /*public Borrowable findBorrowableByTitle(String title) throws UserOrBookDoesNotExistException {
    if (books.size() == 0) {
    throw new UserOrBookDoesNotExistException("List of books is empty");
    }
    for (Borrowable book : books) {
    if (book.getTitle().equals(title)) {
    return book;
    }
    }
    throw new UserOrBookDoesNotExistException("Borrowable not found");
    }*/

  public Book findBookByTitle(String title) throws UserOrBookDoesNotExistException {
    List<Borrowable> lB =borrowables.stream().filter(p -> {
      return p.getClass().getName().equals("Book");
    }).collect(Collectors.toList());

    for (Borrowable borrowable : lB) {
      Book bok = (Book) borrowable;
      if (bok.getTitle().equals(title)) {
	return bok;
      }
    }
    throw new UserOrBookDoesNotExistException("Borrowable not found");
  }



  public User findUserByName(String name) throws UserOrBookDoesNotExistException {
    for (User user : users) {
      if (user.getName().equals(name)) {
	return user;
      }
    }
    throw new UserOrBookDoesNotExistException("User not found");
  }

  /*public void borrowBorrowable(Borrowable book, User user) throws UserOrBookDoesNotExistException {
    if (books.size() == 0) {
    throw new UserOrBookDoesNotExistException("List of books is empty");
    }
    lendings.add(new Lending(book, user));
    }*/

  public void borrowBorrowable(Borrowable borrowable, User user) throws UserOrBookDoesNotExistException {
    findBookByTitle(borrowable.getTitle()).borrowItem(this, user);
  }


  /*public void extendLending(User user, Borrowable borrowable, LocalDate newDueDate){
    for (Lending l : lendings) {
    if (l.getBorrowable().getTitle().equals(borrowable.getTitle()) && l.getUser().getName().equals(user.getName())) {
    l.setDueDate(newDueDate);
    }
    }
    }*/
  public void extendLending(FacultyMember facultyMember, Borrowable item) throws UserOrBookDoesNotExistException{
    item.borrowItem(this, facultyMember);
  }

  public void returnItem(Borrowable borrowable, User user) throws UserOrBookDoesNotExistException {
    /*if (books.size() == 0) {
      throw new UserOrBookDoesNotExistException("List of books is empty");
      }*/
    lendings.removeIf(l -> l.getBorrowable() == borrowable && l.getUser() == user);
  }

  public List<Lending> getLendings() {
    return lendings;
  }

  public boolean listOfAuthorsIsEmpty(List<Author> authors){
    return authors.size() == 0;
  }

  public boolean listOfBorrowablesIsEmpty(){
    return borrowables.size() == 0;
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
      array[i] = l.getBorrowable().getTitle() + ":  " + l.getUser().getName() + ":  " + l.getDueDate();
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


  public void removeBorrowableFromLibrary(Borrowable b){
    borrowables.remove(b);
  }
  public void setBorrowables(ArrayList<Borrowable> books){
    this.borrowables = books;
  }

}
