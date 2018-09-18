package home.learning.designprinciples.srp;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;


/**
 * Module 5 : Getting I/P and displaying O/P to user
 * Now displayBookToConsole method doesn't dependents on any other module.
 * Now , when  tomorrow instead of storing the books in array List if it stores in DB , this class remains unchanged.
 *
 * If tomorrow instead of displaying the output to console , if we want to send to HTML or print it to file , then we only need to
 * change here. Or if the complete I/O operation needs to be changed then only here they need to change and test it.
 *
 * This class only depend on model classes [Book and AdminUser] , when property of these classes changes only this module need to be changed
 * that is very minimal and unavoidable.
 * https://stackoverflow.com/questions/52247333/what-is-the-use-of-single-responsibility-principle#52247462
 * */
public class View {

    private Scanner scanner = new Scanner(System.in);

    //FOR UI DECORATION:
    private  static void printWhiteSpace(int totalSpace, int totalFilled){
        int totalSpaceToPrint = totalSpace - totalFilled;
        for (int i = 0; i < totalSpaceToPrint; i++) {
            System.out.print(" ");
        }
    }


    public void displayBooksToConsole(List<Book> books){
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

    public AdminUser getCredentialsFromUser(){
        System.out.print("Enter your UserName[Case Insensitive]:");
        String userName = scanner.next();
        userName = (userName != null)?userName:""; // To avoid Null pointer exception
        System.out.print("Enter the password[Case Sensitive]:");
        String password = scanner.next();
        password = (password != null)?password:"";// To avoid Null pointer exception


        AdminUser user = new AdminUser();
        user.setUserName(userName);
        user.setPassword(password);
        return user;
    }


    public Book getBookToAddOrModify() {
        Book book = new Book();
        modifyBookLoop:
        while (true) {
            try {
                System.out.println("Enter book name:");
                String bookName = null;
                bookNameLoop:
                while (true) {
                    bookName = scanner.nextLine();
                    if (bookName == null || bookName.equalsIgnoreCase("")) {
                        System.err.println("Invalid book Name, Try again:");
                        continue bookNameLoop;
                    } else {
                        break bookNameLoop;
                    }
                }

                double bookPrice = 0.0;
                System.out.print("Enter book price :");
                bookPriceLoop:
                while (true) {
                    try {
                        bookPrice =  scanner.nextDouble();
                        scanner.nextLine();
                        break bookPriceLoop;
                    } catch (Exception e) {
                        System.err.print("Invalid price , try again:");
                        continue bookPriceLoop;
                    }
                }

                System.out.print("Enter Quantity :");
                int bookQuantity = 0;
                bookQuantityLoop:
                while (true) {
                    try {
                        bookQuantity = scanner.nextInt();
                        scanner.nextLine();
                        break bookQuantityLoop;
                    } catch (Exception e) {
                        System.err.print("Invalid Quantity , try again:");
                        continue bookQuantityLoop;
                    }
                }

                book.setBookName(bookName);
                book.setBookPrice(bookPrice);
                book.setNumberOfBooks(bookQuantity);
                book.setBookId(book.getBookId());
                break modifyBookLoop;
            }catch (Exception e) {
                System.err.print("Invalid book id , try again:");
                continue;
            }
        }
        return book;
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
                input =  scanner.nextInt();
                scanner.nextLine();
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
        return input;
    }

    public int getBookId(String operation) {
        int bookId = 0;
        while (true) {
            try {
                System.out.print("Enter book id to " + operation + " :");
                bookId =  scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println();
                System.err.println("Invalid book id , try again:");
                scanner.nextLine();
                continue;
            }
        }
        return bookId;
    }


    public void printResponseMessageToConsole(String message){
        PrintStream printStream = message.contains("success") ? System.out : System.err;
        String smilie = message.contains("success")? ":)" : ":(";
        if(message.length() < 27) {
            message = String.format("%-28s" ,message);
        }
        String consoleMessage = "|==============================================|" + '\n' +
                                "|      "+message+"   "+smilie+"       |" + '\n' +
                                "|==============================================|";
        printStream.println(consoleMessage);
        /*
         * Added as print stream is executing in parallel and  rendering get distorted
         * */
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();

    }


}
