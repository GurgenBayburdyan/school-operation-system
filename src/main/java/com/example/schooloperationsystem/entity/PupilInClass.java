package com.example.schooloperationsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@ToString
@Table(name = "pupil_in_class")
public class PupilInClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false, foreignKey = @ForeignKey(name = "FK_PUPIL_IN_CLASS_CLASS_ID"))
    private SchoolClass schoolClass;

    @OneToOne
    @JoinColumn(name = "pupil_id", nullable = false, foreignKey = @ForeignKey(name = "FK_PUPIL_IN_CLASS_PUPIL_ID"))
    private Pupil pupil;
}