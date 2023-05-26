package agh.iss.wateritmiddleware.auth;

import agh.iss.wateritmiddleware.auth.model.AuthenticationRequest;
import agh.iss.wateritmiddleware.auth.model.AuthenticationResponse;
import agh.iss.wateritmiddleware.auth.model.RegisterRequest;
import agh.iss.wateritmiddleware.user.Role;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "create/register user")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request, Role.USER));
    }

    @Operation(summary = "register device for authentication")
    @PostMapping("register/device")
    public ResponseEntity<AuthenticationResponse> registerDevice(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request, Role.DEVICE));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
    }
}
