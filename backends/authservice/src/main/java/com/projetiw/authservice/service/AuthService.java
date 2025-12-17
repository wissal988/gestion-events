package com.projetiw.authservice.service;

import com.projetiw.authservice.dto.LoginRequest;
import com.projetiw.authservice.dto.RegisterRequest;
import com.projetiw.authservice.dto.AuthResponse;
import com.projetiw.authservice.model.User;
import com.projetiw.authservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse("Email déjà utilisé", null, null);
        }

        // USER par défaut
        User user = new User(
                request.getFullName(),
                request.getEmail(),
                request.getPassword(),
                "USER"
        );

        userRepository.save(user);

        return new AuthResponse("Inscription réussie", user.getRole(), user.getId());
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());

        if (user == null || !user.getPassword().equals(request.getPassword())) {
            return new AuthResponse("Email ou mot de passe invalide", null, null);
        }

        return new AuthResponse("Connexion réussie", user.getRole(), user.getId());
    }
}
