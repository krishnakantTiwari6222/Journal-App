package web.journal.journalApp.controller;

import org.springframework.web.bind.annotation.*;
import web.journal.journalApp.JournalApplication;
import web.journal.journalApp.entuity.JournalEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/journal")
public class JavaEntryController {
    Map<Long , JournalEntry> journalEntrie = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getall(){
        return new ArrayList<>(journalEntrie.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        journalEntrie.put(myEntry.getId(), myEntry);
        return true;
    }

    @GetMapping("id/" +
            "{myId}")
    public JournalEntry getEntryById(@PathVariable long myId){
        return journalEntrie.get(myId);
    }


    @DeleteMapping("id/{myId}")
    public JournalEntry deleteEntry(@PathVariable long myId){
        return journalEntrie.remove(myId);
    }


    @PutMapping("id/" +
    "{myId}")

    public JournalEntry updateEntry(@PathVariable long myId, @RequestBody JournalEntry myEntry){
        return journalEntrie.put(myId, myEntry);
    }


}
