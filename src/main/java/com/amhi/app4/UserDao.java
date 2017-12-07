package com.amhi.app4;

import java.util.List;

public interface UserDao {

	boolean saveUser(User user);

	User getUserByEmail(String email);

	User login(String email, String password);

	List<User> getUsers();

	boolean deletetUserById(String id);
}
