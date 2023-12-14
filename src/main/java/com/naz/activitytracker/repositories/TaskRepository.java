package com.naz.activitytracker.repositories;

import com.naz.activitytracker.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


//    List<Task> findAll();

    List<Task> findByStatus(String status);


}
