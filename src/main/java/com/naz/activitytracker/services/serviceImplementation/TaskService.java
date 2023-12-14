package com.naz.activitytracker.services.serviceImplementation;

import com.naz.activitytracker.dto.TaskRequestDTO;
import com.naz.activitytracker.dto.TaskResponseDTO;
import com.naz.activitytracker.entities.Task;
import com.naz.activitytracker.repositories.TaskRepository;
import com.naz.activitytracker.services.TaskServices;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService implements TaskServices {
    private final TaskRepository taskRepository;
    private final ModelMapper mapper = new ModelMapper();

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskResponseDTO> getAllTasks() {

        List<Task> allTasks = taskRepository.findAll();
        List<TaskResponseDTO> dtoAllTasks = new ArrayList<>();
        for(Task task:allTasks){
            TaskResponseDTO taskResponseDTO = mapper.map(task, TaskResponseDTO.class);
            dtoAllTasks.add(taskResponseDTO);
        }
        return dtoAllTasks;
    }

    @Override
    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        taskRequestDTO.setStatus("In Progress");
        taskRequestDTO.setCreatedAt(LocalDateTime.now());
        Task task = mapper.map(taskRequestDTO, Task.class);
             taskRepository.save(task);
        TaskResponseDTO taskResponseDTO = mapper.map(task, TaskResponseDTO.class);
        return taskResponseDTO;
    }

    @Override
    public Optional<TaskResponseDTO> getTaskById(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        return Optional.ofNullable(taskOptional
                .map(user -> mapper.map(taskOptional, TaskResponseDTO.class)).orElse(null));
    }

    @Override
    public List<TaskResponseDTO> getAllPendingTasks() {

      return taskRepository.findByStatus("Pending").stream().map(task->mapper.map(task, TaskResponseDTO.class))
              .collect(Collectors.toList());
//        List<TaskResponseDTO> dtoPendingTasks = new ArrayList<>();
//        for(Task task:pendingTasks){
//            TaskResponseDTO taskResponseDTO = mapper.map(task, TaskResponseDTO.class);
//            dtoPendingTasks.add(taskResponseDTO);
//        }
//        return dtoPendingTasks;
    }

    @Override
    public List<TaskResponseDTO> getAllDoneTasks() {
        return taskRepository.findByStatus("Done").stream().map(task -> mapper.map(task, TaskResponseDTO.class))
                .collect(Collectors.toList());
//        List<TaskResponseDTO> dtoDoneTasks = new ArrayList<>();
//        for(Task task:doneTasks){
//            TaskResponseDTO taskResponseDTO = mapper.map(task, TaskResponseDTO.class);
//            dtoDoneTasks.add(taskResponseDTO);
//        }
//        return dtoDoneTasks;
    }

    @Override
    public List<TaskResponseDTO> getAllInProgressTasks() {
        return taskRepository.findByStatus("In progress").stream().map(task -> mapper.map(task, TaskResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void moveTaskToPending(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        taskOptional.ifPresent(task -> {
            task.setStatus("Pending");
            task.setCompletedAt(null);
            taskRepository.save(task);
        });
    }

    @Override
    public void moveTaskToDone(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        taskOptional.ifPresent(task -> {
            task.setStatus("Done");
            task.setCompletedAt(LocalDateTime.now());
            taskRepository.save(task);
        });
    }

    @Override
    public TaskResponseDTO editTask(Long id, TaskRequestDTO updatedTask) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        taskOptional.ifPresent(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setUpdatedAt(LocalDateTime.now());
             taskRepository.save(task);
        });
        TaskResponseDTO taskResponseDTO = mapper.map(taskOptional, TaskResponseDTO.class);
        return taskResponseDTO;
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
