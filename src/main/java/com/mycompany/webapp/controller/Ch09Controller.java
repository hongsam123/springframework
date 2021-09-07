package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/ch09")
public class Ch09Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch09Controller.class);

	@RequestMapping("/content")
	public String content() {
		logger.info("ch09 실행");
		return "ch09/content";
	}

	@PostMapping("/fileUpload")
	public String fileUpload(String title, String desc, MultipartFile attach)
			throws IllegalStateException, IOException {
		logger.info("fileUpload 실행");

		logger.info("title : " + title);
		logger.info("desc : " + desc);

		logger.info("file originalname: " + attach.getOriginalFilename());
		logger.info("file contentType: " + attach.getContentType());
		logger.info("file size: " + attach.getSize());

		String savedname = new Date().getTime() + "-" + attach.getOriginalFilename();
		File file = new File("C:/hyndai_it&e/upload_files/" + savedname);
		attach.transferTo(file);

		return "redirect:/ch09/content";
	}

	@PostMapping(value = "/fileUploadAttach", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String fileUploadAttach(String title, String desc, MultipartFile attach)
			throws IllegalStateException, IOException {
		logger.info("fileUploadAttach 실행");

		logger.info("title : " + title);
		logger.info("desc : " + desc);

		logger.info("file originalname: " + attach.getOriginalFilename());
		logger.info("file contentType: " + attach.getContentType());
		logger.info("file size: " + attach.getSize());

		String savedname = new Date().getTime() + "-" + attach.getOriginalFilename();
		File file = new File("C:/hyndai_it&e/upload_files/" + savedname);
		attach.transferTo(file);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		jsonObject.put("savedname", savedname);
		String json = jsonObject.toString();

		return json;
	}

	/* 
	@GetMapping(value="/fileDownload", produces="images/jpeg")
	@ResponseBody
	public byte[] fileDownload(String savedname) throws IOException {
		String filePath = "C:/hyndai_it&e/upload_files/"+ savedname;
		InputStream is = new FileInputStream(filePath);
		byte[] data = IOUtils.toByteArray(is);
		return data;
	}
	*/

	@GetMapping("/fileDownload")
	@ResponseBody
	public void fileDownload(int fileNo, HttpServletResponse response, @RequestHeader("User-Agent") String userAgent)
			throws IOException {
		String contentType = "img/jpeg";
		String savedName = "1630656700227-눈내리는마을.jpg";
		String originalFileName = "눈내리는마을.jpg";
	
		response.setContentType(contentType);

		if (userAgent.contains("Trident") || userAgent.contains("MSIE")) {
			// IE
			originalFileName = URLEncoder.encode(originalFileName, "UTF-8");
		} else {
			originalFileName = new String(originalFileName.getBytes("UTF-8"), "ISO-8859-1");
		}

		response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFileName + "\"");

		String filePath = "C:/hyndai_it&e/upload_files/" + savedName;
		InputStream is = new FileInputStream(filePath);


		OutputStream os = response.getOutputStream();

		//입력 스트림 -> 출력스트림
		FileCopyUtils.copy(is, os);
		is.close();
		os.flush();
		os.close();
	}
}