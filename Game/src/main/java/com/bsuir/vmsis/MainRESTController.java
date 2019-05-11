package com.bsuir.vmsis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRESTController {

	@Autowired
	public SnakeNotation snakeNotations;

	// http://localhost:8080/snakeNotations
	@RequestMapping(value = "/snakeNotations", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public List<String> getListOfSnakeNotations() {
		List<String> list = snakeNotations.getListOfSnakeNotations();
		return list;
	}
}
