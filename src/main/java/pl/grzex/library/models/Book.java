package pl.grzex.library.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "books")
@Getter
@Setter
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(name = "book_name")
    private String bookName;
    @ManyToOne
    private Author author;
//    @Column(name = "purchase_date")
    private Date purchaseDate;

}
