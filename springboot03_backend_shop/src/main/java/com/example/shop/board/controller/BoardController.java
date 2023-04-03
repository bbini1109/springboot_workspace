package com.example.shop.board.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.shop.board.dto.BoardDTO;
import com.example.shop.board.dto.PageDTO;
import com.example.shop.board.service.BoardService;
import com.example.shop.common.file.FileUpload;

@RestController
public class BoardController {
	
	//자동 매핑
	@Autowired 
	private BoardService boardService;
	
				//넘어오는 첨부파일 저장되는 경로 
	@Value("${spring.servlet.multipart.location}")
	private String filePath;
	
	@Autowired
	private PageDTO pdto;
	
	private int currentPage;

	 public BoardController() {
		
	}
	 
	 	// http://localhost:8090/board/list/1
	 
	 	@GetMapping("/board/list/{currentPage}")
		public Map<String, Object> listExecute(@PathVariable("currentPage") int currentPage,  PageDTO pv) {
	 		Map<String, Object> map = new HashMap<>(); //선언하는 부분과 생성하는 부분이 같으면 생성할 때는 안써도 된다. <String, Object>를 <>로 써도 가능
			int totalRecord = boardService.countProcess();
			if(totalRecord >= 1) {
				//if(pv.getCurrentPage()==0)
					this.currentPage = currentPage;
			//	else					
			//		this.currentPage = pv.getCurrentPage();
				
				this.pdto = new PageDTO(this.currentPage, totalRecord);
				
			    map.put("aList",boardService.listProcess(this.pdto));
			    map.put("pv", this.pdto);
	 	}
			
			return map;
		}//end listExecute()
		
	 	
		//@RequestBody : json => 자바객체
	 	//@ResponseBody : 자바객체 => json
	 	//@PathVariable : /board/list/:num       => /board/list/{num}
	 	//@RequestParam : /board/list?num=value  => /board/list?num=1  => /board/list
	 	// multipart/form-data : @RequestBody 선언없이 그냥 받음 BoardDTO dto
		@PostMapping("/board/write")
		public String writeProExecute( BoardDTO dto, PageDTO pv, HttpServletRequest req, HttpSession session ) {
		   MultipartFile file = dto.getFilename();
		   
		   //System.out.println(dto.getMembersDTO().getMemberName());  //getMembersDTO()가 null값이기 때문에 getMemberName()을 가져올 수 없다.
		   
		   //파일 첨부가 있으면...(첨부파일이 있으면)
		  if(!file.isEmpty()) {
			  UUID random = FileUpload.saveCopyFile(file, filePath);
			  dto.setUpload(random + "_" + file.getOriginalFilename());
		  }
		  
		  dto.setIp(req.getRemoteAddr());
		  
		  //session에 저장되어있는 Email값을 넣어줌
//		  AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
//		  dto.setMemberEmail(authInfo.getMemberEmail());
		  
		  //service 호출
		  boardService.insertProcess(dto);
		  
		  //제목글이 아닐 때, 답변을 달면 답변 달고 그 페이지로 유지되는것. ex) 2페이지에 있는 글에 답변을 달고 저장 하고나서 계속 2페이지에 있는것.
		  //답변글이면 
		  if(dto.getRef() != 0) {
			  //ratt.addAttribute("currentPage", pv.getCurrentPage());
			  return String.valueOf(pv.getCurrentPage());
		  }else {
			  
			  return String.valueOf(1);
		  }
		  // return "redirect:/board/list.do";
		  
			
		}//end writeProExecute()
	 	
	 	
	 
	 
}//end class











