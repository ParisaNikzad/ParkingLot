package com.gojek.assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

import com.gojek.assignment.service.CommandExecutor;

public class App {
	public static void main(String[] args) throws Exception {

		CommandExecutor commandExecutor = CommandExecutor.getInstance();
		BufferedReader bufferedReader;
		
		if (args.length == 0) {
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		} else {
			String filePath = "/Users/Dileep/Downloads/Code/Practice/Java/parkingTest";
			File inputFile = new File(filePath);
			bufferedReader = new BufferedReader(new FileReader(inputFile));
		}
		
		while (true) {	
			String commandText = bufferedReader.readLine();
			if (commandText == null || "exit".equalsIgnoreCase(commandText)) {
				break;
			} else {
				boolean executionSuccess = commandExecutor.execute(commandText);
				if(!executionSuccess) {
					break;
				}
			}
		}
	}
}
