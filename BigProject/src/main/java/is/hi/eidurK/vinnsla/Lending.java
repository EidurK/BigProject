package is.hi.eidurK.vinnsla;
import java.time.LocalDate;
public class Lending {
    private LocalDate dueDate;
    //private Book book;
    private Borrowable borrowable;
    private User user;
    public Lending(Borrowable borrowable, User user) {
        //this.book = book;
        this.borrowable = borrowable;
        this.user = user;
        this.dueDate = LocalDate.now().plusDays(30);
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public Borrowable getBorrowable() {
        return borrowable;
    }
    public void setBorrowable(Borrowable borrowable) {
        this.borrowable = borrowable;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}