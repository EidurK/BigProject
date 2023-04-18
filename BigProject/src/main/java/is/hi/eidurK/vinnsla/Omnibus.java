package is.hi.eidurK.vinnsla;
import java.util.ArrayList;
public class Omnibus implements Borrowable{
  private ArrayList<Book> books;
  private String title;
  public Omnibus(String title, ArrayList<Book> books){
    this.title = title;
    this.books = books;
  }
  public String getTitle() {
    return title;
  }
  public ArrayList<Book> getBooks(){
    return books;
  }
  public void borrowItem(LibrarySystem librarySystem, User user) {
    for (Book book : this.getBooks()){
      book.borrowItem(librarySystem,user);
    }
    librarySystem.getLendings().add(new Lending(this, user));
  }
  public void returnItem(LibrarySystem librarySystem, User user) throws UserOrBookDoesNotExistException {
    boolean checkLending = false;
    for (Lending lending : librarySystem.getLendings()){
      if (lending.getBorrowable().getTitle().equals(this.title)){
	checkLending = true;
	librarySystem.getLendings().remove(lending);
	for (Book book : this.getBooks()){
	  book.returnItem(librarySystem, user);
	}
	break;
      }
    } if (!checkLending){
      throw new UserOrBookDoesNotExistException("Lending not found");
    }
  }
  public void extendLending(FacultyMember facultyMember, LibrarySystem librarySystem) throws UserOrBookDoesNotExistException {
    for (User user : librarySystem.getUsers()){
      if (user instanceof FacultyMember){
	for (Lending lending : librarySystem.getLendings()){
	  if (lending.getBorrowable() instanceof Omnibus && lending.getBorrowable() == this){
	    for (Book book : getBooks()){
	      book.extendLending(facultyMember, librarySystem);
	    }
	    lending.setDueDate(lending.getDueDate().plusDays(30));
	  }
	  else throw new UserOrBookDoesNotExistException("Lending not found");
	}
      }
    }
  }
  public ArrayList<Author> getAuthors(){
    ArrayList<Author> array = new ArrayList<>();
    for(Book b: books){
        array.addAll(b.getAuthors());
    }
    return array;
  }
}