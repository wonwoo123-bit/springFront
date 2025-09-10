package com.spring.application.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/summernote")
public record SummernoteController(@Value("${summernote.img}") String imgPath) {

    @PostMapping(value="/uploadImg")
	public ResponseEntity<String> uploadImg(MultipartFile file,
			                                HttpServletRequest request)	
															throws IOException {
		ResponseEntity<String> result = null;
		
		String savePath = imgPath.replace("/", File.separator);
		String fileName = UUID.randomUUID().toString()+".jpg"; 
		File saveFile = new File(savePath, fileName);

		saveFile.mkdirs();
		
		file.transferTo(saveFile);
		
		result = new ResponseEntity<String>(request.getContextPath() 
				+ "/summernote/getImg?fileName=" + fileName,HttpStatus.OK);
		return result;
	}


}