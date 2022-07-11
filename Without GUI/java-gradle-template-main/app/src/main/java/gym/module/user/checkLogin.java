package gym.module.user;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import gym.module.service.userServiceGenerator;


public class checkLogin {

    private static Scanner scan = new Scanner(System.in);
    private static String filepath = "M:/1st Sem/Software Engineering/Project/5/java-gradle-template-main/app/src/main/resources/loginData.txt";
    public static createUser newUser = new createUser();
    private static String userCustomer;
    
    public static userServiceGenerator userService = new userServiceGenerator();
    //Main class
    public static void main(String[] args) throws IOException {
        performLoginOperation();
    }

    public static void performLoginOperation() throws IOException {
    	
    	System.out.println("################### Fitness Management Application ###################");
    	System.out.println("");
    	
        boolean checkUser = takeCredentials();
       
        if (checkUser) {
        	userService.provideGreetingMsgToUser(userCustomer);
            userService.provideOptionsToUser(scan,userCustomer);
        } else {
            System.out.println("Oops! User not available, please try signup or rerun the application !!");
            try {
            	//userCustomer = newUser.createUserAccount(scan);
            	userCustomer = newUser.createUserAccount(scan);
            	userService.provideOptionsToUser(scan,userCustomer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        scan.close();
    }

    public static boolean takeCredentials() {

        System.out.println("Please enter username !");
        String username = scan.next();
        System.out.println("Please enter password !");
        String password = scan.next();
        userCustomer = username;
        boolean userPresent = checkIfUserExists(username, password);

        return userPresent;
    }

    public static boolean checkIfUserExists(String customerName, String customerPassword) {

        boolean isCustomerPresent = false;
        Scanner fp;
        try {
            fp = new Scanner(new File(filepath));
            fp.useDelimiter("[,\n]");
            while (fp.hasNext() && !isCustomerPresent) {
                String dataCustomerName = fp.next();
                String dataCustomerPassword = fp.next();
                if (dataCustomerName.trim().equals(customerName) && dataCustomerPassword.trim().equals(customerPassword)) {
                    isCustomerPresent = true;
                    System.out.println(" ");
                    System.out.println("Thanks for providing credentials !");
                } else {
                    isCustomerPresent = false;
                }

            }
            fp.close();
            
        } catch (Exception e) {
           // System.out.println(e);
        }

        return isCustomerPresent;
    }

}