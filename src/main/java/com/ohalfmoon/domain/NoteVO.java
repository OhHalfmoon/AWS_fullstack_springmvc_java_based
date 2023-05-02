package com.ohalfmoon.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteVO {
	private Long noteno;
	private String sender;
	private String receiver;
	private Date sdate;
	private Date rdate;
	private String message;
}
