package com.example.book.DTO;

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
}
