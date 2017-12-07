package com.amhi.app3;

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

@RestController
@RequestMapping("/api")
// @CrossOrigin(origins = { "*" }, maxAge = 6000)
public class UserController {

	@Autowired
	private UserService userService;
	final static Logger logger = Logger.getLogger(UserController.class);

	@RequestMapping(value = "/userList", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> getUsers() {

		Response response = null;
		List<User> users = userService.getUsers();

		if (users == null) {
			response = new Response();
			response.setMessage("User Not Found");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		logger.debug("going to Save ....");

		Response response = null;
		User availUser = userService.getUserByEmail(user.getEmail());
		if (availUser != null) {
			logger.info("User Exist already with this Email");
			response = new Response();
			response.setMessage("User Exist already with this Email");
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}

		boolean saveUser = userService.saveUser(user);
		if (!saveUser) {
			logger.info("Something Went Wrong");
			response = new Response();
			response.setMessage("Something Went Wrong");
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}
		logger.info("Register Successfully");
		response = new Response();
		response.setMessage("Register Successfully");

		return new ResponseEntity<Object>(response, HttpStatus.OK);

	}

	@RequestMapping(value = { "/user/{id}" }, method = RequestMethod.DELETE)
	public ResponseEntity<Object> getUserByEmail(@PathVariable Long id) {

		Response response = null;
		boolean deleteUserById = userService.deleteUserById(id);
		if (!deleteUserById) {
			response = new Response();
			response.setMessage("Something Went Wrong, Not able to delete Now");
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}
		response = new Response();
		response.setMessage("User Deleted Successfully");

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}