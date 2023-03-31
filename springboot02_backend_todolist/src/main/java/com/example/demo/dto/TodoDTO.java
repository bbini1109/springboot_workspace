package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/*
  	 @Data : @NoArgsConstructor, @AllArgsConstructor, @Getter, @Setter, @ToString, @EqualAndHashCode를
 		     합쳐놓은 어노테이션이다. 
*/


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TodoDTO {
	private int id;
	private int completed;
	private String todoname;

}
