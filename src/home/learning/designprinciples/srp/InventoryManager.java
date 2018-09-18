package home.learning.designprinciples.srp;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Module 2 : Manage Inventory
 * <p>
 * This Module is now independent of any other modules
 */
public class InventoryManager {

    /*
    *  Only one Storage should be present for the Whole application , so we made this variable as static
    *  and only manager should be modify books so we made it private
    * */
    private static List<Book> books = new ArrayList<>();
    private View view = new View();

    public void modifyBookDetails() {

        while (true) {

            int bookId = view.getBookId("Modify");
            boolean isBookIdPresent =false;
            Book bookToModify = null;

            for (Book book : InventoryManager.books) {
                if (book.getBookId() == bookId) {
                    isBookIdPresent =true;
                    bookToModify =book;
                }
            }

            if(!isBookIdPresent) {
                view.printResponseMessageToConsole("Invalid Book id ! Try again");
                continue ;
            }

            Book modifiedBook = view.getBookToAddOrModify();
            bookToModify.setBookName(modifiedBook.getBookName());
            bookToModify.setBookPrice(modifiedBook.getBookPrice());
            bookToModify.setNumberOfBooks(modifiedBook.getNumberOfBooks());
            bookToModify.setBookId(bookId);

            view.printResponseMessageToConsole("Book Modified successfully!!");
            break;
            }
        }

    public void addBook(){
        Book bookToadd = view.getBookToAddOrModify();
        Collections.sort(books,(b1, b2) -> {
                if(b1.getBookId() > b2.getBookId()){
                    return 1;
                }else if(b1.getBookId() < b2.getBookId()){
                    return  -1;
                }else{
                    return 0;
                }
        });

        bookToadd.setBookId(books.get(books.size()-1).getBookId()+1);
        InventoryManager.books.add(bookToadd);
        view.printResponseMessageToConsole("Book Added successfully!!");
    }

    public void deleteBook(){

        while (true) {

            int bookId = view.getBookId("Delete");
            boolean isBookIdPresent = false;
            Book bookToDelete = null;

            for (Book book : books) {
                if (book.getBookId() == bookId) {
                    isBookIdPresent = true;
                    bookToDelete = book;
                }
            }

            if (!isBookIdPresent) {
                view.printResponseMessageToConsole("Invalid Book id ! Try again");
                continue;
            }

            books.remove(bookToDelete);
            view.printResponseMessageToConsole("Book Deleted successfully!!");
            break;
        }
    }

    public void displayBooks(){
        view.displayBooksToConsole(books);
    }

    public void addBooksToInventory(Book book){
        books.add(book);
    }

}
