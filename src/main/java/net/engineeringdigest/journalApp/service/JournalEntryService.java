package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Component
@Slf4j
public class JournalEntryService {

@Autowired
    private JournalEntryRepo journalEntryRepo;

   @Autowired
   private  UserService userService;



   @Transactional
    public void saveEntry(JournalEntry journalEntry , String userName){
       try {
           User user =  userService.findByUserName(userName);
           journalEntry.setData(LocalDateTime.now());
           JournalEntry saved = journalEntryRepo.save(journalEntry);
           user.getJournalEntries().add(saved);
           userService.saveUser(user);
       } catch (Exception e) {
           log.error("Error", e);
           throw new RuntimeException(e);
       }
    }

    public void saveUser(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }


    public List<JournalEntry> getAll(){
       return journalEntryRepo.findAll();
    }


    public Optional<JournalEntry> findEntry(ObjectId id){
       return journalEntryRepo.findById(id);
    }

    @Transactional
    public boolean DeleteById(ObjectId id, String userName){
       boolean removed = false;
       try {
           User user = userService.findByUserName(userName);
           removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
           if (removed){
               userService.saveUser(user);
               journalEntryRepo.deleteById(id);
           }
       }catch (Exception e){
           log.error("Error", e);
           throw new RuntimeException("there was a error while deleteing by id");

       }
        return removed;



    }



}
