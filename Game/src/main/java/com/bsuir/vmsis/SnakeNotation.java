package com.bsuir.vmsis;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class SnakeNotation {

	List<String> listOfSnakeNotations;

	public SnakeNotation() {
		listOfSnakeNotations = new ArrayList<String>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream("../Game/src/main/resources/savedgame.txt"), StandardCharsets.UTF_8))) {
			String line;
			while ((line = reader.readLine()) != null) {
				listOfSnakeNotations.add(line);
			}

		} catch (IOException e) {
			
			System.out.println("error read file autowrite");
		}
	}

	public void addNotation(String snakenotation) {
		listOfSnakeNotations.add(snakenotation);
	}

	public List<String> getListOfSnakeNotations() {

		return listOfSnakeNotations;
	}

}