package com.mycompany.webapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mycompany.webapp.controller.Ch10Controller;

//객체로 생성해서 관리하도록 설정
@Component
//모든 컨트롤러에 영향을 미치는 설정
@ControllerAdvice
public class Ch10ExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(Ch10Controller.class);

	public Ch10ExceptionHandler() {
		logger.info("Exception Handler 객체 생성");
	}
	
	//예외 처리자 설정
	@ExceptionHandler
	public String handleNullPointerException(NullPointerException e) {
		logger.info("handleException 실행");
		e.printStackTrace();
		return "error/500_null";
	}
	
	@ExceptionHandler
	public String handleClassCastException(ClassCastException e) {
		logger.info("handleClassCastException 실행");
		e.printStackTrace();
		return "error/500_cast";
	}

	@ExceptionHandler
	public String handleRuntimeException(RuntimeException e) { 
		logger.info("handleRuntimeException 실행");
		e.printStackTrace();
		return "error/500";
	}
	
	@ExceptionHandler
	public String handleSoldOutException (Chap10SoldOutException e) { 
		logger.info("Chap10SoldOutException 실행");
		e.printStackTrace();
		return "error/soldout";
	}
	
	@ExceptionHandler
	public String handleNotFoundAccountException (Ch16NotFoundAccountException e, Model model) { 
		logger.info("handleNotFoundAccountException 실행");
		e.printStackTrace();
		model.addAttribute("error", e.getMessage());
		return "error/notFoundAccount";
	}
}