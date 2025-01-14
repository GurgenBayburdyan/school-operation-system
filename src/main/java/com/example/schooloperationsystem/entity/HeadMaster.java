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
    @JoinColumn(name = "teacherId", nullable = false, foreignKey = @ForeignKey(name = "FK_HEAD_MASTER_TEACHER_ID"))
    private Teacher teacher;

    @OneToOne
    @JoinColumn(name = "classId", nullable = false, foreignKey = @ForeignKey(name = "FK_HEAD_MASTER_CLASS_ID"))
    private SchoolClass schoolClass;
}
