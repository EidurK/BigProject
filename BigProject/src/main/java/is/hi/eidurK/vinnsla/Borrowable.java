package is.hi.eidurK.vinnsla;
import java.util.ArrayList;

public interface Borrowable {

    void borrowItem(LibrarySystem librarySystem, User user);

    void returnItem(LibrarySystem librarySystem, User user)throws UserOrBookDoesNotExistException;

    ArrayList<Author> getAuthors()throws EmptyAuthorListException;

    

    void extendLending(FacultyMember facultyMember, LibrarySystem librarySystem) throws UserOrBookDoesNotExistException;

    String getTitle();
}
