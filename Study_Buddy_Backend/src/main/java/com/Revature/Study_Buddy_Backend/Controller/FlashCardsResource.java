package com.Revature.Study_Buddy_Backend.Controller;

import com.Revature.Study_Buddy_Backend.Model.FlashCards;
import com.Revature.Study_Buddy_Backend.Model.Sets;
import com.Revature.Study_Buddy_Backend.Service.FlashCardsService;
import com.Revature.Study_Buddy_Backend.Service.SetsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@RestController
@RequestMapping("/api/flashcards")
public class FlashCardsResource {
    private FlashCardsService flashCardsService;
    private SetsService setsService;

    @GetMapping
    public ResponseEntity<List<FlashCards>> FlashCardsList() {
        return new ResponseEntity<>(flashCardsService.getAllFlashCardsList(), HttpStatus.OK);
    }

    @GetMapping("/getSet/{fCardId}")
    public ResponseEntity<List<FlashCards>> getAllFlashCardsBySetId(@PathVariable("fCardId") Long id) {
        return new ResponseEntity<>(flashCardsService.getBysetId(id), HttpStatus.OK);
    }

    @GetMapping("/{fCardId}")
    public ResponseEntity<FlashCards> getFlashCardsById(@PathVariable("fCardId") Long fCardId) {
        try {
            FlashCards flashCards = flashCardsService.getByfCardId(fCardId);
            if(flashCards != null){
                return new ResponseEntity<>(flashCards, HttpStatus.OK);
            }
        }catch (Exception ignore){}
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<FlashCards> addFlashCards(@RequestBody FlashCards flashCards) {
        return new ResponseEntity<>(flashCardsService.addFlashCards(flashCards), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<FlashCards> updateFlashCards(@RequestBody FlashCards flashCards) {
        try {
            FlashCards findCards = flashCardsService.getByfCardId(flashCards.getFCardId());
            if(findCards != null){
                return new ResponseEntity<>(flashCardsService.updateFlashCards(flashCards), HttpStatus.OK);
            }
        } catch (Exception ignore) {}
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{fCardId}")
    public ResponseEntity<?> deleteByFCardId(@PathVariable("fCardId") Long fCardId) {
        try {
            FlashCards flashCards = flashCardsService.getByfCardId(fCardId);
            if(flashCards != null){
                flashCardsService.deleteByfCardId(fCardId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception ignore) {}
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/randomizeSet/{setId}")
    public ResponseEntity<List<FlashCards>> randomizeInSet(@PathVariable("setId") Long setId){
        try{
            List<FlashCards> cardsListBySet = flashCardsService.getBysetId(setId);
            List<FlashCards> randomizedCards = new ArrayList<>();
            Random random = new Random();
            while(cardsListBySet.size() != 0){
                randomizedCards.add(cardsListBySet.remove(random.nextInt(cardsListBySet.size())));
            }
            return new ResponseEntity<>(randomizedCards, HttpStatus.OK);
        }catch(Exception ignore){}
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getOwner/{setId}")
    public ResponseEntity<Long> getCardOwner(@PathVariable("setId") Long setId){
        try{
            Sets sets = setsService.getBySetId(setId);
            System.out.println(sets);
            if(sets != null){
                return new ResponseEntity<>(sets.getUserId(), HttpStatus.OK);
            }
        }catch(Exception ignore){}
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
