package project.springboot.journal_app.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import project.springboot.journal_app.entity.Journal;
import project.springboot.journal_app.services.JournalService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class JournalController {

    @Autowired
    JournalService journalService;

    @GetMapping("/getall")
    public ResponseEntity<List<Journal>> getAllJournalForUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new ResponseEntity<>(journalService.getAll(username),HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Journal> saveEntry(@RequestBody Journal journal){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            System.out.println("username  :  "+username);
            journalService.saveJournalEntry(journal,username);
            return new ResponseEntity<>(journal,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Journal> getById(@PathVariable ObjectId id){
        Optional<Journal> journal = journalService.getByID(id);
        System.out.println(journal);
        if (journal.isPresent()){
            return new ResponseEntity<>(journal.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


//    ResponseEntity<?>  =>  this means that you can return object of any class...

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateJournal(
            @PathVariable("id") ObjectId id,
            @RequestBody Journal newJournal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Journal old = journalService.getByID(id).orElse(null);
        System.out.println("old : "+old);
        if(old != null){
            old.setTitle(newJournal.getTitle()!=null && !newJournal.getTitle().equals("") ? newJournal.getTitle() : old.getTitle() );
            old.setContent(newJournal.getContent() !=null && !newJournal.getContent().equals("") ? newJournal.getContent() : old.getContent());
            journalService.saveJournalEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>("Journal not found",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteJournal(@PathVariable("id") ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        journalService.deleteEntry(id,username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
