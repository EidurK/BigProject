package is.hi.eidurK.vidmot;
import is.hi.eidurK.vinnsla.Author;
import is.hi.eidurK.vinnsla.Book;
import is.hi.eidurK.vinnsla.EmptyAuthorListException;
import is.hi.eidurK.vinnsla.UserOrBookDoesNotExistException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class FacultyUI extends LibraryApplication {
    protected static void facultyLoop() throws EmptyAuthorListException, UserOrBookDoesNotExistException {
        Gui.clearScreen();
        Scanner s = new Scanner(System.in);
        String[] operations = {"Add book", "Remove book","View lendings","Switch to student", "Quit"};
        Gui.makeNumberedTable(operations);
        System.out.flush();
        switch (s.nextInt()){
            case 1: addBook(); break;
            case 2: removeBook(); break;
            case 3: viewLendings();break;
            case 4: Gui.clearScreen();UserIsFaucultyMember = !UserIsFaucultyMember;break;
            case 5: Gui.clearScreen();System.exit(1);break;
            default:
                System.out.println("this is not a valid option");
                break;
        }
    }
    private static void removeBook() throws UserOrBookDoesNotExistException {
        Scanner s = new Scanner(System.in);
        Gui.clearScreen();
        Book book = findBook();
        if(book != null){
            System.out.println("Are you sure you want to remove " + book.getTitle() +" by " + getAuthors(book) + " from library (y/n)");
            String svar = s.nextLine();
            if(svar.equals("y")| svar.equals("Y")) {
                Gui.clearScreen();
                library.removeBookFromLibrary(library.findBookByTitle(book.getTitle()));
            }
            Gui.printRed(book.getTitle() + " removed");
        }
    }
    private static void addBook() throws EmptyAuthorListException{
        Gui.clearScreen();
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome " + UserName + ", please enter the title of the book you wish to add:");
        String titleInput = s.next();
        s.nextLine();
        Gui.clearScreen();
        System.out.println("Please enter the name of the author/s (separated by a comma):");
        List<Author> authors = new ArrayList<>();
        String authorInput = s.nextLine();
        String[] authorNames = authorInput.split(",");
        for (String authorName : authorNames){
            authors.add(new Author(authorName.trim()));
        }
        library.addBookWithTitleAndAuthorlist(titleInput,authors);
        Gui.clearScreen();
        System.out.println("Book successfully added!");
    }
    private static void viewLendings(){
        Gui.clearScreen();
        Scanner s = new Scanner(System.in);
        Gui.makeTable(library.lendingsListToStringArray());
        System.out.println("Type q to quit");
        s.next();
    }
}