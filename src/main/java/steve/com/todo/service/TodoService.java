package steve.com.todo.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import steve.com.todo.model.Todo;
import steve.com.todo.repository.TodoRepository;

import java.util.List;
import java.util.Set;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;
   public List<Todo> getAllTodoItems() {
        return  this.todoRepository.findAll();
    }

    public Todo addTodo(Todo todo) {
       return this.todoRepository.save(todo);
    }

    public void removeTodo(Set<Todo> todos) {
       for (Todo todoItem: todos) {
           todoRepository.findById(todoItem.getId()).ifPresentOrElse(
                   todoRepository::delete, () -> {
                       new EntityNotFoundException("item not found");
                   }
           );
       }
    }
}
