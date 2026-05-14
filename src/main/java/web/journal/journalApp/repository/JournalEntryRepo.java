package web.journal.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import web.journal.journalApp.entity.JournalEntry;

public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {

}
