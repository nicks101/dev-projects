package com.nikki.taskmanager.service;

import com.nikki.taskmanager.dto.TaskRequest;
import com.nikki.taskmanager.dto.TaskResponse;
import com.nikki.taskmanager.entity.Task;
import com.nikki.taskmanager.exception.TaskNotFoundException;
import com.nikki.taskmanager.mapper.TaskMapper;
import com.nikki.taskmanager.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream().map(taskMapper::toResponse).toList();
    }

    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return taskMapper.toResponse(task);
    }

    public TaskResponse createTask(TaskRequest task) {
        Task entityTask = taskMapper.toEntity(task);
        return taskMapper.toResponse(taskRepository.save(entityTask));
    }

    public TaskResponse updateTask(Long id, TaskRequest updatedTask) {
        final Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        taskMapper.updateEntityFromRequest(task, updatedTask);
        return taskMapper.toResponse(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        final Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.delete(task);
    }

    public List<TaskResponse> searchTaskByTitle(String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title)
                .stream().map(taskMapper::toResponse).toList();
    }

    public List<TaskResponse> getTasksByCompletionStatus(Boolean status) {
        return taskRepository.findByCompleted(status)
                .stream().map(taskMapper::toResponse).toList();
    }
}
