package com.demo.my_first_api.controllers;

import com.demo.my_first_api.entities.Player;
import com.demo.my_first_api.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping
    public Iterable<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    @PostMapping
    public Player addPlayer(@RequestBody Player player){
        return playerRepository.save(player);
    }

    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable Integer id, @RequestBody Player incomingPlayerChanges){
        Optional<Player> playerToUpdateOptional = playerRepository.findById(id);

        if(playerToUpdateOptional.isEmpty()){
            return null;
        }

        Player playerToUpdate = playerToUpdateOptional.get();

        if(incomingPlayerChanges.getName() != null){
            playerToUpdate.setName(incomingPlayerChanges.getName());
        }
        if(incomingPlayerChanges.getAge() != null){
            playerToUpdate.setAge(incomingPlayerChanges.getAge());
        }
        if(incomingPlayerChanges.getRating() != null){
            playerToUpdate.setRating(incomingPlayerChanges.getRating());
        }
        if(incomingPlayerChanges.getYearsOfExperience() != null){
            playerToUpdate.setYearsOfExperience(incomingPlayerChanges.getYearsOfExperience());
        }

        return playerRepository.save(playerToUpdate);
    }

    @DeleteMapping("/{id}")
    public Player deletePlayer(@PathVariable Integer id){
        Optional<Player> playerToDeleteOptional = playerRepository.findById(id);

        if(playerToDeleteOptional.isEmpty()){
            return null;
        }

        Player playerToDelete = playerToDeleteOptional.get();
        playerRepository.delete(playerToDelete);
        return playerToDelete;
    }



}
