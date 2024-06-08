package project.springboot.journal_app.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Journal>> getAll(){
        return new ResponseEntity<>(journalService.getAll(),HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Journal> saveEntry(@RequestBody Journal journal){
        try {
            journalService.saveJournalEntry(journal);
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
    public ResponseEntity<?> updateJournal(@PathVariable ObjectId id,@RequestBody Journal newJournal){
        Journal old = journalService.getByID(id).orElse(null);
        if(old != null){
            old.setTitle(newJournal.getTitle()!=null && !newJournal.getTitle().equals("") ? newJournal.getTitle() : old.getTitle() );
            old.setContent(newJournal.getContent() !=null && !newJournal.getContent().equals("") ? newJournal.getContent() : old.getContent());
            journalService.saveJournalEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteJournal(@PathVariable ObjectId id) {
        journalService.deleteEntry(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
