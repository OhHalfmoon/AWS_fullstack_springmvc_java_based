package com.ohalfmoon.domain;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("member")
public class MemberVO {
	private String userid;
	private String userpw;
	private	String userName;
	private boolean enabled;	
	private Date regDate;
	private Date updateDate;
	private List<AuthVO> auths;
	
}
