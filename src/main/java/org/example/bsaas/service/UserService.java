package org.example.bsaas.service;

import org.example.bsaas.exception.ResourceNotFoundException;
import org.example.bsaas.model.User;
import org.example.bsaas.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
     * Retorna todos os usuários cadastrados.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Busca um usuário pelo id.
     * @throws ResourceNotFoundException se não existir
     */
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
    }

    /**
     * Cria um novo usuário após validar campos obrigatórios.
     */
    @Transactional
    public User createUser(User user) {
        validarCamposObrigatorios(user);
        return userRepository.save(user);
    }

    /**
     * Atualiza um usuário existente.
     * @throws ResourceNotFoundException se não existir
     */
    @Transactional
    public User updateUser(Integer id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
        // Atualiza apenas campos não nulos (atualização parcial)
        if (userDetails.getName() != null) {
            user.setName(userDetails.getName());
        }
        if (userDetails.getEmail() != null) {
            user.setEmail(userDetails.getEmail());
        }
        // Adicione outros campos conforme necessário
        validarCamposObrigatorios(user);
        return userRepository.save(user);
    }

    /**
     * Remove um usuário por id.
     * @throws ResourceNotFoundException se não existir
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
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("E-mail é obrigatório");
        }
        // Adicione outras validações conforme necessário
    }
}