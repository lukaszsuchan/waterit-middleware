package agh.iss.wateritmiddleware.auth;

import agh.iss.wateritmiddleware.auth.model.AuthenticationRequest;
import agh.iss.wateritmiddleware.auth.model.AuthenticationResponse;
import agh.iss.wateritmiddleware.auth.model.RegisterRequest;
import agh.iss.wateritmiddleware.config.JwtService;
import agh.iss.wateritmiddleware.exception.CoreException;
import agh.iss.wateritmiddleware.exception.ErrorCode;
import agh.iss.wateritmiddleware.exception.ErrorSubcode;
import agh.iss.wateritmiddleware.user.Role;
import agh.iss.wateritmiddleware.user.User;
import agh.iss.wateritmiddleware.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .enabled(true)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        //TODO: save Token?
//        var token = Token.builder()
//                .user(user)
//                .token(jwtToken)
//                .tokenType(TokenType.BEARER)
//                .expired(false)
//                .revoked(false)
//                .build();
//        tokenRepository.save(token);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
        
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UserDetails user = userRepository.findUserDetailsByUsername(request.getUsername())
                .orElseThrow(() -> new CoreException(ErrorCode.NOT_FOUND, ErrorSubcode.USER_NOT_FOUND));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword(),
                        user.getAuthorities()
                )
        );
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
}
