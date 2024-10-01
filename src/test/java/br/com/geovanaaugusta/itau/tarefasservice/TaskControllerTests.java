package br.com.geovanaaugusta.itau.tarefasservice;

import br.com.geovanaaugusta.itau.tarefasservice.controller.TaskController;
import br.com.geovanaaugusta.itau.tarefasservice.model.Task;
import br.com.geovanaaugusta.itau.tarefasservice.service.S3Service;
import br.com.geovanaaugusta.itau.tarefasservice.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @MockBean
    private S3Service s3Service;

    @Test
    void testGetAllTasks() throws Exception {
        Task task1 = new Task(1L, "Task 1", "Description 1", LocalDateTime.now(), null, List.of(), 1L);
        Task task2 = new Task(2L, "Task 2", "Description 2", LocalDateTime.now(), null, List.of(), 2L);
        List<Task> mockTasks = List.of(task1, task2);

        Mockito.when(taskService.getAllTasks()).thenReturn(mockTasks);

        mockMvc.perform(get("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].nome", is("Task 1")))
                .andExpect(jsonPath("$[0].descricao", is("Description 1")))
                .andExpect(jsonPath("$[1].nome", is("Task 2")))
                .andExpect(jsonPath("$[1].descricao", is("Description 2")));
    }

    @Test
    void testGetTaskById_Found() throws Exception {
        Task task = new Task(1L, "Task 1", "Description 1", LocalDateTime.now(), null, List.of(), 1L);

        Mockito.when(taskService.getTaskById(1L)).thenReturn(Optional.of(task));

        mockMvc.perform(get("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Task 1")))
                .andExpect(jsonPath("$.descricao", is("Description 1")));
    }

    @Test
    void testGetTaskById_NotFound() throws Exception {
        Mockito.when(taskService.getTaskById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/tasks/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetTasksByUserId() throws Exception {
        Task task1 = new Task(1L, "Task 1", "Description 1", LocalDateTime.now(), null, List.of(), 1L);
        Task task2 = new Task(2L, "Task 2", "Description 2", LocalDateTime.now(), null, List.of(), 1L);
        List<Task> mockTasks = List.of(task1, task2);

        Mockito.when(taskService.getTasksByUserId(1L)).thenReturn(mockTasks);

        mockMvc.perform(get("/api/tasks/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].usuarioId", is(1)))
                .andExpect(jsonPath("$[1].usuarioId", is(1)));
    }
}
