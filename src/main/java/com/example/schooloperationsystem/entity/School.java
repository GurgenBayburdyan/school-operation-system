package com.example.schooloperationsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "school")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "named_after", nullable = false)
    private String namedAfter;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "photo_url", length = 1000)
    private String photoUrl;

    @Column
    private LocalDateTime deletedAt;
}
