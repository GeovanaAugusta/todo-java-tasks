package br.com.geovanaaugusta.itau.tarefasservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.geovanaaugusta.itau.tarefasservice.model.Task;

import java.util.List;

/**
 * Repositório para a entidade {@link Task}, fornecendo operações CRUD e métodos personalizados para acesso ao banco de dados.
 * 
 * Esta interface herda de {@link JpaRepository}, que fornece uma implementação padrão das operações CRUD.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Encontra todas as tarefas atribuídas a um determinado usuário.
     *
     * @param usuarioId o ID do usuário
     * @return uma lista de tarefas pertencentes ao usuário com o ID especificado
     */
    List<Task> findByUsuarioId(Long usuarioId);
}
