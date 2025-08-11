package com.helpdesk.supportapi.repository;

import com.helpdesk.supportapi.model.entity.CalledInterection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Repository para gerenciamento das interações (mensagens) dentro dos chamados.
 * Repository for managing interactions (messages) inside tickets.
 */
public interface CalledInterectionRepository extends JpaRepository<CalledInterection, Long> {

    /**
     * Busca todas as interações de um chamado específico.
     * Search all interactions of a specific ticket.
     *
     * @param calledID ID do chamado/ticket
     * @return Lista de interações associadas ao chamado
     */
    @Query("SELECT c FROM CalledInterection c WHERE c.called.id = :called_id")
    List<CalledInterection> findAllInteractionsByCalledId(@Param("called_id") Long calledID);

    /**
     * Busca todas as interações de um usuário específico, em ordem cronológica (mais antigas primeiro).
     * Search all interactions of a specific user, in chronological order (oldest first).
     *
     * @param userID ID do usuário
     * @return Lista de interações do usuário em ordem cronológica
     */
    @Query("SELECT c FROM CalledInterection c WHERE c.user.id = :user_id ORDER BY c.dateHour ASC")
    List<CalledInterection> findAllInteractionsByUserIdOrderedByDate(@Param("user_id") Long userID);

    /**
     * Busca todas as interações de um chamado específico, em ordem cronológica (mais antigas primeiro).
     * Search all interactions of a specific ticket, in chronological order (oldest first).
     *
     * @param calledId ID do chamado/ticket
     * @return Lista de interações do chamado em ordem cronológica
     */
    List<CalledInterection> findByCalledIdOrderByDateHourAsc(Long calledId);

    /**
     * Busca todas as interações de um usuário específico, em ordem cronológica (mais antigas primeiro).
     * Search all interactions of a specific user, in chronological order (oldest first).
     *
     * @param userId ID do usuário
     * @return Lista de interações do usuário em ordem cronológica
     */
    List<CalledInterection> findByUserIdOrderByDateHourAsc(Long userId);

    /**
     * Busca a última interação registrada em um chamado específico.
     * Search the latest interaction of a specific ticket.
     *
     * @param calledId ID do chamado/ticket
     * @return Última interação do chamado
     */
    CalledInterection findFirstByCalledIdOrderByDateHourDesc(Long calledId);
}
