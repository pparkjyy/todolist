package com.codestates.todo.entity;

import com.codestates.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Todo extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TodoPriority todoPriority;

    @Column(nullable = false)
    private Boolean isCompleted = false;

    public enum TodoPriority {
        TODO_PRIORITY_FIRST("1순위"),
        TODO_PRIORITY_SECOND("2순위"),
        TODO_PRIORITY_THIRD("3순위");

        @Getter
        private String priority;

        TodoPriority(String priority) {
            this.priority = priority;
        }
    }
}
