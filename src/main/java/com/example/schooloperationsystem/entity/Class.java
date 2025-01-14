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
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "grade")
    private int grade;

    @Column(name = "classLetter")
    private char classLetter;

}
