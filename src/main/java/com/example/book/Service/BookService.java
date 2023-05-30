package com.example.book.Service;

import com.example.book.DTO.BookDTO;
import com.example.book.Entity.BookEntity;
import com.example.book.Repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    // 기존과는 다르게 DTO를 Repository로 넘기기 전에 entity로 넘기는 과정이 중요하다.
    public void save(BookDTO bookDTO) {
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setBookName(bookDTO.getBookName());
//        bookEntity.setBookAuthor(bookDTO.getBookAuthor());
//        bookEntity.setBookPrice(bookDTO.getBookPrice());
//        BookEntity bookEntity = toSaveEntity(bookDTO);
        // 여기서 정말 많은 실수가 남. set과 get 조심합시다 // DTO와 메소드 이름 조심합시다.
        BookEntity bookEntity = BookEntity.toSaveEntity(bookDTO);
        bookRepository.save(bookEntity);
        // JpaRepository에서 save 메서드는 Entity를 매개변수로 쓴다


    }

//    private BookEntity toSaveEntity(BookDTO bookDTO){
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setBookName(bookDTO.getBookName());
//        bookEntity.setBookAuthor(bookDTO.getBookAuthor());
//        bookEntity.setBookPrice(bookDTO.getBookPrice());
//        return bookEntity;
//    }


}
