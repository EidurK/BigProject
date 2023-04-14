package is.hi.eidurK.vidmot;

import edu.princeton.cs.algs4.In;
import is.hi.eidurK.vinnsla.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LibraryApplication {
  private static LibrarySystem library = new LibrarySystem();
  private static String UserName;
  private static boolean UserIsFaucultyMember;

  //ANSI töflu strengir
  private static String hLine = "\033[2G+-------------------+";
  private static String vLine = "\033[2G|\033[22G|";
  private static String down = "\033[1B";
  private static String writingPosition = "\033[4G";
  private static String newLine = down + vLine + writingPosition;
  private static void addFewBooks()throws EmptyAuthorListException, UserOrBookDoesNotExistException {

    In in = new In("100Books.csv");
    while (in.hasNextLine()){
      List<Author> authors = new ArrayList<>();

      String bookNamesAndAuthors[] = in.readLine().split(";");
      String[] AllAuthorsOfABook = bookNamesAndAuthors[1].split(",");

      for(int i = 0; i < AllAuthorsOfABook.length; i++){
	authors.add(new Author(AllAuthorsOfABook[i]));
      }
      library.addBookWithTitleAndAuthorlist(bookNamesAndAuthors[0], authors);
    }
  }

  private static void inputUserName(){
    Scanner s = new Scanner(System.in);
    clearScreen();
    System.out.println("What is your name?");
    UserName = s.nextLine();
    clearScreen();
    System.out.println("Please specify whether you are a faculty member or a student (f/s):");
    UserIsFaucultyMember = s.next().equals("f");
    clearScreen();

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

  private static void facultyLoop() throws EmptyAuthorListException{
    Scanner s = new Scanner(System.in);
    // teiknar upp töflu
    String[] operations = {"1. Add book", "2. Remove book", "3. Quit"};
    makeTable(operations);
    System.out.flush();

    switch (s.nextInt()){
      case 1: addBook(); break;
      case 2: break;
      case 3: clearScreen();System.exit(1);break;
      default:
	      System.out.println("this is not a valid option");
	      break;

    }
  }
  private static void addBook() throws EmptyAuthorListException{
    clearScreen();
    Scanner s = new Scanner(System.in);
    System.out.println("Welcome " + UserName + ", please enter the title of the book you wish to add:");
    String titleInput = s.next();
    s.nextLine();
    clearScreen();
    System.out.println("Please enter the name of the author/s (separated by a comma):");
    List<Author> authors = new ArrayList<>();
    String authorInput = s.nextLine();
    String[] authorNames = authorInput.split(",");
    for (String authorName : authorNames){
      authors.add(new Author(authorName.trim()));
    }
    library.addBookWithTitleAndAuthorlist(titleInput,authors);
    clearScreen();
    System.out.println("Book successfully added!");
  }
  private static void clearScreen(){
    System.out.println("\033[H\033[2J");
    System.out.flush();
  }

  private static void studentLoop() throws UserOrBookDoesNotExistException{
    Scanner s = new Scanner(System.in);
    String[] operations = {"1. Search book", "2. View my books", "3. Quit"};
    makeTable(operations);
    System.out.flush();
    try{
      switch(s.nextInt()){
	case 1: findBook(); break;
	case 2: break;
	case 3: clearScreen();System.exit(1);
      }}
    catch(Exception e){
      clearScreen();
      System.out.println("Invalid option");
    }
  }




  private static void makeTable(String[] array){
    System.out.print(hLine);
    for(String s: array){
      System.out.print(newLine + s);
    }
    System.out.print(down + hLine);
    System.out.println();
  }

  private static void findBook() throws UserOrBookDoesNotExistException{
    Scanner s = new Scanner(System.in);
    clearScreen();
    System.out.println("Enter the name of the book");
    String bookName = s.nextLine();
    try {
      Book book = library.findBookByTitle(bookName);
      bookFound(book);
    } catch (UserOrBookDoesNotExistException |  NoSuchElementException e){
      clearScreen();
      System.out.println("Book was not found (´･_･`)");
    }
  }
  private static void bookFound(Book b) throws UserOrBookDoesNotExistException{
    Scanner s = new Scanner(System.in);
    clearScreen();
    StringBuilder SB = new StringBuilder();
    for(Author a: b.getAuthors()){
      SB.append(a.getName());
      SB.append(", ");
    }
    System.out.println(b.getTitle() + " by " + SB.toString());
    String[] options = {"1. Borrow " + b.getTitle(), "2. Quit"};
    makeTable(options);
    switch (s.nextInt()){
      case 1: try{library.borrowBook(b, library.findUserByName(UserName));
		clearScreen();
		System.out.println(b.getTitle() + " borrowed...");
	      } catch (UserOrBookDoesNotExistException e){System.out.println("Error...");}
	      break;
      default: clearScreen(); break;

    }

  }
  public static void main(String[] args) throws EmptyAuthorListException, UserOrBookDoesNotExistException {
    addFewBooks();
    while (true){
      mainLoop();
    }
  }
}
