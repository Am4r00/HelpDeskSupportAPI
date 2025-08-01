package com.helpdesk.supportapi.Repository;

import com.helpdesk.supportapi.Model.Called;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Repository para gerenciamento de chamados (tickets) no sistema.
 * Repository for managing tickets (called) in the system.
 */
public interface CalledRepository extends JpaRepository<Called, Long> {

    /**
     * Busca todos os chamados com prioridade crítica.
     * Search all tickets with CRITICAL priority.
     *
     * @return Lista de chamados críticos.
     */
    @Query("SELECT c FROM Called c WHERE c.priority = 'CRITICAL'")
    List<Called> findAllCriticalPriority();

    /**
     * Busca todos os chamados com prioridade crítica ou alta.
     * Search all tickets with CRITICAL or HIGH priority.
     *
     * @return Lista de chamados urgentes.
     */
    @Query("SELECT c FROM Called c WHERE c.priority = 'CRITICAL' OR c.priority = 'HIGH'")
    List<Called> findAllUrgentPriority();

    /**
     * Busca todos os chamados com status resolvido ou fechado.
     * Search all tickets with RESOLVED or CLOSED status.
     *
     * @return Lista de chamados encerrados.
     */
    @Query("SELECT c FROM Called c WHERE c.status = 'RESOLVED' OR c.status = 'CLOSE'")
    List<Called> findAllClosedCalls();

    /**
     * Busca todos os chamados com status aberto.
     * Search all tickets with OPEN status.
     *
     * @return Lista de chamados abertos.
     */
    @Query("SELECT c FROM Called c WHERE c.status = 'OPEN'")
    List<Called> findAllOpenCalls();

    /**
     * Busca todos os chamados com status aberto ou em progresso.
     * Search all tickets with OPEN or IN_PROGRESS status.
     *
     * @return Lista de chamados abertos ou em progresso.
     */
    @Query("SELECT c FROM Called c WHERE c.status = 'OPEN' OR c.status = 'IN_PROGRESS'")
    List<Called> findAllOpenOrInProgressCalls();

    /**
     * Busca todos os chamados por nome da categoria associada.
     * Search all tickets by the associated category name.
     *
     * @param categoryName Nome da categoria.
     * @return Lista de chamados pertencentes à categoria especificada.
     *
     * <b>Nota:</b> O filtro depende do nome digitado corretamente pelo usuário.
     * <b>Note:</b> The filter depends on the user typing the category name correctly.
     */
    @Query("SELECT c FROM Called c WHERE c.category.name = :categoryName")
    List<Called> findByCategory(@Param("categoryName") String categoryName);
}
