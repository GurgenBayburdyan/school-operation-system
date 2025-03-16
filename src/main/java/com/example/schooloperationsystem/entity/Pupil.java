package com.example.schooloperationsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Entity
@Table(name = "pupil")
public class Pupil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_Name", nullable = false)
    private String firstName;

    @Column(name = "last_Name", nullable = false)
    private String lastName;

    @Column(name = "date_Of_Birth", nullable = false)
    private LocalDateTime dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    private LocalDateTime deletedAt;

}