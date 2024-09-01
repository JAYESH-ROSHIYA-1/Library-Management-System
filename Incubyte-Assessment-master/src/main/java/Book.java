import java.time.Year;

public class Book {

    public String ISBN;
    public String title;
    public String author;
    public Year publicationYear;
    public boolean isBorrowed;

    Book(String ISBN, String title, String author, Year publicationYear){
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isBorrowed = false;
    }

    public boolean isValid(){
        if(this.ISBN == null || this.ISBN.equals("")){
            return false;
        } else if (this.title == null || this.title.equals("")) {
            return false;
        } else if (this.author == null || this.author.equals("")) {
            return false;
        } else if (this.publicationYear == null ) {
            return false;
        }

        return true;
    }
}
