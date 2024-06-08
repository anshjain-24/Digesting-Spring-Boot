package project.springboot.journal_app.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;


// we may write
// @Data : it includes all getter,setter, NoArgsConstructor ,AllArgsConstructor etc.

@Document     // => to tell springboot project that this class is to be mapped as document in MongoDB
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Journal {
    @Id
    private ObjectId id;
    private String title;
    private String content;

    private LocalDate date;

}
