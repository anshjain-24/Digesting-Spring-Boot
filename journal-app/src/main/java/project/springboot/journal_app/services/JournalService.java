package project.springboot.journal_app.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.springboot.journal_app.entity.Journal;
import project.springboot.journal_app.entity.User;
import project.springboot.journal_app.repository.JournalRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    JournalRepo journalRepo;

    @Autowired
    UserService userService;

    public List<Journal> getAll (String username){
        User user = userService.findByUserName(username);
        return user.getJournals();
    }

    @Transactional
    public void saveJournalEntry(Journal journal,String username){
        journal.setDate(LocalDate.now());
        Journal journal1 = journalRepo.save(journal);
        User user = userService.findByUserName(username);
        user.getJournals().add(journal1);
//        user.setUsername(null);
        userService.saveUser(user);
        return;
    }

    public void saveJournalEntry(Journal journal){
        journal.setDate(LocalDate.now());
        journalRepo.save(journal);
    }

    @Transactional
    public boolean deleteEntry(ObjectId id,String username){
        try{
            User user = userService.findByUserName(username);
            boolean removed = user.getJournals().removeIf(x -> x.getId().equals(id));
            if(removed) {
                userService.saveUser(user);
                journalRepo.deleteById(id);
                return true;
            }

        }catch (Exception e){
            System.out.println("Error occured while deleting the journal : "+ e);
        }
        return false;
    }

    public Optional<Journal> getByID(ObjectId id){
        return journalRepo.findById(id);
    }


}
