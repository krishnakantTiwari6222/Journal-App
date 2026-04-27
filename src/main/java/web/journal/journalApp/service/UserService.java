package web.journal.journalApp.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.journal.journalApp.JournalApplication;
import web.journal.journalApp.entuity.JournalEntry;
import web.journal.journalApp.repository.JournalEntryRepo;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll (){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById (ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteaById(ObjectId myId){
        journalEntryRepository.deleteById(myId);
    }
}
