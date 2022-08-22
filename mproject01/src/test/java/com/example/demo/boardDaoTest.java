package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.BoardDao;
import com.example.demo.dao.CommentDao;
import com.example.demo.entity.Board;

//JUnit은 단위 테스트 - 메소드의 동작을 확인
@SpringBootTest
public class boardDaoTest {
	@Autowired
	private BoardDao boardDao;
	@Autowired
	private CommentDao commentdao;
	//Test 케이스 1.Dao생성
	//@Test
	public void initTest() {
		assertNotNull(boardDao);
	}
	//insert, delete, update의 실행 경과는 변경된 행의 개수
	//Test 케이스 2. save :  Board -> 결과값이 1이다
	//Transaction:일련의 sql을 모아서 하나의 작업으로 지정
	//					함께 commit되거나 rollback되어야 한다(일부분만 동작할 수 있다)
	@Transactional
	//@Test
	public void saveTest() {
		Board board = Board.builder().title("vvv").content("yyy").writer("spring").build();
		assertEquals(1, boardDao.save(board));
	}
	//Test 케이스 3. count->개수를 수동으로 확인해서 assert한다
	//@Test
	public void countTest() {
		
		assertEquals(6, boardDao.count(null));
	}
	//Test 케이스 4. findAll : 글이 6개 있다. 2~5까지 읽어오자 
	//@Test
	public void findAllTest() {
		assertEquals(4, boardDao.findAll(null, 2, 6).size());
	}
	//Test케이스 5.(내용, 제목).조회수, 좋아요, 싫어요에 대해 값을 주면 update
	//@Transactional
	//@Test
	public void updateTest() {
		assertNotEquals(0,boardDao.update(Board.builder().bno(1).readCnt(1).build()));
		assertNotEquals(0,boardDao.update(Board.builder().bno(1).goodCnt(1).build()));
		assertNotEquals(0,boardDao.update(Board.builder().bno(1).commentCnt(15).build()));
		assertNotEquals(0,boardDao.update(Board.builder().bno(1).title("변경").content("변경").build()));
	}
	//Test 케이스 6. 7번글을 읽으면 비어있다. 1번글을 읽으면 존재한다 
	//바람직하지 않은 결과도 테스트 하여야한다 
	//@Test
	public void findByIdTest() {
		assertEquals(true,boardDao.findById(28).isEmpty());
		assertEquals(true,boardDao.findById(1).isPresent());
	}
	//Test 케이스7.28번글이 없다, 1번글의 글쓴이는 있다
	//@Test
	public void findWriterTest() {
		assertEquals(true,boardDao.findWriterById(28).isEmpty());
		assertEquals(true,boardDao.findWriterById(1).isPresent());
	}
	//Test 케이스 8.28번글의 삭제 결과는 0 1번글의 삭제 결과는 1 
	//@Transactional
	//@Test
	public void deleteByIdTest() {
		assertEquals(1, boardDao.deleteById(28));
		assertEquals(1, boardDao.deleteById(1));
	}
	
}
