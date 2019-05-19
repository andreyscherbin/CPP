package com.bsuir.vmsis;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRESTController {

	@Autowired
	private SnakeNotation snakeNotation;

	// http://localhost:8080/snakeNotations
	@RequestMapping(value = "/snakeNotations", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public List<String> getListOfSnakeNotations() {

		List<String> listOfSnakeNotations = new ArrayList<String>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream("../Game/src/main/resources/savedgame.txt"), StandardCharsets.UTF_8))) {
			String line;
			while ((line = reader.readLine()) != null) {
				listOfSnakeNotations.add(line);
			}

		} catch (IOException e) {

			System.out.println("error read file autowrite");
		}
		return listOfSnakeNotations;
	}

	@RequestMapping(value = "/Goldbach/{number}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public List<Integer> GoldbachResponse(@PathVariable("number") Integer number) {
		return Goldbach(number);
	}

	@RequestMapping(value = "/getSnakeNotation", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public List<FileSystem> getSnakeNotation() {
		return snakeNotation.getAllNotation();
	}

	@RequestMapping(value = "/addSnakeNotation", //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public String addSnakeNotation(@RequestBody FileSystem newNotation) {

		snakeNotation.addNotation(newNotation);
		return "add SnakeNotation OK";
	}

	@RequestMapping(value = "/sortSnakeNotation", //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public String sortSnakeNotation() {

		snakeNotation.sortListNotations();
		return "sort SnakeNotation OK";
	}

	private List<Integer> Goldbach(Integer number) {

		if (number == 2) {
			return null;
		}
		if ((number % 2) != 0) {
			return null;
		}
		List<Integer> rangelist = Arrays.stream(IntStream.rangeClosed(2, number).map(i -> number - i + 2).toArray())
				.boxed().collect(Collectors.toList());
		rangelist = rangelist.stream().filter(element -> isPrime(element)).collect(Collectors.toList());
		List<Integer> resultList = numbers(number, rangelist);
		return resultList;
	}

	private boolean isPrime(Integer number) // является ли number простым
	{
		if (number < 2) {
			return false;
		} else {
			List<Integer> rangelist = Arrays.stream(IntStream.rangeClosed(2, (int) Math.sqrt(number)).toArray()).boxed()
					.collect(Collectors.toList());
			return !(rangelist.stream().anyMatch(element -> number % element == 0));
		}
	}

	private List<Integer> numbers(Integer number, List<Integer> listrange) {
		Integer maxPrime = listrange.get(0);
		if (listrange.stream().anyMatch(element -> (number - maxPrime) == element)) {
			List<Integer> resultList = new ArrayList<Integer>();
			resultList.add(number - maxPrime);
			resultList.add(maxPrime);
			return resultList;
		} else {
			List<Integer> nextList = listrange.subList(1, listrange.size());
			return numbers(number, nextList);
		}
	}
}
