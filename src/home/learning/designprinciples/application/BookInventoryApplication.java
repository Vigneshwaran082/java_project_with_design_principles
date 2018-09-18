package home.learning.designprinciples.application;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class BookInventoryApplication {

    private Properties properties;
    private List<Book> books = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public void loadApplicationPreferences(String fileLocation) throws IOException{
        properties = new Properties();
        properties.load((fileLocation != null)? new FileInputStream(fileLocation): getClass().getResourceAsStream("SystemPreference.properties"));
        loadDefaultBooksFromApplication();
    }

    public static void main(String[] args) throws IOException {
        BookInventoryApplication application = new BookInventoryApplication();
        application.loadApplicationPreferences(null);

        application.verifyCredentials();
        System.out.println();

        while (true) {
            int userInput = application.getInputFromUser();
            if (userInput == 1) {
                application.addBook();
            } else if (userInput == 2) {
                application.deleteBook();
            } else if (userInput == 3) {
                application.displayBooks();
            }else{
                application.modifyBookDetails();
            }

        }


    }

    public void modifyBookDetails(){
        System.out.print("Enter book id to modify :");
        int bookId = 0;
        modifyBookLoop: while(true){
            try{
                String bookIdStr = scanner.nextLine();
                bookId = Integer.parseInt(bookIdStr);
                for (Book book : this.books) {

                    if (book.getBookId() == bookId) {
                        System.out.println("Enter book name to modify :");
                        String bookName = null;
                        while (true) {
                            bookName = scanner.nextLine();
                            if (bookName == null || bookName.equalsIgnoreCase("")) {
                                System.err.println("Invalid book Name, Try again:");
                                continue ;
                            } else {
                                break;
                            }
                        }

                        double bookPrice = 0.0;
                        System.out.print("Enter book price :");
                        bookPrice: while (true) {
                            try {
                                String price = scanner.next();
                                bookPrice = Double.parseDouble(price);
                                break bookPrice;
                            } catch (Exception e) {
                                System.err.print("Invalid price , try again:");
                                continue bookPrice;
                            }
                        }

                        System.out.print("Enter Quantity :");
                        int bookQuantity = 0;
                        while (true) {
                            try {
                                bookQuantity = scanner.nextInt();
                                break;
                            } catch (Exception e) {
                                System.err.print("Invalid Quantity , try again:");
                                continue;
                            }
                        }

                        book.setBookName(bookName);
                        book.setBookPrice(bookPrice);
                        book.setNumberOfBooks(bookQuantity);
                        book.setBookId(book.getBookId());
                        System.out.println("|==============================================|");
                        System.out.println("|      Book Modified successfully!!   :)       |");
                        System.out.println("|==============================================|");
                        System.out.println();
                        break modifyBookLoop;
                    }
                }
            }catch(Exception e){
                System.err.print("Invalid book id , try again:");
                continue;
            }

        }

    }

    public void deleteBook(){
        System.out.print("Enter book Id to delete :");
        int bookId = 0;
        deleteBook: while (true) {
            try {
                bookId = Integer.parseInt(scanner.next());
                for (Book book : this.books) {
                    if (book.getBookId() == bookId) {
                        this.removeBook(book);
                        System.out.println("|==============================================|");
                        System.out.println("|      Book Deleted successfully!!   :)        |");
                        System.out.println("|==============================================|");
                        System.out.println();
                        break deleteBook;
                    }
                }
                System.err.print("Invalid Book Id, Try Again:");
                continue deleteBook;

            } catch (Exception e) {
                System.err.print("Invalid Book Id, Try Again:");
                continue;
            }
        }
    }

    public void addBook(){
        Book book = new Book();
        scanner.delimiter();
        System.out.print("Enter book name : ");
        String bookName = null;
        while (true) {
            bookName = scanner.nextLine();
            if (bookName == null || bookName.equalsIgnoreCase("")) {
                System.err.println("Invalid book Name, Try again:");
                continue ;
            } else {
                break;
            }
        }

        double bookPrice = 0.0;
        System.out.print("Enter book price :");
        bookPrice: while (true) {
            try {
                String price = scanner.next();
                bookPrice = Double.parseDouble(price);
                break bookPrice;
            } catch (Exception e) {
                System.err.print("Invalid price , try again:");
                continue bookPrice;
            }
        }

        System.out.print("Enter Quantity :");
        int bookQuantity = 0;
        while (true) {
            try {
                bookQuantity = Integer.parseInt(scanner.next());
                break;
            } catch (Exception e) {
                System.err.print("Invalid Quantity , try again:");
                continue;
            }
        }

        book.setBookName(bookName);
        book.setBookPrice(bookPrice);
        book.setNumberOfBooks(bookQuantity);
        book.setBookId(this.books.size()+1);
        this.books.add(book);
        System.out.println("|==============================================|");
        System.out.println("|      Book Added successfully!!   :)          |");
        System.out.println("|==============================================|");
        System.out.println();
    }

    public int getInputFromUser(){
        System.out.print("1.) Press 1 for add a book,");
        System.out.print("2.) Press 2 to delete a book,");
        System.out.print("3.) Press 3 to display inventory.");
        System.out.print("4.) Press 4 to modify inventory.");
        System.out.println();
        System.out.print("Please provide your input : ");
        int input =  0;
        while(true){
            try{
                input = scanner.nextInt();
                if(input ==0 || input >4){
                    System.err.print("Invalid Number , try again:");
                    continue;
                }else {
                    break;
                }
            }catch(Exception e){
                System.err.print("Invalid Number , try again:");
                continue;
            }

        }
        scanner.nextLine(); //nextInt() does not consume the new line character given .https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
        return input;
    }

    private String getUserName() {
        return properties.getProperty("userName");
    }

    private String getPassword() {
        return properties.getProperty("password");
    }

    private List<String> getBooks(){
        String books=  properties.getProperty("books");
        if(books != null){
            return Arrays.asList(books.split(","));
        }
        return new ArrayList<>();
    }

    private void verifyCredentials() {
        System.out.print("Enter your UserName[Case Insensitive]:");
        String userName = scanner.next();
        userName = (userName != null)?userName:""; // To avoid Null pointer exception
        System.out.print("Enter the password[Case Sensitive]:");
        String password = scanner.next();
        password = (password != null)?password:"";// To avoid Null pointer exception

        String systemUserName = this.getUserName();
        String systemPassword = this.getPassword();


        if(!userName.equalsIgnoreCase(systemUserName) || !password.equals(systemPassword)){
            System.err.println("|==============================================|");
            System.err.println("|      Invalid credentials!!   :'(             |");
            System.err.println("|==============================================|");
            System.exit(1);
        }
        System.out.println();
    }

    private void loadDefaultBooksFromApplication(){
        List<String> bookList= this.getBooks();
        int count =1;
        for(String name : bookList){
            Book book = new Book();
            book.setBookId(count++);
            book.setBookName(name);
            book.setBookPrice(100);
            book.setNumberOfBooks(1);
            books.add(book);
        }
    }

    private  boolean addBook(Book book){
        return books.add(book);
    }

    private  boolean removeBook(Book book){
        return books.remove(book);
    }

    public void displayBooks(){
        System.out.println("BOOK INVENTORY:");
        System.out.println("--------------------------------------------------------");
        System.out.println("| S.no[Id] | Name                 |  Price  |  Quantity |");
        System.out.println("---------------------------------------------------------");
        books.forEach(book -> {
            System.out.print("|");
            System.out.print(book.getBookId());
            printWhiteSpace(10, String.valueOf(book.getBookId()).length());
            System.out.print("|");
            System.out.print(book.getBookName());
            printWhiteSpace(22, book.getBookName().length());
            System.out.print("|");
            System.out.print(book.getBookPrice());
            printWhiteSpace(9, String.valueOf(book.getBookPrice()).length());
            System.out.print("|");
            System.out.print(book.getNumberOfBooks());
            printWhiteSpace(11,String.valueOf(book.getNumberOfBooks()).length());
            System.out.print("|");
            System.out.println();
            System.out.println("---------------------------------------------------------");
        });
        System.out.println();
    }

    //FOR UI DECORATION:
    private  void printWhiteSpace(int totalSpace, int totalFilled){
        int totalSpaceToPrint = totalSpace - totalFilled;
        for (int i = 0; i < totalSpaceToPrint; i++) {
            System.out.print(" ");
        }
    }

}


 class Book{

    //Book information
    private int bookId;
    private String bookName;
    private int numberOfBooks;
    private  double bookPrice;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

     public int getBookId() {
         return bookId;
     }

     public void setBookId(int bookId) {
         this.bookId = bookId;
     }
}



