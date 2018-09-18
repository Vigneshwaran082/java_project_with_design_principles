package home.learning.designprinciples.srp;

/**
 * Module 4 [Validate Credentails]
 *
 * Now CredentailValidator is not dependent on input[Console]. If tomorrow we are changing the I/P from console to
 * web this class remains unchanged and don't need to tested as it is free from that Module[Application View]
 *
 * */
public class CredentialValidator {

    public boolean validateCredentials(AdminUser userInfo , String userName, String password){
        return (!userName.equalsIgnoreCase(userInfo.getUserName()) || !password.equals(userInfo.getPassword()))?false:true;
    }

}
