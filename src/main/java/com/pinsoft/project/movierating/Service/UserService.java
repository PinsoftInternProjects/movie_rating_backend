package com.pinsoft.project.movierating.Service;

import com.pinsoft.project.movierating.DTO.UserUpdateDto;
import com.pinsoft.project.movierating.Entity.User;
import com.pinsoft.project.movierating.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void activateUser(Long id) {
        User user = userRepository.findById(id).get();
        user.setAccount_active(true);
        userRepository.save(user);
    }

    public void deactivateUser(Long id) {
        User user = userRepository.findById(id).get();
        user.setAccount_active(false);
        userRepository.save(user);
    }

    public void updateUser(UserUpdateDto userUpdateDto) {
        Optional<User> userOptional = userRepository.findById(userUpdateDto.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (userUpdateDto.getUsername() != null) {
                user.setUsername(userUpdateDto.getUsername());
            }
            if (userUpdateDto.getEmail() != null) {
                user.setEmail(userUpdateDto.getEmail());
            }
            if (userUpdateDto.getPassword() != null) {
                user.setPassword(userUpdateDto.getPassword());
            }
            if (userUpdateDto.getBase64image() != null) {
                user.setBase64image(userUpdateDto.getBase64image());
            }
            userRepository.save(user);
        } else {
            throw new EntityNotFoundException("User not found with id: " + userUpdateDto.getId());
        }
    }
}
