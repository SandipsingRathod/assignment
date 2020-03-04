package com.uxpsystems.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.exception.ResourceNotFoundException;
import com.uxpsystems.model.User;
import com.uxpsystems.repository.UserRepository;

/**
 * @author Sandip Rathod
 *
 */
@RestController
@RequestMapping("/assignement")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String getString() {
		return "Hello sandy";
	}

	@GetMapping("/allusers")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getAllEmployees() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<User> getEmployeeById(@PathVariable(value = "id") Long userId)
			throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + userId));
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/saveusers")
	@PreAuthorize("hasRole('ADMIN')")
	public User createEmployee(@Valid @RequestBody User user) {

		String encoded = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(encoded);

		log.info("saving user with username " + user.getUsername());
		return userRepository.save(user);
	}

	@PutMapping("/updateuser/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<User> updateEmployee(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + userId));

		user.setUsername(userDetails.getUsername());
		user.setPassword(userDetails.getPassword());
		user.setEmail(userDetails.getEmail());
		user.setStatus(userDetails.getStatus());

		final User updatedUser = userRepository.save(user);
		log.info("updating user with username " + user.getUsername());
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/deleteuser/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long userId)
			throws ResourceNotFoundException {
		User employee = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + userId));

		userRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		log.info("deleting user having user Id " + userId);
		return response;
	}

}

