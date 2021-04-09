package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class Controller {

	@Autowired
    private ICityService cityService;
	
	@Autowired
	UserService userService;

	@GetMapping(value = "/cities")
    public List<City> getCities() {

        List<City> cities = cityService.findAll();

        return cities;
    }
	
	@PostMapping(value = "/login",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody String user) throws JsonMappingException, JsonProcessingException {
	
		ResponseEntity<Object> resp = null;
		ObjectMapper mapper = new ObjectMapper();
		User pojo = mapper.readValue(user, User.class);
		
		System.out.println(pojo.getUserId() + " " + pojo.getPassowrd());
		
		User userPojo = userService.getUser(pojo.getUserId(), pojo.getPassowrd());
		
		if(userPojo!=null) {
			String response= "{\"message\":\"user valid\",\"statusCode\":\"200\"}";
			resp= new ResponseEntity<Object>(response, HttpStatus.OK);
		}else {
			String response= "{\"message\":\"user invalid\",\"statusCode\":\"401\"}";
			resp= new ResponseEntity<Object>(response, HttpStatus.OK);
		}
		return resp;

        
    }
}	
