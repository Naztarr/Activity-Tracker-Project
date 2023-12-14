package com.naz.activitytracker.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {
    private String message;
    private boolean success;
//    private Object data;
//    @JsonIgnore
//    private HttpStatus sttus;


}
