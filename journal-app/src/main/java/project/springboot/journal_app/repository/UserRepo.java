package project.springboot.journal_app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import project.springboot.journal_app.entity.User;

public interface UserRepo extends MongoRepository<User, ObjectId> {

    User findByUsername(String username);
}
