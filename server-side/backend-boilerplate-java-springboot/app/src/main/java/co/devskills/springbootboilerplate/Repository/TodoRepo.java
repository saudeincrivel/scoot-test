package co.devskills.springbootboilerplate.Repository;
import co.devskills.springbootboilerplate.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepo extends JpaRepository<TodoEntity, Long > {

}