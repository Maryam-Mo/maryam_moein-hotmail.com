package com.example.todo_list.email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequest {

    private String toAddress;
    private String subject;
    private String message;
    private String attachment;
}
