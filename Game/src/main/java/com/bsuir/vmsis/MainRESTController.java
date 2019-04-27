package com.bsuir.vmsis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bsuir.vmsis1.snake.Snake;
import com.bsuir.vmsis1.snake.SnakeElement;

@RestController

public class MainRESTController {

	@Autowired
	private ServerThread server;

	@RequestMapping("/")
	@ResponseBody
	public String welcome() {
		return "Welcome to RestTemplate Example.";
	}

	
	// http://localhost:8080/listNotation	
	@RequestMapping(value = "/listNotation",
			method = RequestMethod.GET, 
			produces = { MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public List<FileSystem> getNotationList() {
		List<FileSystem> list = server.getNotationList();
		return list;
	}

	
	// http://localhost:8080/bestGame	
	@RequestMapping(value = "/bestGame", 
			method = RequestMethod.GET, 
			produces = { MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public FileSystem getBestGame(@PathVariable("bestGame") String bestGame) {
		return server.getNotationList().get(0);
	}

	
	// http://localhost:8080/worstGame	
	@RequestMapping(value = "/worstGame", 
			method = RequestMethod.GET, 
			produces = { MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public FileSystem getWorstGame(@PathVariable("worstGame") String worstGame) {
		int size = server.getNotationList().size();
		return server.getNotationList().get(size - 1);
	}

	
	// http://localhost:8080/SomeContextPath/addGame
	@RequestMapping(value = "/addGame", 
			method = RequestMethod.POST, 
			produces = { MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public boolean addGame(@RequestBody FileSystem game) {		

		return server.getNotationList().add(game);
	}

	/*
	// http://localhost:8080/employee	
	@RequestMapping(value = "/employee", 
			method = RequestMethod.PUT, 
			produces = { MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Employee updateEmployee(@RequestBody Employee emp) {

		System.out.println("(Service Side) Editing employee: " + emp.getEmpNo());

		return employeeDAO.updateEmployee(emp);
	}
			
	// http://localhost:8080/employee
	@RequestMapping(value = "/employee/{empNo}", 
			method = RequestMethod.DELETE, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public void deleteEmployee(@PathVariable("empNo") String empNo) {

		System.out.println("(Service Side) Deleting employee: " + empNo);

		employeeDAO.deleteEmployee(empNo);
	}*/
}