package com.example.schooloperationsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "headmaster")
public class HeadMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "teacher_Id", nullable = false, foreignKey = @ForeignKey(name = "FK_HEAD_MASTER_TEACHER_ID"))
    private Teacher teacher;

    @OneToOne
    @JoinColumn(name = "class_Id", nullable = false, foreignKey = @ForeignKey(name = "FK_HEAD_MASTER_CLASS_ID"))
    private SchoolClass schoolClass;

    @Override
    public String toString() {
        return "HeadMaster{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", schoolClass=" + schoolClass +
                '}';
    }
}
