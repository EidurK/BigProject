package is.hi.eidurK.vinnsla;
import java.util.ArrayList;
public class Book implements Borrowable{
  private String title;
  private ArrayList<Author> authors;
  public Book(String title,ArrayList<Author> authors) throws EmptyAuthorListException {
    if (authors.size() == 0){
      throw new EmptyAuthorListException("List of authors is empty");
    }
    this.authors = authors;
    this.title = title;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public ArrayList<Author> getAuthors() {
    return authors;
  }
  public void setAuthors(ArrayList<Author> authors) throws EmptyAuthorListException {
    if (authors.size() == 0){
      throw new EmptyAuthorListException("List of authors is empty");
    }
    this.authors = authors;
  }
  public void borrowItem(LibrarySystem librarySystem, User user){
    librarySystem.getLendings().add(new Lending(this, user));
  }
  public void returnItem(LibrarySystem librarySystem, User user) throws UserOrBookDoesNotExistException{
    for (Lending lending : librarySystem.getLendings()){
      if (lending.getBorrowable().getTitle().equals(this.getTitle())){
	librarySystem.getLendings().remove(lending);
	break;
      }
      else {
	throw new UserOrBookDoesNotExistException("Lending does not exist");
      }
    }
  }
  public void extendLending(FacultyMember facultyMember, LibrarySystem librarySystem) throws UserOrBookDoesNotExistException{
    for (User user : librarySystem.getUsers()){
      if (user instanceof FacultyMember){
	if (user.getName().equals(facultyMember.getName())){
	  for (Lending lending : librarySystem.getLendings()){
	    if (lending.getBorrowable().getTitle().equals(this.getTitle())){
	      lending.setDueDate(lending.getDueDate().plusDays(30));
	    }
	    else throw new UserOrBookDoesNotExistException("Lending not found");
	  }
	}
      }
    }
  }
}
