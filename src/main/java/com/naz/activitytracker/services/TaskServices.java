package com.naz.activitytracker.services;

import com.naz.activitytracker.dto.TaskRequestDTO;
import com.naz.activitytracker.dto.TaskResponseDTO;

import java.util.List;
import java.util.Optional;

public interface TaskServices {
    List<TaskResponseDTO> getAllTasks();

    TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO);

    Optional<TaskResponseDTO> getTaskById(Long id);

    List<TaskResponseDTO> getAllPendingTasks();

    List<TaskResponseDTO> getAllDoneTasks();

    List<TaskResponseDTO> getAllInProgressTasks();

    void moveTaskToPending(Long id);

    void moveTaskToDone(Long id);

    TaskResponseDTO editTask(Long id, TaskRequestDTO updatedTask);

    void deleteTask(Long id);
}
