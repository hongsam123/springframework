package com.mycompany.webapp.view;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

@Component
public class Ch12FileDownloadView extends AbstractView{
	private static final Logger logger = LoggerFactory.getLogger(Ch12FileDownloadView.class);

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		logger.info("실행");
		
		String fileName = (String) model.get("fileName");
		String userAgent = (String) model.get("userAgent");
		
		String contentType = request.getServletContext().getMimeType(fileName);	//파일명의 확장명을 보고 자동으로 mime type을 리턴
		String savedName = fileName;
		String originalFileName = fileName;
	
		response.setContentType(contentType);

		if (userAgent.contains("Trident") || userAgent.contains("MSIE")) {
			// IE
			originalFileName = URLEncoder.encode(originalFileName, "UTF-8");
		} else {
			originalFileName = new String(originalFileName.getBytes("UTF-8"), "ISO-8859-1");
		}

		response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFileName + "\"");

		//파일로부터 데이터를 읽는 입력 스트림 생성
		String filePath = "C:/hyndai_it&e/upload_files/" + savedName;
		InputStream is = new FileInputStream(filePath);

		//응답 바디에 출력하는 출력 스트림 얻기
		OutputStream os = response.getOutputStream();

		//입력 스트림 -> 출력스트림
		FileCopyUtils.copy(is, os);
		is.close();
		os.flush();
		os.close();
	}

}
