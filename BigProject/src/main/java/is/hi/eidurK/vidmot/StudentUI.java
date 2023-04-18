package is.hi.eidurK.vidmot;
import edu.princeton.cs.algs4.StdOut;
import is.hi.eidurK.vinnsla.*;
import is.hi.eidurK.vinnsla.UserOrBookDoesNotExistException;

import java.util.Objects;
import java.util.Scanner;
public class StudentUI extends LibraryApplication{
    protected static void studentLoop(){
        Scanner s = new Scanner(System.in);
        String[] operations = {"Search book or omnibus","View my books", "Return book", "Extend book" , "Switch to faculty user", "Quit"};
        Gui.makeNumberedTable(operations);
        System.out.flush();
        try{
            switch(s.nextInt()){
                case 1: bookFound(Objects.requireNonNull(findBook())); break;
                case 2: viewMyBooks(); break;
                case 3: returnBook(); break;
                case 4: extend(); break;
                case 5: Gui.clearScreen(); UserIsFaucultyMember = !UserIsFaucultyMember;break;
                case 6: Gui.clearScreen();System.exit(1);
            }}
        catch(Exception e){
            Gui.clearScreen();
            Gui.printRed("Error... (´･_･`)");
        }
    }
    private static void returnBook() throws UserOrBookDoesNotExistException {
        Scanner s = new Scanner(System.in);
        viewMyBooks();
        System.out.println("Type the name of the book or omnibus you want to return");
        library.returnItem(library.findBorrowableByTitle(s.nextLine()), library.findUserByName(UserName));
    }
    private static void viewMyBooks() throws UserOrBookDoesNotExistException {
        Gui.clearScreen();
        Gui.makeTable(library.lendingsListToStringArray(library.getLendingsOfUser(UserName)));
    }
    private static void bookFound(Borrowable borrowable) throws EmptyAuthorListException {
        Scanner s = new Scanner(System.in);
        Gui.clearScreen();
        System.out.print(borrowable.getTitle() + " by ");
        borrowable.getAuthors().forEach(author -> System.out.print(author.getName() + ", "));
        System.out.println();
        String[] options = {"Borrow " + borrowable.getTitle(), "Quit"};
        Gui.makeNumberedTable(options);
        if (s.nextInt() == 1) {
            try {
                library.borrowBorrowable(borrowable, library.findUserByName(UserName));
                Gui.clearScreen();
                System.out.println(borrowable.getTitle() + " borrowed...");
            } catch (UserOrBookDoesNotExistException e) {
                System.out.println("Error...");
            }
        } else {
            Gui.clearScreen();
        }
    }
    private static void extend() throws UserOrBookDoesNotExistException {
        Scanner s = new Scanner(System.in);
        Gui.clearScreen();
        System.out.println("Please enter the name of the book or omnibus you would like to extend:");
        String bookName = s.nextLine();
        Borrowable borrowable = library.findBorrowableByTitle(bookName);
        System.out.println("Please enter the name of a faculty member:");
        String facultyName = s.nextLine();
        if (library.findUserByName(facultyName) instanceof FacultyMember){
            FacultyMember facultyMember;
            facultyMember = (FacultyMember) library.findUserByName(facultyName);
            library.extendLending(facultyMember, borrowable);
        }
    }
}