package com.naz.activitytracker.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequestDTO{

    private String userName;


    private String passWord;
}
