package web.journal.journalApp.service;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.journal.journalApp.entity.JournalEntry;
import web.journal.journalApp.entity.User;
import web.journal.journalApp.repository.JournalEntryRepo;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepository;

    @Autowired
    private UserService userService;


    @Transactional
    public void saveEntry(JournalEntry journalEntry){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userService.findByUsername(userName);
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntryList().add(saved);
            userService.SaveUser(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("Error thiorws with the usser set to null",e);
        }


    }

    public List<JournalEntry> getAll (){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById (ObjectId id){
        return journalEntryRepository.findById(id);
    }


    @Transactional
    public boolean deleteaById(ObjectId myId, String usename){
        boolean removed = false;
        try {
            User user = userService.findByUsername(usename);
            removed = user.getJournalEntryList().removeIf(x -> x.getId().equals(myId));
            if (removed){
                userService.SaveUser(user);
                journalEntryRepository.deleteById(myId);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  removed;
    }
}
