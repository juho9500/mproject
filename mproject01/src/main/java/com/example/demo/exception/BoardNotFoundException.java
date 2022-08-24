package com.example.demo.exception;

import lombok.AllArgsConstructor;

//오류 메세지 :  글을 찾을 수 없습니다
@AllArgsConstructor
public class BoardNotFoundException extends RuntimeException{
	
}
