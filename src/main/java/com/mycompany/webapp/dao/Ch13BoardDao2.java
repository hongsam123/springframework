package com.mycompany.webapp.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.view.Ch12FileListView;

@Repository
public class Ch13BoardDao2 {
	private static final Logger logger = LoggerFactory.getLogger(Ch13BoardDao2.class);
	
	public Ch13BoardDao2() {
		logger.info("실행");
	}
	public void update() {
		logger.info("update 실행");
	}
}
