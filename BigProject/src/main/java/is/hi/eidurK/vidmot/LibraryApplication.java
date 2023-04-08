package is.hi.eidurK.vidmot;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import is.hi.eidurK.vinnsla.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApplication {
  private LibrarySystem library = new LibrarySystem();

  private void addTop10Books()throws EmptyAuthorListException, UserOrBookDoesNotExistException {
    In in = new In("file:BigProject/Top10Books.txt");
    while (in.hasNextLine()){
      String line = in.readLine();
      String bookNamesAndAuthors[] = line.split(",");
      for (int i=0; i < bookNamesAndAuthors.length; i+=2){
	List<Author> authors = new ArrayList<>();
	authors.add(new Author(bookNamesAndAuthors[i+1]));
	Book book = new Book(bookNamesAndAuthors[i], authors);
	library.addBookWithTitleAndAuthorlist(book.getTitle(),book.getAuthors());
      }
    }

  }

  public static void main(String[] args) throws EmptyAuthorListException, UserOrBookDoesNotExistException {
    In in = new In("file:BigProject/Top10Books.txt");
    Scanner s = new Scanner(System.in);
    LibrarySystem librarySystem = new LibrarySystem();
    while (in.hasNextLine()){
      String line = in.readLine();
      String bookNamesAndAuthors[] = line.split(",");
      for (int i=0; i < bookNamesAndAuthors.length; i+=2){
	List<Author> authors = new ArrayList<>();
	authors.add(new Author(bookNamesAndAuthors[i+1]));
	Book book = new Book(bookNamesAndAuthors[i], authors);
	librarySystem.addBookWithTitleAndAuthorlist(book.getTitle(),book.getAuthors());
	System.out.println(book.getTitle()+", "+book.getAuthors().get(0).getName()+" ");
      }
    }


    while (true){
      System.out.println("Please specify whether you are a faculty member or a student (f/s):");
      String postition = s.next();
      s.nextLine();
      if (postition.equals("f")){
	System.out.println("What is your name?");
	String name = s.nextLine();
	try {
	  librarySystem.findUserByName(name);
	}
	catch (UserOrBookDoesNotExistException e){
	  FacultyMember facultyMember = new FacultyMember(name,"Upper Management");
	  librarySystem.addFacultyMemberUser(facultyMember.getName(), facultyMember.getDepartment());
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
	librarySystem.addBookWithTitleAndAuthorlist(titleInput,authors);
	System.out.println("Book successfully added!");
      }
      else if (postition.equals("s")) {
	System.out.println("What is your name?");
	String name = s.nextLine();
	System.out.println("Welcome " + name + ", what is the name of the book you would like to borrow?");
	String titleInput = s.nextLine();
	librarySystem.findBookByTitle(titleInput);
      }
      else {
	System.out.println("Input invalid");
      }
    }
  }
}
