package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public int addUser(int user_id ,String password,String user_name ,String email_id) {
	    return jdbcTemplate.update(
	      "INSERT INTO user VALUES (?, ?, ?, ?)", user_id, password, user_name, email_id);
	}
	
	public User getUser(int user_id , String password) {
		String query = "SELECT * FROM user WHERE user_id = ? and password = ?";
		User user = null;
		try {
			 user = jdbcTemplate.queryForObject(query, new Object[] { user_id , password  }, new UserRowMapper());
		}catch(Exception ex) {
			
		}
		return user;
	}
}
