package com.example.order_jpa.Service;

import com.example.order_jpa.dto.UserCreateDto;
import com.example.order_jpa.dto.UserUpdateDto;
import com.example.order_jpa.entity.User;
import com.example.order_jpa.entity.UserType;
import com.example.order_jpa.repository.JPAUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final JPAUserRepository userRepository;

//    public UserService(JPAUserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

//    @Transactional(readOnly = true)
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

//    @Transactional(readOnly = true)
    public User getUserInfo(Long id) {
        return userRepository.findById(id);
    }

    public void addUser(User user) {
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
}