package br.com.geovanaaugusta.itau.tarefasservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidade que representa uma Tarefa no sistema.
 * Cada tarefa está associada a um usuário e pode conter informações de nome, descrição, 
 * horários de início e fim, anexos e o ID do usuário responsável.
 */
@Entity
@Table(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    /**
     * Identificador único da tarefa (gerado automaticamente).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome da tarefa.
     * Este campo é obrigatório.
     */
    @NotEmpty(message = "Nome é obrigatório")
    private String nome;

    /**
     * Descrição detalhada da tarefa (opcional).
     */
    private String descricao;

    /**
     * Data e hora de início da tarefa (opcional).
     */
    private LocalDateTime inicio;

    /**
     * Data e hora de fim da tarefa (opcional).
     */
    private LocalDateTime fim;

    /**
     * Lista de URLs ou caminhos de anexos relacionados à tarefa.
     * Pode ser usado para armazenar referências a arquivos.
     */
    @ElementCollection
    private List<String> anexos;

    /**
     * ID do usuário ao qual a tarefa está atribuída.
     * Este campo define o vínculo entre a tarefa e um usuário específico.
     */
    private Long usuarioId;

}
