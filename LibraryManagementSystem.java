package in.sp.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String isbn;
    private String title;
    private String author;
    private int publicationYear;
    private boolean isAvailable;

    public Book(String isbn, String title, String author, int publicationYear) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isAvailable = true;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return String.format("| %-13s | %-25s | %-15s | %-4d | %-8s |",
                             isbn, title, author, publicationYear, isAvailable ? "Yes" : "No");
    }
}

public class LibraryManagementSystem {

    private List<Book> books;

    public LibraryManagementSystem() {
        books = new ArrayList<>();
    }

    // Add a book to the library
    public void addBook(String isbn, String title, String author, int year) {
        Book book = new Book(isbn, title, author, year);
        books.add(book);
        System.out.println("Book Has benn Added: " + title);
    }

    // Borrow a book from the library
    public void borrowBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (book.isAvailable()) {
                    book.setAvailable(false);
                    System.out.println("Borrowed: " + book.getTitle());
                } else {
                    System.out.println("Sorry, this book is not available.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    // Return a borrowed book to the library
    public void returnBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (!book.isAvailable()) {
                    book.setAvailable(true);
                    System.out.println("Returned: " + book.getTitle());
                } else {
                    System.out.println("This book was not borrowed.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    // View all available books in the library
    public void viewAvailableBooks() {
        System.out.println("\nAvailable Books:");
        System.out.println("+--------------+---------------------------+-----------------+------+----------+");
        System.out.println("| ISBN         | Title                     | Author          | Year | Available |");
        System.out.println("+--------------+---------------------------+-----------------+------+----------+");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book);
            }
        }
        System.out.println("+--------------+---------------------------+-----------------+------+----------+");
    }

    // Main method to interact with the Library system
    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Menu:");
            System.out.println("+---+---------------------+");
            System.out.println("| 1 | Add Book            |");
            System.out.println("+---+---------------------+");
            System.out.println("| 2 | Borrow Book         |");
            System.out.println("+---+---------------------+");
            System.out.println("| 3 | Return Book         |");
            System.out.println("+---+---------------------+");
            System.out.println("| 4 | View Available Books|");
            System.out.println("+---+---------------------+");
            System.out.println("| 5 | Exit                |");
            System.out.println("+---+---------------------+");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Publication Year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    library.addBook(isbn, title, author, year);
                    break;

                case 2:
                    System.out.print("Enter ISBN of the book to borrow: ");
                    String borrowIsbn = scanner.nextLine();
                    library.borrowBook(borrowIsbn);
                    break;

                case 3:
                    System.out.print("Enter ISBN of the book to return: ");
                    String returnIsbn = scanner.nextLine();
                    library.returnBook(returnIsbn);
                    break;

                case 4:
                    library.viewAvailableBooks();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
