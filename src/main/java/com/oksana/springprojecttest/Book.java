package com.oksana.springprojecttest;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="books")
public class Book {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
