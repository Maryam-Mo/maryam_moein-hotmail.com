package com.example.todo_list.todo;

import com.example.todo_list.category.CategoryRepository;
import com.example.todo_list.exception.TodoListException;
import com.example.todo_list.user.UserService;
import com.example.todo_list.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired private TodoRepository todoRepository;

    @Override
    public List<TodoList> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public TodoList findById(long id) {
        return todoRepository.findById(id).get();
    }

    @Override
    public TodoList create(TodoList todo) {
        List<TodoList> todoLists = todoRepository.findByName(todo.getName());
        for (TodoList todoList: todoLists){
            if (todoList.getCategory().getUser().getId() == UserServiceImpl.getLoginUser().getId()) {
                if (todoList != null && todoList.getId() != todo.getId() && todo.getCategory().getUser().getId() == todoList.getCategory().getUser().getId()) {
                    throw new TodoListException("TodoList name already exist!");
                }
            }
        }
        return todoRepository.save(todo);
    }

    @Override
    public TodoList update(TodoList todo) {
        Optional<TodoList> todoListOptional = todoRepository.findById(todo.getId());
        if (!todoListOptional.isPresent()){
            throw new TodoListException("TodoList does not exist!");
        }
        List<TodoList> todoLists = todoRepository.findByName(todo.getName());
        for (TodoList todoList: todoLists){
            if (todoList.getCategory().getUser().getId() == UserServiceImpl.getLoginUser().getId()){
                if (todoList!= null && todoList.getId() != todo.getId() && todo.getCategory().getUser().getId() == todoList.getCategory().getUser().getId()) {
                    throw new TodoListException("TodoList name already exist!");
                }
            }
        }
        return todoRepository.save(todo);
    }

    @Override
    public void delete(long id) {
        Optional<TodoList> todoListOptional = todoRepository.findById(id);
        if (!todoListOptional.isPresent()){
            throw new TodoListException("TodoList does not exist!");
        }
        todoRepository.delete(todoListOptional.get());
    }

    @Override
    public List<TodoList> findAllByStatus(TodoRequest todoRequest) {
        return todoRepository.findAllByStatusAndCategoryId(todoRequest.getStatus(), todoRequest.getCategoryId());
    }
}
