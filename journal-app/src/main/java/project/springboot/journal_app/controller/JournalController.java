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

    @GetMapping("/{username}/getall")
    public ResponseEntity<List<Journal>> getAllJournalForUser(@PathVariable String username){
        return new ResponseEntity<>(journalService.getAll(username),HttpStatus.OK);
    }

    @PostMapping("/{username}/save")
    public ResponseEntity<Journal> saveEntry(@RequestBody Journal journal,@PathVariable String username){
        try {
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

    @PutMapping("/update/{username}/{id}")
    public ResponseEntity<?> updateJournal(
            @PathVariable("id") ObjectId id,
            @RequestBody Journal newJournal,
            @PathVariable("username") String username ){
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

    @DeleteMapping("delete/{username}/{id}")
    public ResponseEntity<?> deleteJournal(@PathVariable("username") String username,@PathVariable("id") ObjectId id) {
        journalService.deleteEntry(id,username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
