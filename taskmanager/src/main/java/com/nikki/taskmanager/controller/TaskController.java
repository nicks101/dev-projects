package com.nikki.taskmanager.controller;

import com.nikki.taskmanager.entity.Task;
import com.nikki.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task savedTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,
                           @Valid @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public List<Task> searchTaskByTitle(@RequestParam String title) {
        return taskService.searchTaskByTitle(title);
    }

    @GetMapping("/completed/{status}")
    public List<Task> getTasksByCompletions(@PathVariable String status) {
        return taskService.getTasksByCompletionStatus(Boolean.parseBoolean(status));
    }
}
