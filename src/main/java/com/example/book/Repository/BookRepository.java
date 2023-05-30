package com.example.book.Repository;

import com.example.book.Entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    // JpaRepository를 쓰는 순간 bookRepository는 자연스럽게 객체로 등록됨.
}
