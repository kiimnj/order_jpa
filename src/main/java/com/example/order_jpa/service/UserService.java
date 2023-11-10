package com.example.order_jpa.service;

import com.example.order_jpa.dto.UserUpdateDto;
import com.example.order_jpa.entity.User;
import com.example.order_jpa.entity.UserType;
import com.example.order_jpa.repository.JPAUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final JPAUserRepository userRepository;

//    public UserService(JPAUserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

//    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//    @Transactional(readOnly = true)
    public User getUserInfo(Long id) {
        return userRepository.findById(id);
    }

    public void addUser(User user) { // 일반 사용자 - 고객
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        if (!byEmail.isEmpty()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        user.setUserType(UserType.BASIC);
        userRepository.save(user);
    }

    public void updateUser(UserUpdateDto userUpdateDto) {
        User findUser = userRepository.findById(userUpdateDto.getUserId());
        findUser.setName(userUpdateDto.getName());
        userRepository.save(findUser);
    }

    public void deleteUser(Long id) {
        User findUser = userRepository.findById(id);
        userRepository.delete(findUser);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId);
    }
}