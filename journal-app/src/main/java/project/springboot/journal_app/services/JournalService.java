package project.springboot.journal_app.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.springboot.journal_app.entity.Journal;
import project.springboot.journal_app.repository.JournalRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class JournalService {

    @Autowired
    private JournalRepo journalRepo;

    public List<Journal> getAll (){
        return journalRepo.findAll();
    }

    public void saveJournalEntry(Journal journal){
        journal.setDate(LocalDate.now());
        journalRepo.save(journal);
    }

    public void deleteEntry(ObjectId id){
        journalRepo.deleteById(id);
    }

    public Optional<Journal> getByID(ObjectId id){
        return journalRepo.findById(id);
    }


}
