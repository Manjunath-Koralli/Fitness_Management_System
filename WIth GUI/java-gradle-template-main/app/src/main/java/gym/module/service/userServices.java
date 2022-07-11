package gym.module.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class userServices {
	private static String datafilepath = "M:/1st Sem/Software Engineering/Project/5/java-gradle-template-main/app/src/main/resources/dataFile.csv";
	private static String entry = "",currentUsername="",currentUserTag = "", currentUserTrainingPlan = "";
	private static String currentUserActivities = "", currentUserdata = "", currenttrainer = "";
	private static int currentworkinghours,currenttargethours;
	public boolean showUserCurrentData(String trainerName) throws IOException {
		boolean hasValidCustomers = false;
		//try(BufferedReader reader = new BufferedReader(new FileReader(datafilepath))) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(datafilepath));

			while((entry = reader.readLine()) != null) {

				String[] values = entry.split(",");
				//System.out.println(values.toString());
				if(values[0].equals("tagId")) {
					continue;
				}
				else {
					//check null
					if(values[7].equals(trainerName)) {
						hasValidCustomers = true;
						String message = "Tag Id : " + values[0] + ", Customer Name : " + values[1] + ", Training Plan : " + values[2] + ", Workout Hours completed : " + values[3] + ", Workout Hours Target : " + values[4];
//						System.out.println("Tag Id : " + values[0] + ", Customer Name : " + values[1] + ", Training Plan : "
//								+ values[2]  + ", Workout Hours completed : " + values[3] + ", Workout Hours Target : " + values[4]);

					}
				}
			}
			reader.close();}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}

		if(!hasValidCustomers) {
			System.out.println("Sorry ! You don't have any customer listed under you");
		}
		return hasValidCustomers;

	}

	public void showUserTrainingConsistency(int tagId) throws IOException {
		//try (BufferedReader reader1 = new BufferedReader(new FileReader(datafilepath))){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(datafilepath));
			while((entry = reader.readLine()) != null) {
				String[] values = entry.split(",");
				if(values[0].equals("tagId")) {
					continue;
				}
				else {
					if(Integer.parseInt(values[0]) == tagId) {
						int userTag = Integer.parseInt(values[0]);
						int completedHours =  Integer.parseInt(values[3]);
						int targetHours =  Integer.parseInt(values[4]);
						double efficiency = (double)completedHours/targetHours;
						assignCurrentUserData(values);
						//Changed Math.floor((efficiency*100)) into efficiency*100
						//System.out.println("Customer Name : "  + values[1] + ", Efficiency Of The Customer : " + (efficiency*100));
					}
				}
			}
			//reader.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void assignCurrentUserData(String values[]) {
		currentUserTag = values[0];
		currentUsername = values[1];
		currentUserTrainingPlan =  values[2];
		currentworkinghours = Integer.parseInt(values[3]);
		currenttargethours = Integer.parseInt(values[4]);
		currentUserActivities = values[5];
		currentUserdata =  values[6];
		currenttrainer =  values[7];
	}
	public void showUserIndividualTrainingData(int tagId) throws IOException {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(datafilepath));
			while((entry = reader.readLine()) != null) {
				String[] values = entry.split(",");
				if(values[0].equals("tagId")) {
					continue;
				}
				else {
					if(Integer.parseInt(values[0]) == tagId) {
						String message = "Customer Name : " + values[1] + " "+ "Activities Targeted Daily : " +  values[5]+ "Collected Data : " +  values[6];
//						System.out.println("Customer Name : " + values[1]);
//						System.out.println(" ");
//						System.out.println("Activities Targeted Daily : " +  values[5]);
//						System.out.println("Collected Data : " +  values[6]);
						//return message;
					}
				}
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void modifyTrainingPlan(int tagId, String trainingPlan, String trainingActivities) throws IOException {
		File userDataFile = new File(datafilepath);
		String previousEntry = "";
		BufferedReader reader = null;
		FileWriter writer = null;
		try
		{
			reader = new BufferedReader(new FileReader(userDataFile));
			String line = reader.readLine();
			while (line != null)
			{
				previousEntry = previousEntry + line + System.lineSeparator();
				line = reader.readLine();

			}
			String currentEntry = currentUserTag + "," + currentUsername + "," + currentUserTrainingPlan  + "," + 0 + "," + currenttargethours + "," + currentUserActivities + "," +  currentUserdata + "," + currenttrainer;
			String modifiedEntry = currentUserTag + "," + currentUsername + "," + trainingPlan  + "," + 0 + "," + currenttargethours + "," + trainingActivities + "," +  currentUserdata + "," + currenttrainer;
			String newEntry = previousEntry.replace(currentEntry, modifiedEntry);
			writer = new FileWriter(userDataFile);
			writer.write(newEntry);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				reader.close();
				writer.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	public void addNewCustomer(String newUserData, String filepath) throws IOException {
		Path path = Paths.get(filepath.toString());
		OutputStream output = new BufferedOutputStream(Files.newOutputStream(path, StandardOpenOption.APPEND));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
		//System.out.println(newPassBookStatement);

		writer.write(newUserData);
		writer.newLine();
		writer.close();
		output.close();
	}
}