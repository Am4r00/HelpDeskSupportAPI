package com.helpdesk.supportapi.Repository;

import com.helpdesk.supportapi.Model.Entity.User;
import com.helpdesk.supportapi.Model.Enums.Position;
import com.helpdesk.supportapi.Model.Enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

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
   Optional<User> findByEmail(String email);

    /**
     * Busca todos os usuários inativos.
     * Search all users with INACTIVE status.
     *
     * @return Lista de usuários inativos.
     */
    default  List<User> findAllInactiveUsers(){
        return findAllByStatus(Status.INACTIVE);
    }

    /**
     * Busca todos os usuários ativos.
     * Search all users with ACTIVE status.
     *
     * @return Lista de usuários ativos.
     */
    default  List<User> findAllActiveUsers(){
        return findAllByStatus(Status.ACTIVE);
    }

    /***
     * Chamada padrão pora status
     * @param status
     * @return uma busca inspirada no parâmetro
     */
    List<User> findAllByStatus(Status status);

    /**
     * Busca todos os usuários com posição ADMIN.
     * Search all users with ADMIN role.
     *
     * @return Lista de usuários administradores.
     */
    default  List<User> findAllAdminUsers(){
            return findAllByPosition(Position.ADMIN);
    }

    /**
     * Busca todos os usuários com posição SUPPORT.
     * Search all users with SUPPORT role.
     *
     * @return Lista de usuários de suporte.
     */

    default  List<User> findAllSupportUsers(){
        return findAllByPosition(Position.SUPPORT);
    }

    /**
     * Busca todos os usuários com posição CUSTOMER.
     * Search all users with CUSTOMER role.
     *
     * @return Lista de usuários clientes.
     */
    default  List<User> findAllCustumerUsers(){
        return findAllByPosition(Position.CUSTOMER);
    }

    /***
     * Chamada padrão pora position
     * @param position
     * @return uma busca inspirada no parâmetro
     */
    @Query("SELECT DISTINCT u FROM User u JOIN u.positions p WHERE p = :position")
    List<User> findAllByPosition(@Param("position") Position position);
}
