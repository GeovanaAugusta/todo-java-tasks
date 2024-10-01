package br.com.geovanaaugusta.itau.tarefasservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.geovanaaugusta.itau.tarefasservice.model.Task;
import br.com.geovanaaugusta.itau.tarefasservice.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável pelo gerenciamento de tarefas, incluindo operações de criação, leitura, 
 * atualização e exclusão (CRUD).
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Retorna todas as tarefas existentes.
     *
     * @return uma lista de todas as tarefas
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Retorna uma tarefa específica com base no seu ID.
     *
     * @param id o ID da tarefa
     * @return um {@link Optional} contendo a tarefa se encontrada, ou vazio se não encontrada
     */
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    /**
     * Retorna todas as tarefas associadas a um usuário específico com base no ID do usuário.
     *
     * @param usuarioId o ID do usuário
     * @return uma lista de tarefas do usuário
     */
    public List<Task> getTasksByUserId(Long usuarioId) {
        return taskRepository.findByUsuarioId(usuarioId);
    }

    /**
     * Cria uma nova tarefa.
     *
     * @param task a tarefa a ser criada
     * @return a tarefa criada
     */
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Atualiza uma tarefa existente com base no seu ID.
     *
     * @param id o ID da tarefa a ser atualizada
     * @param task a tarefa com os novos dados
     * @return a tarefa atualizada, ou null se a tarefa não for encontrada
     */
    public Task updateTask(Long id, Task task) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            task.setId(id);
            return taskRepository.save(task);
        }
        return null;
    }

    /**
     * Exclui uma tarefa com base no seu ID.
     *
     * @param id o ID da tarefa a ser excluída
     */
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
