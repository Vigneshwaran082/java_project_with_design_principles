package home.learning.designprinciples.srp;

public class MainClass {

    public static void main(String[] args) throws Exception{

        BootStrap bootStrap = new BootStrap();
        InventoryManager manager = new InventoryManager();
        CredentialValidator validator = new CredentialValidator();
        View view = new View();



        AdminUser user = view.getCredentialsFromUser();

        //Validate Credentials
        if(!validator.validateCredentials(user, bootStrap.getAdminUser().getUserName(),bootStrap.getAdminUser().getPassword())){
            view.printResponseMessageToConsole("Invalid Credentials");
            System.exit(1);
        }

        while (true) {
            int userInput =  view.getInputFromUser();
            if (userInput == 1) {
                manager.addBook();
            } else if (userInput == 2) {
                manager.deleteBook();
            } else if (userInput == 3) {
                manager.displayBooks();
            }else{
                manager.modifyBookDetails();
            }
        }
    }
}
