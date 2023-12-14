package com.naz.activitytracker.controller;

import com.naz.activitytracker.dto.TaskRequestDTO;
import com.naz.activitytracker.dto.TaskResponseDTO;
import com.naz.activitytracker.response.ApiResponse;
import com.naz.activitytracker.services.serviceImplementation.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all-tasks")
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        List<TaskResponseDTO> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping("/task")
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO createdTask = taskService.createTask(taskRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTask(@PathVariable Long id) {
    Optional<TaskResponseDTO> task = taskService.getTaskById(id);

    if (task.isPresent()) {
        return new ResponseEntity<>(task.get(), HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


    @GetMapping("/pending-tasks")
    public ResponseEntity<List<TaskResponseDTO>> getAllPendingTasks() {
        List<TaskResponseDTO> tasks = taskService.getAllPendingTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/done-tasks")
    public ResponseEntity<List<TaskResponseDTO>> getAllDoneTasks() {
        List<TaskResponseDTO> tasks = taskService.getAllDoneTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/in-progress")
    public ResponseEntity<List<TaskResponseDTO>> getAllInProgressTasks() {
        List<TaskResponseDTO> tasks = taskService.getAllInProgressTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/pending-move/{id}")
    public ResponseEntity<ApiResponse> moveTaskToPending(@PathVariable Long id) {
        taskService.moveTaskToPending(id);
//        return new ResponseEntity<>(HttpStatus.OK);
        return ResponseEntity.ok(new ApiResponse("Task has been moved to pending", true));
    }

    @PutMapping("/done-move/{id}")
    public ResponseEntity<ApiResponse> moveTaskToDone(@PathVariable Long id) {
        taskService.moveTaskToDone(id);
        return ResponseEntity.ok(new ApiResponse("Task has been moved to done", true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> editTask(@PathVariable Long id, @RequestBody TaskRequestDTO task) {
        TaskResponseDTO editedTask = taskService.editTask(id, task);
        return new ResponseEntity<>(editedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
       return ResponseEntity.ok(new ApiResponse("Task has been deleted", true));
    }
}
