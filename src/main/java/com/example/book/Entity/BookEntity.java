package com.example.book.Entity;
import com.example.book.DTO.BookDTO;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
@Entity
@Getter
@Setter
@Table(name="book_table")

public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String bookName;

    @Column(length = 20)
    private String bookAuthor;

    @Column
    private int bookPrice;

    //기본생성자를 private으로
//    private BookEntity(){
//    } 실행하는데 문제는 없지만 1번째 줄이 오류가 나서 문제
    public static BookEntity toSaveEntity(BookDTO bookDTO){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookName(bookDTO.getBookName());
        bookEntity.setBookAuthor(bookDTO.getBookAuthor());
        bookEntity.setBookPrice(bookDTO.getBookPrice());
        return bookEntity;
    }

}
