package is.hi.eidurK.vinnsla;

import java.util.List;

public class Book {


    private String title;
    private List<Author> authors;

    public Book(String title, List<Author> authors)throws
            EmptyAuthorListException {
        if (authors.size() == 0){
            throw new EmptyAuthorListException("List of authors is empty");
        }
        this.authors = authors;
        this.title = title;
        }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) throws EmptyAuthorListException {
        if (authors.size() == 0){
            throw new EmptyAuthorListException("List of authors is empty");
        }
        this.authors = authors;
    }






}
