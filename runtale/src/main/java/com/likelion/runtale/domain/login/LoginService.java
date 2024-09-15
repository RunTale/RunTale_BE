package com.likelion.runtale.domain.login;

import com.likelion.runtale.domain.user.entity.User;
import com.likelion.runtale.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    //
    private final UserRepository userRepository;

    public User login(String loginId, String password) {
        return userRepository.findByLoginId(loginId).stream()
                .filter(m -> m.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}
