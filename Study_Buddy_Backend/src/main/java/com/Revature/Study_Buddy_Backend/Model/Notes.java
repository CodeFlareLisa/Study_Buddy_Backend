package com.Revature.Study_Buddy_Backend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long notesId;

    private String title;
    private String cat;
    private String content;
    private Long setId;
    private Long userId;
}
