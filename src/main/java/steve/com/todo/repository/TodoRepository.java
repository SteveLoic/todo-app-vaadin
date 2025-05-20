package steve.com.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import steve.com.todo.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long > {
}
