package is.hi.eidurK.vidmot;
import is.hi.eidurK.vinnsla.Book;
import is.hi.eidurK.vinnsla.UserOrBookDoesNotExistException;
import java.util.Scanner;
public class StudentUI extends LibraryApplication{
    protected static void studentLoop(){
        Scanner s = new Scanner(System.in);
        String[] operations = {"Search book","View my books", "Return book", "Switch to faculty user", "Quit"};
        Gui.makeNumberedTable(operations);
        System.out.flush();
        try{
            switch(s.nextInt()){
                case 1: bookFound(findBook()); break;
                case 2: viewMyBooks(); break;
                case 3: returnBook(); break;
                case 4: Gui.clearScreen(); UserIsFaucultyMember = !UserIsFaucultyMember;break;
                case 5: Gui.clearScreen();System.exit(1);
            }}
        catch(Exception e){
            Gui.clearScreen();
            Gui.printRed("Error... (´･_･`)");
        }
    }
    private static void returnBook() throws UserOrBookDoesNotExistException {
        Scanner s = new Scanner(System.in);
        viewMyBooks();
        System.out.println("Type the name of the book you want to return");
        library.returnItem(library.findBorrowableByTitle(s.nextLine()), library.findUserByName(UserName));

    }

    private static void viewMyBooks() throws UserOrBookDoesNotExistException {
        Gui.clearScreen();
        Gui.makeTable(library.lendingsListToStringArray(library.getLendingsOfUser(UserName)));
    }
    private static void bookFound(Book book) {
        Scanner s = new Scanner(System.in);
        Gui.clearScreen();
        System.out.println(book.getTitle() + " by " + getAuthors(book));
        String[] options = {"Borrow " + book.getTitle(), "Quit"};
        Gui.makeNumberedTable(options);
        if (s.nextInt() == 1) {
            try {
                library.borrowBorrowable(book, library.findUserByName(UserName));
                Gui.clearScreen();
                System.out.println(book.getTitle() + " borrowed...");
            } catch (UserOrBookDoesNotExistException e) {
                System.out.println("Error...");
            }
        } else {
            Gui.clearScreen();
        }
    }
}