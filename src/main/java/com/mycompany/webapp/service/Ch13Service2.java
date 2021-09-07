package com.mycompany.webapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.view.Ch12FileListView;

@Service
public class Ch13Service2 {
	private static final Logger logger = LoggerFactory.getLogger(Ch12FileListView.class);

	public Ch13Service2() {
		logger.info("실행");
	}
}
