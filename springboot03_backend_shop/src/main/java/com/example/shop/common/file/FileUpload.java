package com.example.shop.common.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	         //첨부파일명 앞에 난수를 줘서 파일명 중복방지
	public static UUID saveCopyFile(MultipartFile file, String filePath) {
		String fileName = file.getOriginalFilename();
		
		//중복파일명을 처리하기 위한 난수 발생
		UUID random = UUID.randomUUID();
	
		//File ff = new File(urlPath(filePath), random + "_" + fileName);
		File ff = new File(filePath, random + "_" + fileName);
		
		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(ff)); //클라이언트에서 넘어온 첨부파일을 웹서버에 저장
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		return random;
	}//end saveCopyfile()
	
	public static String urlPath(HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("/");
		System.out.print("root" + root);
		String saveDirectory = root + "temp" + File.separator;		

		File fe = new File(saveDirectory);
			if(!fe.exists())
				fe.mkdir();
				
		return saveDirectory; //첨부파일이 저장될 위치
	}

}//end class

















