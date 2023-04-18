package is.hi.eidurK.vidmot;
import edu.princeton.cs.algs4.In;
import is.hi.eidurK.vinnsla.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Stream;

public class LibraryApplication {
  protected static LibrarySystem library;
  protected static String UserName;
  protected static boolean UserIsFaucultyMember;
  private static void addFewBooks()throws EmptyAuthorListException {
    In in = new In("100Books.csv");
    while (in.hasNextLine()){
      List<Author> authors = new ArrayList<>();
      String[] bookNamesAndAuthors = in.readLine().split(";");
      String[] AllAuthorsOfABook = bookNamesAndAuthors[1].split(",");
      for (String s : AllAuthorsOfABook) {
        authors.add(new Author(s));
      }
      library.addBookWithTitleAndAuthorlist(bookNamesAndAuthors[0], authors);
    }
  }
  private static void inputUserName(){
    Scanner s = new Scanner(System.in);
    Gui.clearScreen();
    System.out.println("What is your name?");
    UserName = s.nextLine();
    Gui.clearScreen();
    System.out.println("Please specify whether you are a faculty member or a student (f/s):");
    UserIsFaucultyMember = s.next().equals("f");
    Gui.clearScreen();
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
      FacultyUI.facultyLoop();
    }
    else {
      try {
        library.findUserByName(UserName);
      }
      catch (UserOrBookDoesNotExistException e){
        Student  student = new Student(UserName, false);
        library.addStudentUser(student.getName(), student.isFeePaid());
      }
      StudentUI.studentLoop();
    }
  }
  protected static Borrowable findBook() {
    Scanner s = new Scanner(System.in);
    Gui.clearScreen();
    System.out.println("Enter the name of the book");
    try {
      return library.findBorrowableByTitle(s.nextLine());
    } catch (UserOrBookDoesNotExistException |  NoSuchElementException e){
      Gui.clearScreen();
      System.out.println("Book was not found (´･_･`)");
    }
    return null;
  }

  protected static String getAuthors(Book b){
    StringBuilder stringBuilder = new StringBuilder();
    for(Author a: b.getAuthors()){
      stringBuilder.append(a.getName());
      if(!(b.getAuthors().indexOf(a) == b.getAuthors().size() -1)){
        stringBuilder.append(", ");
      }
    }
    return stringBuilder.toString();
  }
  public static void main(String[] args) throws EmptyAuthorListException, UserOrBookDoesNotExistException {
    library = new LibrarySystem();
    addFewBooks();
    while (true){
      mainLoop();
    }
  }



}
