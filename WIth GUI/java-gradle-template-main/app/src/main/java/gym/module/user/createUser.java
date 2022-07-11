package gym.module.user;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class createUser {
	private static String filepath = "M:/1st Sem/Software Engineering/Project/5/java-gradle-template-main/app/src/main/resources/loginData.txt";
	public String createUserAccount(String username, String password) throws IOException {
		
		Path path = Paths.get(filepath.toString());
		OutputStream output = new BufferedOutputStream(Files.newOutputStream(path, StandardOpenOption.APPEND));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
		writer.write(username + "," + password);
		writer.newLine();
		writer.close();
		output.close();
		return username;
		
	}
}
