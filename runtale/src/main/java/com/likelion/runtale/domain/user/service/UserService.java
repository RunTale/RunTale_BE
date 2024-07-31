package com.likelion.runtale.domain.user.service;

import com.likelion.runtale.common.exception.BadRequestException;
import com.likelion.runtale.common.exception.NotFoundException;
import com.likelion.runtale.common.response.ErrorMessage;
import com.likelion.runtale.domain.tier.service.TierService;
import com.likelion.runtale.domain.user.dto.UserRequest;
import com.likelion.runtale.domain.user.dto.UserResponse;
import com.likelion.runtale.domain.user.entity.User;
import com.likelion.runtale.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TierService tierService;


    public UserResponse getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User foundUser = user.get();
            return new UserResponse(foundUser.getId(), foundUser.getLoginId(), foundUser.getNickname());
        } else {
            throw new NotFoundException(ErrorMessage.USER_NOT_EXIST);
        }
    }

    // User 엔티티를 직접 반환하는 메서드 추가
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.USER_NOT_EXIST));
    }
    // 회원가입
    public UserResponse createUser(UserRequest userRequest) {
        User user = userRequest.toUser();
        validateDuplicateMember(user);
        userRepository.save(user);

        // 회원가입 후 디폴트 티어 할당
        tierService.assignDefaultTierToUser(user);

        return new UserResponse(user.getId(), user.getLoginId(), user.getNickname());
    }

    private void validateDuplicateMember(User user) {
        List<User> findMembers = userRepository.findByLoginId(user.getLoginId());
        if (!findMembers.isEmpty()) {
            throw new BadRequestException(ErrorMessage.USER_LOGIN_ID_VALIDATE);
        }
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new NotFoundException(ErrorMessage.USER_NOT_EXIST);
        }
        User user = optionalUser.get();
        user.setNickname(userRequest.getNickname());
        user.setPassword(userRequest.getPassword());

        User updatedUser = userRepository.save(user);
        return new UserResponse(updatedUser.getId(),updatedUser.getLoginId(), updatedUser.getNickname());
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException(ErrorMessage.USER_NOT_EXIST);
        }
        userRepository.deleteById(id);
    }
}
