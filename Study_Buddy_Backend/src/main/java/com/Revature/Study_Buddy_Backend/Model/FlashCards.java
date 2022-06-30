package com.Revature.Study_Buddy_Backend.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "FlashCards")
@AllArgsConstructor
@NoArgsConstructor
public class FlashCards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fCardId;
    private String term;
    private String def;
    private Long setId;
}
