package com.nikki.taskmanager.repository;

import com.nikki.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // @Query("SELECT t FROM Task t WHERE t.completed = :completed")
    List<Task> findByCompleted(boolean completed);

    List<Task> findByTitleContainingIgnoreCase(String title);

    // Paginated methods - Not used yet
//    Page<Task> findByCompleted(boolean completed, Pageable pageable);
//    Page<Task> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
