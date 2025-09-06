package com.user.service.application.userService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.service.application.userService.dto.OrdersDto;
import com.user.service.application.userService.dto.UserDto;
import com.user.service.application.userService.model.User;
import com.user.service.application.userService.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService service;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@Value("${orderservice.url}")
	String orderservice;
	
	
	@GetMapping("/home")
	public String hello() {
		return "<h1>gteets from user service</h1>";
	}
	
	
	
	
	@GetMapping(value = "/calling")
	public ResponseEntity<String> geet(){
	  String resp= restTemplate.getForObject(orderservice, String.class);
		return new ResponseEntity<String>(resp, HttpStatus.OK);	
	}
	
	
	@GetMapping("/userdetails/{uid}")
	public ResponseEntity<UserDto> getUserDetails(@PathVariable Integer uid){
		UserDto dto = new UserDto();
		User user= service.getUserById(uid);
		dto.setUid(user.getUid());
		dto.setName(user.getName());
		dto.setAdddr(user.getAdddr());
		List<OrdersDto> orders = restTemplate.getForObject(orderservice+"/orderstat/"+uid, List.class);
		dto.setOrders(orders);
		return new ResponseEntity<UserDto>(dto, HttpStatus.OK); 
	}
	
	
	
//	@GetMapping(value = "/greet",produces = org.springframework.http.MediaType.TEXT_PLAIN_VALUE)
//	public ResponseEntity<String> geet(String name){
//		return new ResponseEntity<String>("<h1>hello microservices application</h1>", HttpStatus.OK);	
//	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUser(){
		return new ResponseEntity<List<User>>(service.getUser(), HttpStatus.OK); 
	}
	
	
	@GetMapping("/userPage/{pageSize}/{pageNo}")
	public ResponseEntity<List<User>> getUserByPage(@PathVariable int pageSize,@PathVariable int pageNo){
		return new ResponseEntity<List<User>>(service.getUserByPage(pageNo, pageSize),HttpStatus.OK);
	}
	
	
//	@GetMapping("/usersort")
//	public ResponseEntity<List<User>> getUserBySort(){
//		return new ResponseEntity<List<User>>(service.getUserBySort(), HttpStatus.OK);
//	}
//	
	
//	@GetMapping("/usersort/{sort}")
//	public ResponseEntity<List<User>> getUserBySort(@PathVariable String sort){
//		return new ResponseEntity<List<User>>(service.getUserBySort(sort), HttpStatus.OK);
//	}
	
	
	@GetMapping("/usersort/{sort}/{asc}") 
	public ResponseEntity<List<User>> getUserBySort(@PathVariable String sort, @PathVariable String asc){
		return new ResponseEntity<List<User>>(service.getUserBySort(sort, asc), HttpStatus.OK);
	}
	
	
	
	@GetMapping("/user1/{uid}")
	public ResponseEntity<User> getUserById(@PathVariable Integer uid) {
		return new ResponseEntity<User>(service.getUserById(uid), HttpStatus.OK);   
	}
	
	@GetMapping("/user2/{name}")
	public ResponseEntity<User> getUserByName(@PathVariable String name) {
		return new ResponseEntity<User>(service.getUserByName(name), HttpStatus.OK); 
	}

	
	@GetMapping("/user3/{name}/{id}")
	public ResponseEntity<List<User>> getUserByNameAndId(@PathVariable String name, @PathVariable Integer uid){
		return new ResponseEntity<List<User>>(service.findDetailsByNameAndId(name, uid),HttpStatus.OK); 
	}  
	
	
	
	
	@GetMapping("/user4/{adddr}")
	public ResponseEntity<List<User>> getUserByAdddr(@PathVariable String adddr) {
		return new ResponseEntity<List<User>>(service.findUserByAdddr(adddr), HttpStatus.OK); 
	}
	

	// for the receive data in text form we will use consume in bellow method //
//	@PostMapping(value = "/saves", consumes = org.springframework.http.MediaType.TEXT_PLAIN_VALUE)
	@PostMapping("/saves")
	public ResponseEntity<User> insertUser(@RequestBody User urd) {
		return new ResponseEntity<User>(service.insertUser(urd), HttpStatus.CREATED); 
	}
	

	@PutMapping("/User5/{uid}")
	public ResponseEntity<User> updateUser(@PathVariable Integer uid, @RequestBody User urd) {
		return new ResponseEntity<User>(service.updateUser(uid, urd), HttpStatus.CREATED) ;	
	}
	
	
	@DeleteMapping("delete/{uid}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer uid) {
		return new ResponseEntity<String>(service.deleteUser(uid), HttpStatus.ACCEPTED); 
	}
	
}
