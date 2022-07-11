package gym.module.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class userServiceGenerator {
	
	public static userServices services = new userServices();
	public static String currentUser = "";
	private static String datafilepath = "M:/1st Sem/Software Engineering/Project/5/java-gradle-template-main/app/src/main/resources/dataFile.csv";
	public void provideGreetingMsgToUser( String user) 
	{
		currentUser = user;
    	System.out.println(" ");
        System.out.println("Dear " + user + " , Welcome to your account !!");
		System.out.println("Dear " + user + " , Please enter option you want to use !");
	}
	
	public void provideOptionsToUser(Scanner sc, String user) throws IOException {
		
		System.out.println(" ");
		System.out.println("------------------------- 1. Evaluate Customer Training Plan -------------------------");
		System.out.println("------------------------- 2. Add customer/client under your name -------------------------");
		
		System.out.println(" ");
		System.out.println(" 1/2 ");
		
		int option = sc.nextInt();
		
		checkOptions(option, sc, user);
		
	}
	
	public void checkOptions(int option, Scanner sc, String user) throws FileNotFoundException {
		
		switch(option) {
		
		case 1:
			try {
				boolean isValidTrainer = services.showUserCurrentData(currentUser);
				
				if(isValidTrainer) {
					System.out.println(" ");
					System.out.println("Please enter tagId to check efficiency of the customer");
					
					int tagOption = sc.nextInt();

					services.showUserTrainingConsistency(tagOption);
					
					System.out.println(" ");
					System.out.println(" ");
					System.out.println("Continue getting further analysis of the customer ?");
					System.out.println(" ");
					System.out.println("Press 1 to continue or others to go back !");
					
					int menuOption = sc.nextInt();
					
					if(menuOption == 1) {
						services.showUserIndividualTrainingData(tagOption);
						
						System.out.println(" ");
						System.out.println("Do you want to modify the current training plan ? ");
						System.out.println("Press 1 to continue or others to go back !");
						System.out.println(" ");
						
						int subMenuOption = sc.nextInt();
						
						if(subMenuOption == 1) {
							System.out.println("Please enter the new training plan value  !! Ex - WL/WG (WeightLoss / WeightGain)");
							String newTrainingPlan = sc.next();
							System.out.println(" ");
							System.out.println("Thanks for entering the new training plan ");
							
							sc.nextLine();
							System.out.println(" ");
							System.out.println("Please enter the new training activities value  !! Ex - Seated leg press (10 reps x 3 sets)");
							String newTrainingActivities = sc.nextLine();
							
							services.modifyTrainingPlan(tagOption,newTrainingPlan,newTrainingActivities);
						}
						else {
							System.out.println("Please select options !!");
							provideOptionsToUser(sc,currentUser);
						}
						
					}
					else {
						System.out.println("Please select options !!");
						provideOptionsToUser(sc,currentUser);
					}
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			try {

				System.out.println(" ");
				System.out.println("Please enter Customer Name");
				
				String customerName = sc.next();

				System.out.println(" ");
				System.out.println("Please enter Training Plan - ex (WL/WG)");
				
				String trainingPlan = sc.next();
				
				System.out.println("Working hours are currently set as 0 for the user");
				
				System.out.println(" ");
				System.out.println("Please enter Target Hours");
				
				int targetHours = sc.nextInt();
				
				System.out.println(" ");
				System.out.println("Please enter activities targeted");
				
				String activities = sc.next();
				
				sc.nextLine();
				System.out.println(" ");
				System.out.println("Please enter current physical status - Ex (Fitbit Tag Status - 90%)");
				
				String currentPhysicalStatus =  sc.nextLine();
				
				//tagId generation
				ArrayList<Integer> numbers = new ArrayList<Integer>();   
				Random ran = new Random();
				int tagId = ran.nextInt(1000);
				if(!numbers.contains(tagId)) {
					numbers.add(tagId);
				}
				System.out.println(tagId);
				//if(tagId)
				String newUserData = tagId + "," + customerName + "," + trainingPlan  + "," + 0 + "," + targetHours + "," + activities + "," +  currentPhysicalStatus + "," + user;
				services.addNewCustomer(newUserData,datafilepath);
				System.out.println("New customer/user added under you with the tagId - " +tagId);
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}	
