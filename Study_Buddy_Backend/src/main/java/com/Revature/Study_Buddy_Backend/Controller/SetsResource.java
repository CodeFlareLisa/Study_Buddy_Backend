package com.Revature.Study_Buddy_Backend.Controller;

import com.Revature.Study_Buddy_Backend.Model.Sets;
import com.Revature.Study_Buddy_Backend.Model.User;
import com.Revature.Study_Buddy_Backend.Service.SetsService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@AllArgsConstructor
@RequestMapping("/api/Sets")
@RestController
public class SetsResource {

    private SetsService setsService;

    @GetMapping("/listSets")
    public ResponseEntity<List<Sets>> getListSets() {
        return new ResponseEntity<>(setsService.getAllSets(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Sets> addSets(@RequestBody Sets newSets) {
        return new ResponseEntity<>(setsService.addSets(newSets), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSetById(@PathVariable("id") Long id){
        Sets setExist = setsService.getSetById(id);
        if (setExist != null){
            return new ResponseEntity<>(setExist, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //find if user exist then update, if not return null
    @PutMapping
    public ResponseEntity<Sets> updateListSets(@RequestBody Sets sets) {
        try {
            setsService.getSetById(sets.getSetId());
            return new ResponseEntity<>(setsService.updateSets(sets), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //find if the user exist, then delete them, if not return null
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteListSets(@PathVariable("id") Long id) {
        try{
            setsService.deleteSetsById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //need to find a list of sets that shows all that belong to a user and all public seT NO_CONTENT
    @GetMapping("/publicSet/{userId}")
    public ResponseEntity<List<Sets>> getPublicSets(@PathVariable("userId") Long id){
        List<Sets> viewableSets = new ArrayList<>();
        List<Sets> allSets = setsService.getAllSets();
        for(Sets sets: allSets){
            if(sets.getUserId() == id || sets.getPrivacy().equalsIgnoreCase("public")){
                viewableSets.add(sets);
            }
        }
        return new ResponseEntity<>(viewableSets, HttpStatus.OK);
    }
}