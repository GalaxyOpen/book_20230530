package com.example.book;

import com.example.book.DTO.BookDTO;
import com.example.book.Repository.BookRepository;
import com.example.book.Service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
// Assertions 클래스가 가지고 있는 모든 static 메서드를 가져오겠다.
import static org.assertj.core.api.Assertions.*;
@SpringBootTest
// 테스트하기 위한 어노테이션
public class BookTest {
    //서비스에 있는 메서드를 호출해서 테스트를 해보려고 한다.
    @Autowired
    private BookService bookService;



    //도서 등록 테스트
    /**
     * 1. 신규 도서 데이터 생성
     * 2. save 메서드 호출해서 저장 처리
     * 3. 저장한 데이터의 id값을 가져오고
     * 4. 해당 id로 DB에서 조회를 한 뒤
     * 5. 1번에서 만든 객체의 책제목 값과 4번에서 조회한 객체의 책제목 값이
     *     일치 하는지를 판단하여
     * 6. 일치하면 테스트 성공, 일치하지 않으면 테스트 실패 출력?
     */

    private BookDTO newBook(){
        // 저장을 하기 위한 테스트 데이터
        BookDTO bookDTO = new BookDTO();

        bookDTO.setBookName("test book");
        bookDTO.setBookAuthor("test author");
        bookDTO.setBookPrice(10000);
        return bookDTO;
    }
    //



    @Test
    @Transactional
    @Rollback(value=true)
    public void bookSaveTest(){
        BookDTO bookDTO = newBook(); // test data ready
        Long saveId = bookService.save(bookDTO);// 저장을 위한 메서드 호출 후 id값 가져옴

        //id로 조회
        BookDTO findDTO = bookService.findById(saveId);

        //테스트용 데이터의 제목과 조회한 데이터의 제목이 일치하는지 확인
        assertThat(bookDTO.getBookName()).isEqualTo(findDTO.getBookName());

        //테스트 데이터는 왠만해선 흔적을 남기지 않는게 좋다.
    }
    @Test
    @Transactional
    @Rollback(value=true)
    @DisplayName("삭제 테스트")
    public void bookDeleteTest(){
        /**
         * 1. 새로운 데이터 저장
         * 2. 저장된 데이터의 id를 가져온다.
         * 3. 해당 id로 삭제처리를 한다.
         * 4. 해당 id로 조회했을 때, Null 이라면 성공
         */
        BookDTO bookDTO  = newBook();
        Long savedId =bookService.save(bookDTO);
        bookService.delete(savedId);
        assertThat(bookService.findById(savedId)).isNull();
    }
    @Test
    @Transactional
    @Rollback
    @DisplayName("수정 테스트")
    public void bookUpdateTest(){
        /**
         * 1. 새로운 데이터 저장
         * 2. 수정용 데이터 준비 및 수정 처리(제목만 변경)
         * 3. 데이터 조회
         * 4. 2번에서 저장한 제목과 3번에서 조회한 제목이 일치하면 수정 성공
         */
        //1
        BookDTO bookDTO = newBook();
        Long savedId = bookService.save(bookDTO);

        //2
        bookDTO.setId(savedId);
        bookDTO.setBookName("수정 제목");
        bookService.update(bookDTO);

        BookDTO dto = bookService.findById(savedId);

        //
        assertThat(dto.getBookName()).isNotEqualTo(bookDTO.getBookName());
    }
}















