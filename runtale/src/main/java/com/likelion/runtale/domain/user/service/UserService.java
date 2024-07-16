package com.likelion.runtale.domain.user.service;

import com.likelion.runtale.domain.user.Exception.UserNotFoundException;
import com.likelion.runtale.domain.user.dto.UserRequest;
import com.likelion.runtale.domain.user.dto.UserResponse;
import com.likelion.runtale.domain.user.entity.User;
import com.likelion.runtale.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User foundUser = user.get();
            return new UserResponse(foundUser.getId(), foundUser.getLoginId(), foundUser.getNickname());
        } else {
            throw new UserNotFoundException("해당 유저는 없습니다. : " + id);
        }
    }

    public UserResponse createUser(UserRequest userRequest) {
        User user = userRequest.toUser();
        validateDuplicateMember(user);
        userRepository.save(user);
        return new UserResponse(user.getId(), user.getLoginId(), user.getNickname());
    }
    private void validateDuplicateMember(User user) {
        List<User> findMembers = userRepository.findByLoginId(user.getLoginId());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 유저아이디입니다.");
        }
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("해당 유저는 없습니다. : " + id);
        }
        User user = optionalUser.get();
        user.setNickname(userRequest.getNickname());
        user.setPassword(userRequest.getPassword());

        User updatedUser = userRepository.save(user);
        return new UserResponse(updatedUser.getId(),updatedUser.getLoginId(), updatedUser.getNickname());
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
