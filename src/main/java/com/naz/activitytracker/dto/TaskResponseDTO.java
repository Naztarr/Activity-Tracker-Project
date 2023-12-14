package com.naz.activitytracker.dto;


import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class TaskResponseDTO{

    private Long id;


    private String title;

    private String description;


    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime completedAt;
}








































//package com.naz.activitytracker.DTO;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import java.time.LocalDateTime;
//
//@JsonFormat
//
//public class TaskResponseDTO {
//
//    private Long id;
//
//    private String title;
//
//    private String description;
//
//    private String status;
//
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") // Format LocalDateTime as a string
//    private LocalDateTime createdAt;
//
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") // Format LocalDateTime as a string
//    private LocalDateTime updatedAt;
//
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") // Format LocalDateTime as a string
//    private LocalDateTime completedAt;

    // Add appropriate Jackson annotations for field customization if needed (e.g., @JsonProperty)

    // Constructors, getters, and setters as required
//}
