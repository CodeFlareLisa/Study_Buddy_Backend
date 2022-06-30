package com.Revature.Study_Buddy_Backend;

import com.Revature.Study_Buddy_Backend.Model.Notes;
import com.Revature.Study_Buddy_Backend.Repository.NotesRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class NotesTest {
    @Autowired
    NotesRepo notesRepo;

    @Test
    void testAddNotes(){
        Notes notes = new Notes();
        notes.setTitle("JTesting");
        notes.setCat("JTest");
        notes.setContent("Test");
        notes.setSetId(10L);
        notes.setUserId(10L);
        notesRepo.save(notes);
    }

    @Test
    void testGetNotes(){
        Notes notes = notesRepo.findBynotesId(8L);
        assertNotNull(notes);
        System.out.println(notes);
    }

    @Test
    void testNoteList(){
        List<Notes> notesList = notesRepo.findAll();
        assertThat(notesList).size().isGreaterThan(0);
        System.out.println("Notes List: " + notesList.size());
    }

    @Test
    void testUpdateNote(){
        Notes notes = notesRepo.findBynotesId(8L);
        assertNotNull(notes, notes.toString());
        notes.setTitle("update JTesting");
        notes.setCat("update JTest");
        notes.setContent("update Test");
        notes.setSetId(15L);
        notes.setUserId(15L);
        notesRepo.save(notes);
    }

    @Test
    void testDeleteNote(){
        Notes notes = notesRepo.findBynotesId(8L);
        notesRepo.delete(notes);
        System.out.println(notes);
    }

    @Test
    void testListByUserId(){
        List<Notes> notesByUser = notesRepo.findByUserId(1L);
        assertThat(notesByUser).size().isGreaterThan(0);
        System.out.println("Notes list by user: " + notesByUser.size());
    }

    @Test
    void testListByUserAndSetId(){
        List<Notes> notesByUserAndSetId = notesRepo.findBySetIdAndUserId(4L, 5L);
        assertThat(notesByUserAndSetId).size().isGreaterThan(0);
        System.out.println("Notes by user and set: " + notesByUserAndSetId.size());
    }
}
