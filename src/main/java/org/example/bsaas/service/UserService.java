package org.example.bsaas.service;

import org.example.bsaas.dto.UserRequestDTO;
import org.example.bsaas.dto.UserResponseDTO;
import org.example.bsaas.exception.ResourceNotFoundException;
import org.example.bsaas.model.User;
import org.example.bsaas.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pelas operações relacionadas à entidade User.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retorna todos os usuários cadastrados como DTOs.
     */
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca um usuário pelo id e retorna como DTO.
     */
    public UserResponseDTO getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
        return UserMapper.toDTO(user);
    }

    /**
     * Cria um novo usuário a partir de um DTO.
     */
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO request) {
        User user = UserMapper.toEntity(request);
        validarCamposObrigatorios(user);
        user = userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    /**
     * Atualiza um usuário existente a partir de um DTO.
     */
    @Transactional
    public UserResponseDTO updateUser(Integer id, UserRequestDTO request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
        // Atualiza apenas campos não nulos (atualização parcial)
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }
        if (request.getIsActive() != null) {
            user.setIsActive(request.getIsActive());
        }
        if (request.getPassword() != null) {
            user.setPasswordHash(request.getPassword());
        }
        validarCamposObrigatorios(user);
        user = userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    /**
     * Remove um usuário por id.
     */
    @Transactional
    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado com id: " + id);
        }
        userRepository.deleteById(id);
    }

    /**
     * Valida campos obrigatórios do usuário.
     */
    private void validarCamposObrigatorios(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username é obrigatório");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("E-mail é obrigatório");
        }
        // Adicione outras validações conforme necessário
    }
}