package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.TodoDTO;

//데이터에 접근하기 위한 객체로 사용 

@Mapper
@Repository
public interface TodoDAO {
	
	public List<TodoDTO> getTodoList() throws Exception;

}
