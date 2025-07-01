package org.example.bsaas.service;

import org.example.bsaas.model.User;
import org.example.bsaas.dto.UserRequestDTO;
import org.example.bsaas.dto.UserResponseDTO;

/**
 * Classe utilitária para conversão entre entidade User e seus DTOs.
 */
public class UserMapper {

    /**
     * Converte a entidade User para UserResponseDTO.
     * @param user entidade User
     * @return DTO de resposta, ou null se user for null
     */
    public static UserResponseDTO toDTO(User user) {
        if (user == null) return null;
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setRole(user.getRole());
        dto.setIsActive(user.getIsActive());
        return dto;
    }

    /**
     * Converte UserRequestDTO para entidade User.
     * @param dto DTO de requisição
     * @return entidade User, ou null se dto for null
     */
    public static User toEntity(UserRequestDTO dto) {
        if (dto == null) return null;
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPasswordHash(dto.getPassword()); // Certifique-se que o hash é tratado no lugar correto!
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setRole(dto.getRole());
        user.setIsActive(dto.getIsActive());
        return user;
    }
}