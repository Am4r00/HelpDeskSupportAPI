package com.helpdesk.supportapi.repository;

import com.helpdesk.supportapi.model.entity.TicketHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TicketHistoryRepository extends JpaRepository<TicketHistory, Long> {

    Optional<TicketHistory> findFirstByTicket_IdOrderByCreatedAtDesc(Long ticketId);

    /***
     * Busca td historico do chamado em ordem cronológia em forma de páginas
     * para melhorar a organização dos registros
     * Searches the entire call history in chronological order in page format
     * to improve record organization
     *
     * @param TicketId ID do chamado/ticket
     * @return Lista de histórico do chamado em ordem cronológica
     */
    Page<TicketHistory> findByTicketId(Long TicketId, Pageable pageable);

    /***
     * Busca pelo ultimo evento do histórico do chamado
     * Search for the last event in the call history
     * @param TicketId ID do chamado
     * @return retorna uma ultima interação do chamado
     */
    @EntityGraph(attributePaths = {"users"})
    Optional<TicketHistory> findTopByTicketIdOrderByCreatedAtDesc(Long TicketId);

}
