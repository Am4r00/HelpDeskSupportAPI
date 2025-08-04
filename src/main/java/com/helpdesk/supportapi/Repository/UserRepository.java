package com.helpdesk.supportapi.Repository;

import com.helpdesk.supportapi.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * Repository para gerenciamento de usuários no sistema.
 * Repository for managing users in the system.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Busca todos os usuários com nome igual ao informado, ignorando maiúsculas/minúsculas.
     * Search all users by name, ignoring case sensitivity.
     *
     * @param name Nome do usuário.
     * @return Lista de usuários correspondentes.
     */
    List<User> findByNameIgnoreCase(String name);

    /**
     * Busca um usuário pelo e-mail.
     * Search a user by email.
     *
     * @param email E-mail do usuário.
     * @return Usuário correspondente ou null se não encontrado.
     */
    User findByEmail(String email);

    /**
     * Busca todos os usuários inativos.
     * Search all users with INACTIVE status.
     *
     * @return Lista de usuários inativos.
     */
    @Query("SELECT u FROM User u WHERE u.status = 'INACTIVE'")
    List<User> findAllInactiveUsers();

    /**
     * Busca todos os usuários ativos.
     * Search all users with ACTIVE status.
     *
     * @return Lista de usuários ativos.
     */
    @Query("SELECT u FROM User u WHERE u.status = 'ACTIVE'")
    List<User> findAllActiveUsers();

    /**
     * Busca todos os usuários com posição ADMIN.
     * Search all users with ADMIN role.
     *
     * @return Lista de usuários administradores.
     */
    @Query("SELECT u FROM User u WHERE u.positions = 'ADMIN'")
    List<User> findAllAdminUsers();

    /**
     * Busca todos os usuários com posição SUPPORT.
     * Search all users with SUPPORT role.
     *
     * @return Lista de usuários de suporte.
     */
    @Query("SELECT u FROM User u WHERE u.positions = 'SUPPORT'")
    List<User> findAllSupportUsers();

    /**
     * Busca todos os usuários com posição CUSTOMER.
     * Search all users with CUSTOMER role.
     *
     * @return Lista de usuários clientes.
     */
    @Query("SELECT u FROM User u WHERE u.positions = 'CUSTOMER'")
    List<User> findAllCustomerUsers();
}
