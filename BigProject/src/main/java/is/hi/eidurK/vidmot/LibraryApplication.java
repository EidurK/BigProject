package is.hi.eidurK.vidmot;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import is.hi.eidurK.vinnsla.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApplication {
  private static LibrarySystem library = new LibrarySystem();
  private String UserName;


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
  
  private static void logIn(){
    

  }
  private static void mainLoop(){
    clearScreen();
  }



  private static void clearScreen(){
    System.out.println("\033[H\033[2J");
    System.out.flush();
  }

  public static void main(String[] args) throws EmptyAuthorListException, UserOrBookDoesNotExistException {
    Scanner s = new Scanner(System.in);
    addFewBooks();
    

    while (true){
      System.out.println("Please specify whether you are a faculty member or a student (f/s):");
      String postition = s.next();
      s.nextLine();
      if (postition.equals("f")){
	System.out.println("What is your name?");
	String name = s.nextLine();
	try {
	  library.findUserByName(name);
	}
	catch (UserOrBookDoesNotExistException e){
	  FacultyMember facultyMember = new FacultyMember(name,"Upper Management");
	  library.addFacultyMemberUser(facultyMember.getName(), facultyMember.getDepartment());
	}
	System.out.println("Welcome " + name + ", please enter the title of the book you wish to add:");
	String titleInput = s.next();
	s.nextLine();
	System.out.println("Please enter the name of the author/s (separated by a comma):");
	List<Author> authors = new ArrayList<>();
	String authorInput = s.nextLine();
	String[] authorNames = authorInput.split(",");
	for (String authorName : authorNames){
	  authors.add(new Author(authorName.trim()));
	}
	library.addBookWithTitleAndAuthorlist(titleInput,authors);
	System.out.println("Book successfully added!");
      }
      else if (postition.equals("s")) {
	System.out.println("What is your name?");
	String name = s.nextLine();
	System.out.println("Welcome " + name + ", what is the name of the book you would like to borrow?");
	String titleInput = s.nextLine();
	library.findBookByTitle(titleInput);
      }
      else {
	System.out.println("Input invalid");
      }
    }
  }
}
