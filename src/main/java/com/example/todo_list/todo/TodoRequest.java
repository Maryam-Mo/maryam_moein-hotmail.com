package com.example.todo_list.todo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoRequest {
    private String status;
    private long categoryId;
}
