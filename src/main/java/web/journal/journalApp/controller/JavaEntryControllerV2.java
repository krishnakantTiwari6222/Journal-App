package web.journal.journalApp.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import web.journal.journalApp.entity.JournalEntry;
import web.journal.journalApp.entity.User;
import web.journal.journalApp.service.JournalEntryService;
import web.journal.journalApp.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping ("/journal")
public class JavaEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAllEntry(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        List<JournalEntry> all= journalEntryService.getAll();
        if(!all.isEmpty() && all != null){
            return new ResponseEntity<>(all,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @PostMapping()
    public ResponseEntity<Boolean> createEntry(@RequestBody JournalEntry myEntry) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/" +
            "{myId}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUsername(userName);
        List<JournalEntry> islist = user.getJournalEntryList().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if(!islist.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.getById(myId);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("id/{myId}")
    public ResponseEntity<Boolean> deleteEntry(@PathVariable ObjectId myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        boolean removed = journalEntryService.deleteaById(myId, userName);
        if(removed){
            return new ResponseEntity<>(true,HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("id/" +
    "{myId}")

    public ResponseEntity<JournalEntry> updateEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry myEntry){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUsername(userName);
        List<JournalEntry> islist = user.getJournalEntryList().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if(!islist.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.getById(myId);
            if(journalEntry.isPresent()){
                JournalEntry old = journalEntry.get();
                old.setTitle(myEntry.getTitle() != null && !myEntry.getTitle().equals("") ? myEntry.getTitle() : old.getTitle());
                old.setContent(myEntry.getContent() != null && !myEntry.getContent().equals("") ? myEntry.getContent() : old.getContent());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(old,HttpStatus.FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
