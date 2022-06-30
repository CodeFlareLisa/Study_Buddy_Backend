package com.Revature.Study_Buddy_Backend.Service;

import com.Revature.Study_Buddy_Backend.Model.Sets;
import com.Revature.Study_Buddy_Backend.Repository.SetsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetsService {
    @Autowired
    private SetsRepo setsRepo;

    public List<Sets> getAllSets(){
        return setsRepo.findAll();
    }

    public Sets addSets(Sets setSets) {
        return setsRepo.save(setSets);
    }

    public Sets updateSets(Sets updateSets){
        return setsRepo.save(updateSets);
    }

    public void deleteSetsById(Long deleteSets){
        setsRepo.deleteById(deleteSets);
    }

    public Sets getBySetId(Long id){
        return setsRepo.findBysetId(id);
    }
}