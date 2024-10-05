package controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import model.Task;
import model.TaskCategory; // Import necessário para o enum
import repository.TaskRepository;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository; 

    // Listar todas as tarefas
    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        return ResponseEntity.ok(taskRepository.findAll());
    }

    // Obter uma tarefa pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Buscar tarefas por título
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Task>> getByTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(taskRepository.findAllByTituloContainingIgnoreCase(titulo));
    }

    // Criar uma nova tarefa
    @PostMapping
    public ResponseEntity<Task> post(@Valid @RequestBody Task task) {
        // Verifica se a categoria da tarefa é válida com base no enum TaskCategory
        if (isValidTaskCategory(task.getTaskCategory())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(taskRepository.save(task));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping
    public ResponseEntity<Task> put(@Valid @RequestBody Task task) {
        // Verifica se a tarefa existe
        Optional<Task> optionalTask = taskRepository.findById(task.getId());

        if (optionalTask.isPresent()) {
            // Verifica se a categoria da tarefa é válida
            if (isValidTaskCategory(task.getTaskCategory())) {
                // Atualiza a tarefa e retorna o status 200 OK
                return ResponseEntity.status(HttpStatus.OK).body(taskRepository.save(task));
            } else {
                // Se a categoria da tarefa for inválida, retorna 400 Bad Request
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            // Se a tarefa não for encontrada, retorna 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Deletar uma tarefa por ID
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Task> task = taskRepository.findById(id);

        if (task.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            taskRepository.deleteById(id);
        }
    }

    // Método auxiliar para verificar se a categoria da tarefa é válida com base no enum TaskCategory
    private boolean isValidTaskCategory(TaskCategory taskCategory) {
        try {
            TaskCategory.valueOf(taskCategory.name());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
