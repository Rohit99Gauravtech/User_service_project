package com.user.service.application.userService.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.user.service.application.userService.exception.UserNotFoudException;
import com.user.service.application.userService.model.User;
import com.user.service.application.userService.repository.UsersRepository;



@Service
public class UserService {
	
	@Autowired
	public UsersRepository repo;
	
	List<User> ul = new ArrayList<>();

	public List<User> getUser(){
		return (List<User>) repo.findAll();
	}
	
	public List<User> getUserByPage(int pageNo, int pageSize){
		Pageable pageable =PageRequest.of(pageNo, pageSize);
		Page<User> page = repo.findAll(pageable);
		if(page.getContent().isEmpty())
			throw new RuntimeException("no recards for this page"); 
		return page.toList();
	}
	
//	public List<User> getUserBySort(){
//		return (List<User>) repo.findAll(Sort.by("name").ascending()); 
//	}
	
//	public List<User> getUserBySort(String sort){
//		return (List<User>) repo.findAll(Sort.by(sort).ascending()); 
//	}
//	
//	
	
	public List<User> getUserBySort(String sort, String asc){
		if(asc.equalsIgnoreCase("dsc"))
			return (List<User>) repo.findAll(Sort.by(sort).descending()); 
		return (List<User>) repo.findAll(Sort.by(sort).ascending()); 
	}
	
	
	public User getUserById(Integer uid) {
		return repo.findById(uid);
	}

	public User getUserByName(String name) {
		return repo.findByName(name);	
	}
	
	public List<User> findUserByAdddr(String adddr) {
		return repo.findByAdddr(adddr);
	}
	
	public List<User> findDetailsByNameAndId(String name, Integer uid){
		return repo.findDetailsByNameAndId(name, uid);
		
	}
	
	public User insertUser(User urd) {
		return repo.save(urd);
	}
	
	public User updateUser(Integer uid, User urd){
		 User existing = this.getUserById(uid);
		 
		 if(urd.getName()!=null)
			 existing.setName(urd.getName());
		 
		 else if(urd.getAdddr()!=null) 
			 existing.setAdddr(urd.getAdddr());
		 
			 return repo.save(existing);
			 
		}
		 
		public String deleteUser(Integer uid) {
			repo.deleteById(uid);
			return "Product deleted successfully with id" +uid;
			
		}

}
