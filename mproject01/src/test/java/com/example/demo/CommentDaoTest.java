package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.transaction.annotation.*;

import com.example.demo.dao.*;
import com.example.demo.entity.*;

@SpringBootTest
public class CommentDaoTest {
	@Autowired
	private CommentDao dao;
	//save테스트 
	//@Test
	public void saveTest() {
		assertEquals(1,dao.save(Comment.builder().bno(1).content("zzz")
				.writer("winter").build()));
	}
	//findByBno테스트 
	//@Test
	public void findByBnoTest() {
		assertEquals(1, dao.findByBno(1).size());
	}
	//findWriterByBno테스트
	//@Test
	public void findWriterByBnoTest() {
		assertEquals(true, dao.findWriterById(28).isEmpty());
		assertEquals(true, dao.findWriterById(1).isPresent());
	}
	//deleteByCno테스트
	@Transactional
	//@Test
	public void deleteByCnoTest() {
		assertEquals(1, dao.deleteByCno(1));
	}
	//deleteByBno테스트
	@Transactional
	//@Test
	public void deleteByBnoTest() {
		assertNotEquals(0, dao.deleteByBno(1));
	}
}
















