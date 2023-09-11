package co.devskills.springbootboilerplate.entity;


import co.devskills.springbootboilerplate.Priority;


import javax.persistence.*;


@Entity
@Table(name="TodoTable")
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Id_sequence")
    @SequenceGenerator(name = "Id_sequence", sequenceName = "id_sec")
    private Long id;
    private String description = "default description";
    private boolean done = false;
    private long dueDate = System.currentTimeMillis();
    private Priority priority = Priority.MEDIUM;

    public TodoEntity() {

    }

    public TodoEntity (String description, boolean done, long dueDate, Priority priority) {
        this.description = description;
        this.done = done;
        this.dueDate = dueDate;
        this.priority = priority;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
