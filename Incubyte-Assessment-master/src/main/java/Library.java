import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Library {

    public String name;
    public Map<String,Book> allBook;
    public Map<Long,User> allUser;

    Library(String name){
        this.name = name;
        this.allBook = new HashMap<>();
        this.allUser = new HashMap<>();
    }

    public void isValidName() throws Exception{
        if(this.name == null || this.name.equals("")){
            throw new Exception("Please Enter library name");
        }
        if(this.name.length() < 4){
            throw new Exception("Library name must be more than 3 letters");
        }
    }

    public void addBook(Book book) throws Exception{
        if(!book.isValid()){
            throw new Exception("Please fill all the details");
        }
        if(allBook.containsKey(book.ISBN)){
            throw new Exception("A book has already added");
        }
        this.allBook.put(book.ISBN, book);
    }


    public void borrowBook(User user,String ISBN) throws Exception {
        this.allUser.put(user.ID, user);
        boolean flag = true;
        if (allBook.containsKey(ISBN)) {
            for (Map.Entry<String, Book> i : allBook.entrySet()) {
                if ((Objects.equals(i.getValue().ISBN, ISBN)) && (!i.getValue().isBorrowed)) {
                    i.getValue().isBorrowed = true;
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            throw new Exception("Book not found");
        }
    }


    public void returnBook(User user, String ISBN) throws Exception {
        if (!allUser.containsKey(user.ID)) {
            throw new Exception("User not Found");
        }
        boolean flag = true;
        if (allBook.containsKey(ISBN)) {
            for (Map.Entry<String, Book> i : allBook.entrySet()) {
                if ((Objects.equals(i.getValue().ISBN, ISBN)) && (i.getValue().isBorrowed)) {
                    i.getValue().isBorrowed = false;
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            throw new Exception("This book is not borrowed");
        }
    }

    public ArrayList<Book> viewBook() throws Exception {
        ArrayList<Book> availableBook = new ArrayList<>();
        for (Map.Entry<String,Book> i: allBook.entrySet()) {
            if(!i.getValue().isBorrowed){
                availableBook.add(i.getValue());
            }
        }
        if(availableBook.size() == 0){
            throw new Exception("No one book is available");
        }else{
            for(Book i : availableBook){
                System.out.println("{ISBN: " + i.ISBN + "} {title: "+ i.title + "} {author: "+ i.author +"} {Publication Year" + i.publicationYear +"}");
            }
        }

        availableBook.sort((a,b) -> (b.ISBN.compareTo(a.ISBN)));
        return availableBook;
    }
}
