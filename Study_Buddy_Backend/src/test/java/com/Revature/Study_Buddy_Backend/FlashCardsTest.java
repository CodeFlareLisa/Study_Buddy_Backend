package com.Revature.Study_Buddy_Backend;

import com.Revature.Study_Buddy_Backend.Model.FlashCards;
import com.Revature.Study_Buddy_Backend.Repository.FlashCardsRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class FlashCardsTest {
    @Autowired
    FlashCardsRepo flashCardsRepo;

    @Test
    void testAddCard(){
        FlashCards flashCards = new FlashCards();
        flashCards.setTerm("Term X");
        flashCards.setDef("Def X");
        flashCards.setSetId(2L);
        flashCardsRepo.save(flashCards);
    }

    @Test
    void testAllCardList(){
        List<FlashCards> fCardList = flashCardsRepo.findAll();
        assertThat(fCardList).size().isGreaterThan(0);
        System.out.println("Card list: " + fCardList.size());
    }

    @Test
    void testListBySetId(){
        List<FlashCards> fCardBySet = flashCardsRepo.findBySetId(1L);
        assertThat(fCardBySet).size().isGreaterThan(0);
        System.out.println("List by set id: " + fCardBySet.size());
    }

    @Test
    void testGetCard(){
        FlashCards card = flashCardsRepo.findByfCardId(11L);
        assertNotNull(card);
        System.out.println(card);
    }

    @Test
    void testUpdateCard(){
        FlashCards card = flashCardsRepo.findByfCardId(11L);
        assertNotNull(card, card.toString());
        card.setTerm("Update term X");
        card.setDef("Update def X");
        flashCardsRepo.save(card);
        System.out.println(card);
    }

    @Test
    void testDeleteCard(){
        FlashCards card = flashCardsRepo.findByfCardId(10L);
        flashCardsRepo.delete(card);
        System.out.println(card);
    }
}
