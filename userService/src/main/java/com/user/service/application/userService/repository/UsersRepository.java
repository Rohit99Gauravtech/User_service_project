package com.user.service.application.userService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.user.service.application.userService.model.User;


@Repository
public interface UsersRepository extends PagingAndSortingRepository<User, Integer> {
	public User findByName(String name);
	public User findById(Integer uid);
	public List<User> findByAdddr(String adddr);
	public List<User> findAll();
	public User save(User urd);
	public User deleteById(Integer uid);
	
	
	@Query(value = "Select * from user where name=:name and uid=:uid", nativeQuery = true)
	public List <User> findDetailsByNameAndId(@Param(value="name") String name, @Param(value="uid") Integer uid);

}
