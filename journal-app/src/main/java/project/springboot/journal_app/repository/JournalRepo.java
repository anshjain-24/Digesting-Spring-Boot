package project.springboot.journal_app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import project.springboot.journal_app.entity.Journal;

public interface JournalRepo extends MongoRepository<Journal, ObjectId> {
}
