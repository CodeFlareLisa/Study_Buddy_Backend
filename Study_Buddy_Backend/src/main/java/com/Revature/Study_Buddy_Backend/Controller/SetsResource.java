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

    @GetMapping("/{setId}")
    public ResponseEntity<Sets> getSetById(@PathVariable("setId") Long setId){
        try{
            Sets findSets = setsService.getBySetId(setId);
            if(findSets != null){
                return new ResponseEntity<>(findSets, HttpStatus.OK);
            }
        } catch(Exception ignore){}
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Sets> updateListSets(@RequestBody Sets sets) {
        try {
            Sets findSets = setsService.getBySetId(sets.getSetId());
            if(findSets != null) {
                return new ResponseEntity<>(setsService.updateSets(sets), HttpStatus.OK);
            }
        }catch (Exception ignore){}
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{setId}")
    public ResponseEntity<?> deleteListSets(@PathVariable("setId") Long setId) {
        try{
            Sets sets = setsService.getBySetId(setId);
            if(sets != null){
                setsService.deleteSetsById(setId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }catch (Exception ignore){}
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/publicSet/{userId}")
    public ResponseEntity<List<Sets>> getPublicSets(@PathVariable("userId") Long id){
        List<Sets> viewableSets = new ArrayList<>();
        List<Sets> allSets = setsService.getAllSets();
        try{
            for(Sets sets: allSets){
                if(sets.getUserId().equals(id) || sets.getPrivacy().equalsIgnoreCase("public")){
                    viewableSets.add(sets);
                }
            }
        }catch (Exception ignore){}
        return new ResponseEntity<>(viewableSets, HttpStatus.OK);
    }
}