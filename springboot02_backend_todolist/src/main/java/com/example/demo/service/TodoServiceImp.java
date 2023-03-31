package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TodoDAO;
import com.example.demo.dto.TodoDTO;


@Service
public class TodoServiceImp implements TodoService {
	
	 //TodoDAO에 되어있는 것이 자동으로 연결 
	 @Autowired
	 private TodoDAO todoDAO;
		
	 public TodoServiceImp() {
		
	}

	@Override
	public List<TodoDTO> search() throws Exception {
		
		return todoDAO.getTodoList();
	}
}
