package agh.iss.wateritmiddleware.user;

import agh.iss.wateritmiddleware.exception.CoreException;
import agh.iss.wateritmiddleware.exception.ErrorCode;
import agh.iss.wateritmiddleware.exception.ErrorSubcode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUser {

    private final UserRepository userRepository;

    public User getUserInfo() {
        final var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CoreException(ErrorCode.NOT_FOUND, ErrorSubcode.USER_NOT_FOUND));
    }
}
