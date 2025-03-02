package com.example.schooloperationsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@ToString
@Table(name = "headmaster")
public class HeadMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "teacher_Id", nullable = false, foreignKey = @ForeignKey(name = "FK_HEAD_MASTER_TEACHER_ID"))
    private Teacher teacher;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "class_Id", nullable = false, foreignKey = @ForeignKey(name = "FK_HEAD_MASTER_CLASS_ID"))
    private SchoolClass schoolClass;

}