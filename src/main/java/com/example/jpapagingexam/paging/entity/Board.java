package com.example.jpapagingexam.paging.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
public class Board {

	@Id @GeneratedValue
	private Long id;

	private String author;

	private String title;

	private LocalDateTime createdAt;

	public Board(String author, String title, LocalDateTime createdAt) {
		this.author = author;
		this.title = title;
		this.createdAt = createdAt;
	}

}
