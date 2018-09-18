package home.learning.designprinciples.srp;

import java.io.IOException;
import java.util.Properties;

public class BootStrap {

    private static Properties properties;

    public BootStrap() throws IOException {
        if(properties == null) {
            properties = new Properties();
            properties.load(getClass().getResourceAsStream("SystemPreference.properties"));
            addDefaultBooksToInventory();
        }
    }

    public AdminUser getAdminUser(){
        AdminUser adminUser = new AdminUser();
        adminUser.setUserName(properties.getProperty("userName"));
        adminUser.setPassword(properties.getProperty("password"));
        return adminUser;
    }


    private void addDefaultBooksToInventory(){
        String books=  properties.getProperty("books");
        InventoryManager manager = new InventoryManager();

        String[] bookNames =   books.split(",");
        int count =1;
        for(String bookName : bookNames){
            Book book = new Book();
            book.setBookId(count++);
            book.setBookName(bookName);
            book.setBookPrice(100);
            book.setNumberOfBooks(1);
            manager.addBooksToInventory(book);
        }
    }

}
