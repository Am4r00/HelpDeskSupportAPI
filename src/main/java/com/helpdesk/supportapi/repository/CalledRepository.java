package com.helpdesk.supportapi.repository;

import com.helpdesk.supportapi.model.entity.Called;
import com.helpdesk.supportapi.model.enums.Priority;
import com.helpdesk.supportapi.model.enums.Status;
import org.springframework.data.jpa.repository.EntityGraph;
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
     * Busca todos os chamados com  a prioridade passada pelo parâmetro.
     * Search for all tickets with the priority passed by the parameter.
     *
     * @return Lista de chamados críticos.
     */
    @EntityGraph(attributePaths = {"category", "responsibleUser"})
    List<Called> findAllByPriority(Priority priority);

    /**
     * Busca todos os chamados com prioridade passada pelo parâmetro.
     * Search  for all tickets with the priorities passed by parameter.
     *
     * @return Lista de chamados críticos.
     */
    @EntityGraph(attributePaths = {"category", "responsibleUser"})
    List<Called> findAllByPriorityIn(List<Priority> priorities);

    /**
     * Busca todos os chamados com o status passado pelo parâmetro
     * Searches for all calls with the status passed by the parameter
     *
     * @return Lista de chamados com o status passado.
     */
    @EntityGraph(attributePaths = {"category", "responsibleUser"})
    List<Called> findAllByStatus(Status status);

    /**
     * Busca todos os chamados com os status passados pelo parâmetro
     * Searches for all calls with the statuses passed by the parameter
     *
     * @return Lista de chamados com os status passados.
     */
    @EntityGraph(attributePaths = {"category", "responsibleUser"})
    List<Called> findAllByStatusIn(List<Status> statusList);

    /**
     * Busca todos os chamados por nome da categoria associada.
     * Search all tickets by the associated category name.
     *
     * @param categoryName Nome da categoria.
     * @return Lista de chamados pertencentes à categoria especificada.
     *
     * <b>Nota:</b> O filtro depende do nome digitado corretamente pelo usuário,nescessário adicionar % na passada do nome .
     * <b>Note:</b> The filter depends on the user typing the category name correctly.
     */
    @Query("SELECT c FROM Called c WHERE LOWER(c.category.name) LIKE LOWER(:categoryName)")
    @EntityGraph(attributePaths = {"category", "responsibleUser"})
    List<Called> findByCategoryIgnoreCase(@Param("categoryName") String categoryName);
}
