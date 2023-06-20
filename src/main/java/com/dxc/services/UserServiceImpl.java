package com.dxc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.exception.IncorrectPasswordException;
import com.dxc.exception.UserExistsException;
import com.dxc.exception.UserNotFoundException;
import com.dxc.model.User;
import com.dxc.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User newUser(User user) throws UserExistsException {
		final boolean existById = this.userRepository.existsById(user.getUserId());
		if(existById) {
			throw new UserExistsException("User Already Exists");
		}
		return this.userRepository.save(user);
	}

	@Override
	public void updateUserById(User user) {
		userRepository.save(user);
	}
	
	@Override
	public void deleteUserById(String userId){
		Optional<User> user=userRepository.findById(userId);
		if(user.isPresent()) {
			userRepository.deleteById(userId);
		
			userRepository.deleteById(userId);
		}
	}

	@Override
	public Optional<User> getUserById(String userId) {
		return userRepository.findById(userId);
	}
	
	@Override
	public String authenticateUser(String userId, String userPassword)
			throws UserNotFoundException, IncorrectPasswordException {
		final Optional<User> optionalUser = this.userRepository.findByUserIdAndUserPassword(userId, userPassword);
		if(optionalUser.isEmpty()) {
			throw new UserNotFoundException("User Not Found");
		}
		return "Logged in";
	}

	@Override
	public String authenticateUserlogout(String userId, String userPassword) throws UserNotFoundException, IncorrectPasswordException{
		
		final Optional<User> optionalUser = this.userRepository.findByUserIdAndUserPassword(userId, userPassword);
		if(optionalUser.isEmpty()) {
			throw new UserNotFoundException("User Not Found");
		}
		return "Logged out";
	}
	
}
