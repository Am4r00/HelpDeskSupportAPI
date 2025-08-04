package com.helpdesk.supportapi.Repository;

import com.helpdesk.supportapi.Model.Entity.CallHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CallHistoryRepository extends JpaRepository<CallHistory, Long> {
    /***
     * Busca todo historico do chamado em ordem cronológia em forma de páginas
     * para melhorar a organização dos registros
     * Searches the entire call history in chronological order in page format
     * to improve record organization
     *
     * @param calledId ID do chamado/ticket
     * @return Lista de histórico do chamado em ordem cronológica
     */
    Page<CallHistory> findByCalledId(Long calledId,  Pageable pageable);

    /***
     * Busca pelo ultimo evento do histórico do chamado
     * Search for the last event in the call history
     * @param calledId ID do chamado
     * @return retorna uma ultima interação do chamado
     */
    @EntityGraph(attributePaths = {"user"})
    Optional<CallHistory> findByTopByCalledIdOrderByCreatedAtDesc(Long calledId);
}
