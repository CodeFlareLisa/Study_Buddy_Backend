package com.Revature.Study_Buddy_Backend.Service;

import com.Revature.Study_Buddy_Backend.Model.FlashCards;
import com.Revature.Study_Buddy_Backend.Repository.FlashCardsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FlashCardsService{
    @Autowired
    private FlashCardsRepo flashCardsRepo;
    public List<FlashCards> getAllFlashCards(){
        return flashCardsRepo.findAll();
    }

    // try catch if exist
    public FlashCards getByfCardId (Long id) {
        try {
            return flashCardsRepo.findByfCardId(id);
        } catch (Exception e) {
            return flashCardsRepo.findByfCardId(id);
        }
    }
    public FlashCards addFlashCards (FlashCards flashCards){
        return flashCardsRepo.save(flashCards);
    }

    //try catch find flash card if not exist return null
    public FlashCards updateFlashCards (FlashCards flashCards){
            try{
                return flashCardsRepo.save(flashCards);
            } catch (Exception e){
                return flashCardsRepo.save(flashCards);
        }

    }

    //try catch find flash card if not exist return null
    public void deleteByfCardId (Long fCardId) {
        try {
            flashCardsRepo.deleteByfCardId(fCardId);
        } catch (Exception e) {
            flashCardsRepo.deleteByfCardId(fCardId);
        }
    }

    /*
    try catch if null or size is 0
    implement findBysetId

    call getAllFlashCards return list base off setId
     */


   public List<FlashCards> getFlashsBySetId(Long setId){
       try {
           return flashCardsRepo.findBySetId(setId);
       }
       catch (Exception e){
           return null;
       }
   }

}
