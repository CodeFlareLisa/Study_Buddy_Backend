package com.Revature.Study_Buddy_Backend.Controller;

import com.Revature.Study_Buddy_Backend.Model.Notes;
import com.Revature.Study_Buddy_Backend.Model.Sets;
import com.Revature.Study_Buddy_Backend.Model.User;
import com.Revature.Study_Buddy_Backend.Service.NotesService;
import com.Revature.Study_Buddy_Backend.Service.SetsService;
import com.Revature.Study_Buddy_Backend.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/Notes")
public class NotesResource {
    private NotesService notesService;
    private SetsService setsService;
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Notes>> getAllNotes() {
        return new ResponseEntity<>(notesService.getAllNotes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notes> getNotesById(@PathVariable("id")Long id){
        try{
            Notes notes = notesService.getNotesById(id);
            if(notes != null) {
                return new ResponseEntity<>(notes, HttpStatus.OK);
            }
        }catch(Exception ignored){}
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Notes> addNotes(@RequestBody Notes notes) {
        try{
            User user = userService.getUserById(notes.getUserId());
            Sets sets = setsService.getBySetId(notes.getSetId());
            if (user != null && (sets == null || sets.getUserId().equals(notes.getUserId()) || sets.getPrivacy().equalsIgnoreCase("public"))) {
                return new ResponseEntity<>(notesService.addNotes(notes), HttpStatus.OK);
            }
        }catch(Exception ignore){}
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Notes> updatesNotes(@RequestBody Notes notes){
        try{
            Notes findNotes = notesService.getNotesById(notes.getNotesId());
            if(findNotes != null){
                return new ResponseEntity<>(notesService.updateNotes(notes), HttpStatus.OK);
            }
        }catch(Exception ignore){}
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotes(@PathVariable("id")Long notesId){
        try {
            Notes notes = notesService.getNotesById(notesId);
            if(notes != null) {
                notesService.deleteNotes(notesId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception ignore) {}
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<Notes>> getNotesByUserId(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(notesService.getNotesByUserIds(userId), HttpStatus.OK);
    }

    @GetMapping("/bySet/{setId}/{userId}")
    public ResponseEntity<List<Notes>> getNotesBySetId(@PathVariable("setId") Long setId, @PathVariable("userId") Long userId){
        return new ResponseEntity<>(notesService.getNotesBySetIdAndUserId(setId,userId), HttpStatus.OK);
    }
}
