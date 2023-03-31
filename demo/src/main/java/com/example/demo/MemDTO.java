package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//파라미터가 없는 생성자를 자동으로 생성해주는 것
@NoArgsConstructor
//모든 멤버변수를 제공하는 생성자
@AllArgsConstructor

@Getter
@Setter
public class MemDTO {
	private String name;
	private int age;
	private String loc;
	
	
	
	
}//end class
