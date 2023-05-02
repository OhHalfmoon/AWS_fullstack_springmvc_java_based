package com.ohalfmoon.task;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ohalfmoon.controller.UploadController;
import com.ohalfmoon.domain.AttachVO;
import com.ohalfmoon.mapper.AttachMapper;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class FileCheckTask {
	// 초 분 시 일 월 요일 (년)
	// 2주 4주의 목요일 02시 마다
	// 0 0 2 * * 4#2, 4#4"
	@Autowired
	AttachMapper attachMapper;
	
	@Scheduled(cron = "0 * * * * *")
	public void check() {
	//	log.warn("file check");
	}
//	@Scheduled(cron = "0 0 2 * * *")
	public void checkFiles() {
		log.warn("============= DB 목록 =============");
		List<AttachVO> fileList =  attachMapper.getOldFiles();
		fileList.forEach(log::warn);
		log.warn("총 갯수 : " + fileList.size());
		log.warn("============= 어제 날짜 =============");
		log.warn(getYesterday());
		log.warn("============= FS 목록 =============");
		List<File> files = fileList.stream().map(vo -> vo.getOriginFile()).collect(Collectors.toList());
		fileList.stream().filter(vo->vo.isImage()).forEach(vo->files.add(vo.getThumbFile()));
		
		files.forEach(f->log.warn(f.exists()));
		
		log.warn("============= FS의 폴더 내 모든 파일 목록 =============");
//		log.warn(new ArrayList<>(Arrays.asList(new File(UploadController.getPATH(), getYesterday()).listFiles())));
		List<File> realfiles = new ArrayList<>(Arrays.asList(new File(UploadController.getPATH(), getYesterday()).listFiles()));

		realfiles.forEach(log::warn);
		
		log.warn("======== FS의 폴더 내 모든 파일 목록 중 DB와 일치하지 않는 파일 목록 ========");
		realfiles = new ArrayList<>(Arrays.asList(new File(UploadController.getPATH(), getYesterday()).listFiles(f -> !files.contains(f))));
		realfiles.forEach(log::warn);
		realfiles.forEach(f->f.delete());
	}
	public static void main(String[] args) {
		
	}
	private String getYesterday() {
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date().getTime()-1000 * 60 * 60 * 24);
	}
}
