package com.Revature.Study_Buddy_Backend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sets {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long setId;
    private String privacy;
    private Long userId;
    private String setName;
}
