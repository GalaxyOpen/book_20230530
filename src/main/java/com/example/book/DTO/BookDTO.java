package com.example.book.DTO;

import com.example.book.Entity.BookEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class BookDTO {
    private Long id;
    private String bookName;
    private String bookAuthor;
    private int bookPrice;

    public static BookDTO toDTO(BookEntity bookEntity) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(bookEntity.getId());
        bookDTO.setBookName(bookEntity.getBookName());
        bookDTO.setBookPrice(bookDTO.getBookPrice());
        bookDTO.setBookAuthor(bookDTO.getBookAuthor());
        return bookDTO;
    }
}
