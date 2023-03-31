package is.hi.eidurK;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
    private List<Book> books;
    private List<Lending> lendings;
    private List<User> users;

    public LibrarySystem() {
        books = new ArrayList<>();
        lendings = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBookWithTitleAndAuthorlist(String title, List<Author> authors) throws EmptyAuthorListException {
        if (authors.size() == 0) {
            throw new EmptyAuthorListException("List of authors is empty");
        }
        books.add(new Book(title, authors));
    }

    public void addStudentUser(String name, Boolean feePaid) throws UserOrBookDoesNotExistException {
        if (users.size() == 0) {
            throw new UserOrBookDoesNotExistException("List of users is empty");
        }
        users.add(new Student(name, feePaid));
    }

    public void addFacultyMemberUser(String name, String department) throws UserOrBookDoesNotExistException {
        if (users.size() == 0) {
            throw new UserOrBookDoesNotExistException("List of users is empty");
        }
        users.add(new FacultyMember(name, department));
    }

    public Book findBookByTitle(String title) throws UserOrBookDoesNotExistException {
        if (books.size() == 0) {
            throw new UserOrBookDoesNotExistException("List of books is empty");
        }
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        throw new UserOrBookDoesNotExistException("Book not found");
    }

    public User findUserByName(String name) throws UserOrBookDoesNotExistException {
        if (users.size() == 0) {
            throw new UserOrBookDoesNotExistException("List of users is empty");
        }
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw new UserOrBookDoesNotExistException("User not found");
    }

    public void borrowBook(Book book, User user) throws UserOrBookDoesNotExistException {
        if (books.size() == 0) {
            throw new UserOrBookDoesNotExistException("List of books is empty");
        }
        for (Book b : books) {
            if (b.getTitle().equals(book.getTitle())) {
                if (b.getAuthors().size() == 0) {
                    throw new UserOrBookDoesNotExistException("List of authors is empty");
                }
                for (Author a : b.getAuthors()) {
                    if (a.getName().equals(user.getName())) {
                        lendings.add(new Lending(book, user));
                    }
                }
            }
        }
    }

    public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate){
        for (Lending l : lendings) {
            if (l.getBook().getTitle().equals(book.getTitle()) && l.getUser().getName().equals(facultyMember.getName())) {
                l.setDueDate(newDueDate);
            }
        }
    }

    public void returnBook(Book book, User user) throws UserOrBookDoesNotExistException {
        if (books.size() == 0) {
            throw new UserOrBookDoesNotExistException("List of books is empty");
        }
        for (Book b : books) {
            if (b.getTitle().equals(book.getTitle())) {
                if (b.getAuthors().size() == 0) {
                    throw new UserOrBookDoesNotExistException("List of authors is empty");
                }
                for (Author a : b.getAuthors()) {
                    if (a.getName().equals(user.getName())) {
                        lendings.remove(lendings.indexOf(new Lending(book, user)));
                    }
                }
            }
        }
    }

}