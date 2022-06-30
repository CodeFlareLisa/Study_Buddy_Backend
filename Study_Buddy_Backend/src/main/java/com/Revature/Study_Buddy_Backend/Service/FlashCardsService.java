package com.Revature.Study_Buddy_Backend.Service;

import com.Revature.Study_Buddy_Backend.Model.FlashCards;
import com.Revature.Study_Buddy_Backend.Repository.FlashCardsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlashCardsService {
    @Autowired
    private FlashCardsRepo flashCardsRepo;

    public List<FlashCards> getAllFlashCardsList() {
        return flashCardsRepo.findAll();
    }

    public List<FlashCards> getBysetId(Long setId) {
        return flashCardsRepo.findBySetId(setId);
    }

    public FlashCards getByfCardId(Long fCardId) {
        return flashCardsRepo.findByfCardId(fCardId);
    }

    public FlashCards addFlashCards(FlashCards flashCards) {
        return flashCardsRepo.save(flashCards);
    }

    public FlashCards updateFlashCards(FlashCards flashCards) {
        return flashCardsRepo.save(flashCards);
    }

    public void deleteByfCardId(Long fCardId) {
        flashCardsRepo.deleteById(fCardId);
    }
}
