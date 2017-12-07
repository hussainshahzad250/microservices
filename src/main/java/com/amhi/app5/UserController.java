package com.amhi.app5;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amhi.model.Response;
import com.amhi.model.User;
import com.amhi.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	final static Logger logger = Logger.getLogger(UserController.class);

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		logger.debug("User going to Save ....");
		Response response = null;
		User availUser = userService.getUserByEmail(user.getEmail());
		if (availUser != null) {
			logger.info("User Exist already with this Email");
			response = new Response();
			response.setMessage("User Exist already with this Email");
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}

		boolean savedUser = userService.saveUser(user);
		logger.info("Saved User ==>> " + savedUser);
		if (!savedUser) {
			logger.info("Something Went Wrong");
			response = new Response();
			response.setMessage("Something Went Wrong");
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}
		logger.info("User saved Successfully");
		response = new Response();
		response.setMessage("User saved Successfully");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getUsers() {

		Response response = null;
		List<User> users = userService.getUsers();
		logger.info("User List ==>> " + users);
		if (users == null) {
			response = new Response();
			response.setMessage("User Not Found");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getUserById(@PathVariable Long id) {

		Response response = null;
		User user = userService.getUserById(id);
		logger.info("User List ==>> " + user);
		if (user == null) {
			response = new Response();
			response.setMessage("User Not Found");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(user, HttpStatus.OK);
	}

	@RequestMapping(value = { "/user/{id}" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deletetUserById(@PathVariable Long id) {

		Response response = null;
		boolean deletedUser = userService.deletetUserById(id);
		logger.info("Deleted User ==>> " + deletedUser);
		if (!deletedUser) {
			response = new Response();
			response.setMessage("User Not Found");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
		}
		response = new Response();
		response.setMessage("User Deleted Successfully");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
