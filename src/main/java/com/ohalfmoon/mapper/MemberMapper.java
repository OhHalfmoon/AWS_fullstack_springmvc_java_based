package com.ohalfmoon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.ohalfmoon.domain.MemberVO;

public interface MemberMapper {
    MemberVO read(String userid);
}
