package com.helpdesk.supportapi.Repository;

import com.helpdesk.supportapi.Model.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/***
 * Ligação simples para acessos a métodos básicos
 * Simple binding for basic method access
 */
public interface CategoryRepository extends JpaRepository<Category, Long > {

}
