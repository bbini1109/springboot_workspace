package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.TodoDTO;

public interface TodoService {
	public List<TodoDTO> search() throws Exception;

}
