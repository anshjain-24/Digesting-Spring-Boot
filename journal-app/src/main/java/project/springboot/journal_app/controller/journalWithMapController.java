package project.springboot.journal_app.controller;

import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import project.springboot.journal_app.entity.Journal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class journalWithMapController {

    public Map<ObjectId, Journal> journalEntries = new HashMap<ObjectId,Journal>();

    @GetMapping()
    public List<Journal> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping()
    public Boolean addItem (@RequestBody Journal journal){
        journalEntries.put(journal.getId(),journal);
        return true;
    }

    @PutMapping("{id}")
    public Boolean updateItem(@PathVariable("id") ObjectId id, @RequestBody Journal journal){

        journalEntries.remove(id);
        journalEntries.put(journal.getId(),journal);
        return true;
    }

    @DeleteMapping("{id}")
    public Boolean deleteItem(@PathVariable ObjectId id){
        journalEntries.remove(id);
        return true;
    }

}
