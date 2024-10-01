package br.com.geovanaaugusta.itau.tarefasservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação TarefasService.
 * 
 * Esta classe inicializa a aplicação Spring Boot para o gerenciamento de tarefas.
 */
@SpringBootApplication
public class TarefasServiceApplication {

    /**
     * Método principal que inicia a aplicação Spring Boot.
     *
     * @param args argumentos de linha de comando
     */
    public static void main(String[] args) {
        SpringApplication.run(TarefasServiceApplication.class, args);
    }
}
