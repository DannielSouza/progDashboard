package com.dannielSouza.progDashboard.models;

import com.dannielSouza.progDashboard.models.Enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUser;
    private Long idCompany;
    private String name;
    private String description;
    private Integer taskStatus;

    public Task(Long idUser, Long idCompany, String name, String description, TaskStatus taskStatus) {
        this.idUser = idUser;
        this.idCompany = idCompany;
        this.name = name;
        this.description = description;
        setTaskStatus(taskStatus);
    }

    public Task(Long id, TaskStatus taskStatus) {
        this.id = id;
        setTaskStatus(taskStatus);
    }

    public TaskStatus getTaskStatus() {
        return TaskStatus.valueOf(taskStatus);
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        if(taskStatus != null){
            this.taskStatus = taskStatus.getCode();
        }
    }
}
