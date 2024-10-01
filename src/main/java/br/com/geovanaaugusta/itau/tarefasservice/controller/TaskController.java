package br.com.geovanaaugusta.itau.tarefasservice.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.geovanaaugusta.itau.tarefasservice.model.Task;
import br.com.geovanaaugusta.itau.tarefasservice.service.S3Service;
import br.com.geovanaaugusta.itau.tarefasservice.service.TaskService;
import jakarta.validation.Valid;

/**
 * Controlador para operações de CRUD e upload de arquivos relacionados às tarefas.
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private S3Service s3Service;

    /**
     * Retorna todas as tarefas existentes.
     *
     * @return uma lista de tarefas
     */
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    /**
     * Retorna uma tarefa específica com base no seu ID.
     *
     * @param id o ID da tarefa
     * @return a tarefa encontrada ou 404 se não for encontrada
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retorna todas as tarefas associadas a um usuário específico.
     *
     * @param userId o ID do usuário
     * @return uma lista de tarefas do usuário
     */
    @GetMapping("/user/{userId}")
    public List<Task> getTasksByUserId(@PathVariable Long userId) {
        return taskService.getTasksByUserId(userId);
    }

    /**
     * Cria uma nova tarefa.
     *
     * @param task o objeto de tarefa a ser criado
     * @return a tarefa criada
     */
    @PostMapping
    public Task createTask(@Valid @RequestBody Task task) {
        return taskService.createTask(task);
    }

    /**
     * Atualiza uma tarefa existente com base no seu ID.
     *
     * @param id o ID da tarefa a ser atualizada
     * @param task o objeto de tarefa atualizado
     * @return a tarefa atualizada
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    /**
     * Exclui uma tarefa com base no seu ID.
     *
     * @param id o ID da tarefa a ser excluída
     * @return uma resposta sem conteúdo
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Faz o upload de um arquivo para o S3 e retorna a URL do arquivo carregado.
     *
     * @param file o arquivo a ser carregado
     * @return a URL do arquivo carregado ou uma mensagem de erro
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = s3Service.uploadFile(file);
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Erro ao fazer upload do arquivo: " + e.getMessage());
        }
    }
}
