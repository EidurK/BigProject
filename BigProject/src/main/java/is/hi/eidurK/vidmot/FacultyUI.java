package is.hi.eidurK.vidmot;
import is.hi.eidurK.vinnsla.*;
import java.util.ArrayList;
import java.util.Scanner;
public class FacultyUI extends LibraryApplication {
  protected static void facultyLoop() throws EmptyAuthorListException, UserOrBookDoesNotExistException {
    Gui.clearScreen();
    Scanner s = new Scanner(System.in);
    String[] operations = {"Add book", "Remove book", "Add Omnibus","View lendings","Switch to student", "Quit"};
    Gui.makeNumberedTable(operations);
    System.out.flush();
    switch (s.nextInt()){
      case 1: addBook(); break;
      case 2: removeItem(); break;
      case 3: addOmnius(); break;
      case 4: viewLendings();break;
      case 5: Gui.clearScreen();UserIsFaucultyMember = !UserIsFaucultyMember;break;
      case 6: Gui.clearScreen();System.exit(1);break;
      default:
	      System.out.println("this is not a valid option");
	      break;
    }
  }
  private static void removeItem() throws UserOrBookDoesNotExistException {
    Scanner s = new Scanner(System.in);
    Gui.clearScreen();
    Borrowable borrowable = findBook();
    if(borrowable != null){
      System.out.println("Are you sure you want to remove " + borrowable.getTitle() + " from library (y/n)");
      String svar = s.nextLine();
      if(svar.equals("y")| svar.equals("Y")) {
	Gui.clearScreen();
	library.removeBorrowableFromLibrary(library.findBorrowableByTitle(borrowable.getTitle()));
      }
      Gui.printRed(borrowable.getTitle() + " removed");
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
    ArrayList<Author> authors = new ArrayList<>();
    String authorInput = s.nextLine();
    String[] authorNames = authorInput.split(",");
    for (String authorName : authorNames){
      authors.add(new Author(authorName.trim()));
    }
    library.addBookWithTitleAndAuthorlist(titleInput,authors);
    Gui.clearScreen();
    System.out.println("Book successfully added!");
  }
  private static void addOmnius() throws EmptyAuthorListException{
    Gui.clearScreen();
    Scanner s = new Scanner(System.in);
    System.out.println("Welcome " + UserName + ", please enter the title of the omnibus you wish to add:");
    String titleInput = s.nextLine();
    Gui.clearScreen();
    ArrayList<Book> books = new ArrayList<>();
    do {
      Gui.clearScreen();
      Gui.makeBookTable(books);
      System.out.println("Please enter the name of a book you want to add to the omnibus or type $Exit to exit:");
      String nameOfBook = s.nextLine();
      try {
        Book b = (Book) library.findBorrowableByTitle(nameOfBook);
        books.add(b);
        Gui.clearScreen();
        Gui.makeBookTable(books);
        Gui.printGreen("Book successfully added...");
        System.out.println("Continue? (y/n)");
      } catch (UserOrBookDoesNotExistException e) {
        Gui.clearScreen();
        Gui.makeBookTable(books);
        Gui.printRed("This book does not exist...");
        System.out.println("Continue? (y/n)");
      }
    } while (!s.nextLine().equals("n"));
    library.addOmnibus(titleInput,books);
    Gui.clearScreen();
Gui.printGreen("Omnibus successfully added!");
  }
  private static void viewLendings(){
    Gui.clearScreen();
    Scanner s = new Scanner(System.in);
    Gui.makeTable(library.lendingsListToStringArray());
    System.out.println("Type q to quit");
    s.next();
  }
}