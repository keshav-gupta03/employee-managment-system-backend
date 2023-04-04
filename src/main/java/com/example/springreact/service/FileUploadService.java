package com.example.springreact.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadService {
	public final String upload="C:\\Users\\keshav.gupta_infobea\\Documents\\workspace-spring-tool-suite-4-4.14.0.RELEASE\\fullstack\\src\\main\\resources\\static\\images";
	
	@Value("${image.basePath}")
	String imageBasePath;
	
	public String uploadFile(MultipartFile file) {
		boolean bool=false;
		String path="";
		try {
			InputStream is=file.getInputStream();
			byte data[] =new byte[is.available()];
			is.read(data);
			
			FileOutputStream fos=new FileOutputStream(upload+File.separator+file.getOriginalFilename());
			fos.write(data);
		
			fos.flush();
			fos.close();
			
			bool=true;
			System.out.println(imageBasePath);
			path=imageBasePath+file.getOriginalFilename();
			System.out.println(path);	
		}
		
		catch(Exception e) {
			e.printStackTrace(); 
		}
		return path;
	}
}
