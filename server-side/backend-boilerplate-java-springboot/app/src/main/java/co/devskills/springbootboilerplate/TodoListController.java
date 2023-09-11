package co.devskills.springbootboilerplate;

import co.devskills.springbootboilerplate.Repository.TodoRepo;
import co.devskills.springbootboilerplate.entity.TodoEntity;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin()
public class TodoListController {

    @Autowired
    private TodoRepo dbConnection;

    @GetMapping(value = "/ping")
    @ResponseStatus(HttpStatus.OK)
    public String healthCheck() {
        return "pong";
    }

    @GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)
    public List<TodoEntity> getAllTodoLists() {
        System.out.println("Gettin all elements..");
        if (dbConnection.count() == 0) {
            System.out.println("inserting default..");
            TodoEntity defaultEntity = new TodoEntity();
            dbConnection.save(defaultEntity);
        }
        return dbConnection.findAll();
    }

    @PostMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public TodoEntity insertItem(@RequestBody TodoEntity x) {
        System.out.println("Inserting new element");
        System.out.println(x);
        String description = x.getDescription();
        boolean done = x.isDone();
        long dueDate = x.getDueDate();
        Priority priority = x.getPriority();
        TodoEntity e = dbConnection.findById(x.getId()).get();
        e.setDescription(x.getDescription());
        e.setPriority(x.getPriority());
        e.setDone(x.isDone());
        e.setDueDate(x.getDueDate());

        dbConnection.save(e);
        return e;
    }

    @GetMapping(value = "/clear")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll() {
        System.out.println("Deleting all elements..");
        dbConnection.deleteAll();
    }

    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.OK)
    public void addDefault() {
        System.out.println("adding new task..");
        System.out.println("inserting default..");
        TodoEntity defaultEntity = new TodoEntity();
        dbConnection.save(defaultEntity);
    }
}
