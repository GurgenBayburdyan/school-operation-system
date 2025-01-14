package com.example.schooloperationsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "staffId", nullable = false, foreignKey = @ForeignKey(name = "FK_TEACHER_STAFF_ID"))
    private Staff staff;

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", staff=" + staff +
                '}';
    }
}
