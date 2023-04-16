package is.hi.eidurK.vidmot;
import edu.princeton.cs.algs4.In;
import is.hi.eidurK.vinnsla.*;
import is.hi.eidurK.vinnsla.Lending;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class LibraryApplication {
  private static LibrarySystem library;
  private static String UserName;
  private static boolean UserIsFaucultyMember;
  private static void addFewBooks()throws EmptyAuthorListException, UserOrBookDoesNotExistException {
    In in = new In("100Books.csv");
    while (in.hasNextLine()){
      List<Author> authors = new ArrayList<>();
      String[] bookNamesAndAuthors = in.readLine().split(";");
      String[] AllAuthorsOfABook = bookNamesAndAuthors[1].split(",");
      for(int i = 0; i < AllAuthorsOfABook.length; i++){
        authors.add(new Author(AllAuthorsOfABook[i]));
      }
      library.addBookWithTitleAndAuthorlist(bookNamesAndAuthors[0], authors);
    }
  }
  private static void inputUserName(){
    Scanner s = new Scanner(System.in);
    Tables.clearScreen();
    System.out.println("What is your name?");
    UserName = s.nextLine();
    Tables.clearScreen();
    System.out.println("Please specify whether you are a faculty member or a student (f/s):");
    UserIsFaucultyMember = s.next().equals("f");
    Tables.clearScreen();
  }
  private static void mainLoop()throws EmptyAuthorListException, UserOrBookDoesNotExistException {
    if(UserName == null){
      inputUserName();
    }
    if(UserIsFaucultyMember){
      try {
        library.findUserByName(UserName);
      }
      catch (UserOrBookDoesNotExistException e){
        FacultyMember facultyMember = new FacultyMember(UserName ,"Upper Management");
        library.addFacultyMemberUser(facultyMember.getName(), facultyMember.getDepartment());
      }
      facultyLoop();
    }
    else {
      try {
        library.findUserByName(UserName);
      }
      catch (UserOrBookDoesNotExistException e){
        Student  student = new Student(UserName, false);
        library.addStudentUser(student.getName(), student.isFeePaid());
      }
      studentLoop();
    }
  }
  private static void facultyLoop() throws EmptyAuthorListException, UserOrBookDoesNotExistException {
    Scanner s = new Scanner(System.in);
    String[] operations = {"1. Add book", "2. Remove book","3. View lendings","4. Switch to student", "5. Quit"};
    Tables.makeTable(operations);
    System.out.flush();
    switch (s.nextInt()){
      case 1: addBook(); break;
      case 2: removeBook(); break;
      case 3: viewLendings();break;
      case 4: Tables.clearScreen();UserIsFaucultyMember = !UserIsFaucultyMember;break;
      case 5: Tables.clearScreen();System.exit(1);break;
      default:
        System.out.println("this is not a valid option");
        break;
    }
  }
  private static void removeBook() throws UserOrBookDoesNotExistException {
    Scanner s = new Scanner(System.in);
    Tables.clearScreen();
    Book book = findBook();
    if(book != null){
      System.out.println("Are you sure you want to remove " + book.getTitle() + " from library (y/n)");
      String svar = s.nextLine();
      if(svar.equals("y")| svar.equals("Y")) {
        library.removeBookFromLibrary(library.findBookByTitle(book.getTitle()));
      }
    }
  }
  private static void viewLendings(){
    Tables.clearScreen();
    Scanner s = new Scanner(System.in);
    Tables.makeTable(library.getLendingsStringArray());
    System.out.println("type q to quit");
    s.next();
  }
  private static void addBook() throws EmptyAuthorListException{
    Tables.clearScreen();
    Scanner s = new Scanner(System.in);
    System.out.println("Welcome " + UserName + ", please enter the title of the book you wish to add:");
    String titleInput = s.next();
    s.nextLine();
    Tables.clearScreen();
    System.out.println("Please enter the name of the author/s (separated by a comma):");
    List<Author> authors = new ArrayList<>();
    String authorInput = s.nextLine();
    String[] authorNames = authorInput.split(",");
    for (String authorName : authorNames){
      authors.add(new Author(authorName.trim()));
    }
    library.addBookWithTitleAndAuthorlist(titleInput,authors);
    Tables.clearScreen();
    System.out.println("Book successfully added!");
  }
  private static void studentLoop() throws UserOrBookDoesNotExistException{
    Scanner s = new Scanner(System.in);
    String[] operations = {"1. Search book", "2. Switch to faculty user", "3. Quit"};
    Tables.makeTable(operations);
    System.out.flush();
    try{
      switch(s.nextInt()){
        case 1: bookFound(findBook()); break;
        case 2: Tables.clearScreen(); UserIsFaucultyMember = !UserIsFaucultyMember;break;
        case 3: Tables.clearScreen();System.exit(1);
      }}
    catch(Exception e){
      Tables.clearScreen();
      System.out.println("Book was not found (´･_･`)");
    }
  }
  private static Book findBook() throws UserOrBookDoesNotExistException{
    Scanner s = new Scanner(System.in);
    Tables.clearScreen();
    System.out.println("Enter the name of the book");
    try {
      return library.findBookByTitle(s.nextLine());
    } catch (UserOrBookDoesNotExistException |  NoSuchElementException e){
      Tables.clearScreen();
      System.out.println("Book was not found (´･_･`)");
    }
    return null;
  }
  private static void bookFound(Book book) throws UserOrBookDoesNotExistException{
    Scanner s = new Scanner(System.in);
    Tables.clearScreen();
    StringBuilder SB = new StringBuilder();
    for(Author a: book.getAuthors()){
      SB.append(a.getName());
      SB.append(", ");
    }
    System.out.println(book.getTitle() + " by " + SB.toString());
    String[] options = {"1. Borrow " + book.getTitle(), "2. Quit"};
    Tables.makeTable(options);
    if (s.nextInt() == 1) {
      try {
        library.borrowBook(book, library.findUserByName(UserName));
        Tables.clearScreen();
        System.out.println(book.getTitle() + " borrowed...");
      } catch (UserOrBookDoesNotExistException e) {
        System.out.println("Error...");
      }
    } else {
      Tables.clearScreen();
    }
  }
  public static void main(String[] args) throws EmptyAuthorListException, UserOrBookDoesNotExistException {
    library = new LibrarySystem();
    addFewBooks();
    while (true){
      mainLoop();
    }
  }
}