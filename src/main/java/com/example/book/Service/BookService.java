package com.example.book.Service;

import com.example.book.DTO.BookDTO;
import com.example.book.Entity.BookEntity;
import com.example.book.Repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    // 기존과는 다르게 DTO를 Repository로 넘기기 전에 entity로 넘기는 과정이 중요하다.
    public Long save(BookDTO bookDTO) {
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setBookName(bookDTO.getBookName());
//        bookEntity.setBookAuthor(bookDTO.getBookAuthor());
//        bookEntity.setBookPrice(bookDTO.getBookPrice());
//        BookEntity bookEntity = toSaveEntity(bookDTO);
        // 여기서 정말 많은 실수가 남. set과 get 조심합시다 // DTO와 메소드 이름 조심합시다.
        BookEntity bookEntity = BookEntity.toSaveEntity(bookDTO);
//        bookRepository.save(bookEntity);
        // JpaRepository에서 save 메서드는 Entity를 매개변수로 쓴다

        //저장 처리 후 저장한 데이터의 id 값을 리턴
        return bookRepository.save(bookEntity).getId();
    }

//    private BookEntity toSaveEntity(BookDTO bookDTO){
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setBookName(bookDTO.getBookName());
//        bookEntity.setBookAuthor(bookDTO.getBookAuthor());
//        bookEntity.setBookPrice(bookDTO.getBookPrice());
//        return bookEntity;
//    }
    public List<BookDTO> findAll(){
        List<BookEntity> bookEntityList = bookRepository.findAll();
        //레파지토리로부터 뭔가를 받아오는건 거의 Entity 형태로 받아온다.
        //이제 여기서 List<BookEntity> 리스트를 List<BookDTO>로 옮겨서 컨트롤러 쪽으로 넘길 예정
        List<BookDTO>  bookDTOList = new ArrayList<>();
        for(BookEntity bookEntity: bookEntityList){
            /* 현재 DB에는 Entity가 담겨 있는 리스트를 가져왔다.
             그럼 이걸 우리는 DTO가 담긴 리스트로 변환해서 담을 예정이다.
             1. Entity -> 를 DTO로 변환하기 위한 메서드가 호출되어야 한다.
             메서드는 BookDTO에 정의해야 한다.
             2. 호출결과를 DTO 객체로 받는다.
             3. DTO객체를 DTO리스트에 추가
             4. 반복문 종료 후 DTO 리스트를 리턴 */
           BookDTO bookDTO = BookDTO.toDTO(bookEntity);
           bookDTOList.add(bookDTO);
//위 두 줄 한 줄로 표현 =  bookDTOList.add(BookDTO.toDTO(bookEntity));
        }
        return bookDTOList;
    }


    public BookDTO findById(Long id) {
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(id);
        if(optionalBookEntity.isPresent()){
            System.out.println("있다");
            //optional 객체에서 꺼내기
            BookEntity bookEntity = optionalBookEntity.get();
            // 이제 꺼냈으니, BookEntity - > BookDTO로 변환
            BookDTO bookDTO = BookDTO.toDTO(bookEntity);
            return bookDTO;

            // return BookDTO.toDTO(optionalBookEntity.get());
        }else{
            System.out.println("없다");
            return null;
        }
        // optional : Java 클래스. NullPoint가 뜨는걸 좀 포장지같은거로 감쌌다고 생각하면 됨.
        // 쓸만한 건 isEmpty // isPresent
        // 매우 귀찮긴 하지만 isPresent 알고 있어야 함.
    }

    public void update(BookDTO bookDTO) {
        BookEntity bookEntity = BookEntity.toUpdateEntity(bookDTO);


        BookEntity savedEntity = bookRepository.save(bookEntity);

    }
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }


}
