package com.Revature.Study_Buddy_Backend.Service;

import com.Revature.Study_Buddy_Backend.Model.Notes;
import com.Revature.Study_Buddy_Backend.Repository.NotesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {

    @Autowired
    private NotesRepo notesRepo;

    public List<Notes>getAllNotes(){
        return notesRepo.findAll();
    }

    public Notes getNotesById(Long notesId){
        return notesRepo.findBynotesId(notesId);
    }

    public Notes addNotes(Notes notes) {
        return notesRepo.save(notes);
    }

    public Notes updateNotes(Notes notes) {
        return notesRepo.save(notes);
    }

    public void deleteNotes(Long notes){
        notesRepo.deleteById(notes);
    }

    public List<Notes> getNotesByUserIds(Long userId){
        return notesRepo.findByUserId(userId);
    }

    public List<Notes> getNotesBySetIdAndUserId(Long setId, Long userId){return notesRepo.findBySetIdAndUserId(setId, userId); }
}
