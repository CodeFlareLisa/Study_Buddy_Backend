package com.Revature.Study_Buddy_Backend.Repository;

import com.Revature.Study_Buddy_Backend.Model.FlashCards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashCardsRepo extends JpaRepository<FlashCards, Long>{
    List<FlashCards> findBySetId(Long setId);
    FlashCards findByfCardId(Long fCardId);
}