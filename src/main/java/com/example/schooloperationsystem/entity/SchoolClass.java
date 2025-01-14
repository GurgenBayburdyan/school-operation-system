package com.example.schooloperationsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


/**
 * @author Gurgen Bayburdyan
 */
@Setter
@Getter
@Entity
@Table(name = "class")
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "grade", nullable = false)
    private Integer grade;

    @Column(name = "classLetter", nullable = false)
    private Character classLetter;

    @Override
    public String toString() {
        return "SchoolClass{" +
                "id=" + id +
                ", grade=" + grade +
                ", classLetter=" + classLetter +
                '}';
    }
}
