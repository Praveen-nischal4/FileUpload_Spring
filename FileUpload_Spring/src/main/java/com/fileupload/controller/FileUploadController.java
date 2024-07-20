package com.fileupload.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fileupload.dao.UserDAO;
import com.fileupload.dto.UserDTO;
import com.itextpdf.io.exceptions.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	
	@Autowired
	private UserDAO userDAO;

	@GetMapping("/myHome")
	public String getHomePage()
	{
		return "myhome";
	}
	
	
	
	
	@PostMapping("/saveDetails")
	public String saveData(@RequestParam("name") String name, @RequestParam("age") int age, @RequestParam("photo") MultipartFile photo,Model model) throws java.io.IOException {
		try
		{
		String imagePath = SaveImageFile(photo);
		
		UserDTO userDetails = new UserDTO();
		userDetails.setName(name);
		userDetails.setAge(age);
		userDetails.setPhoto_path(imagePath);
		
		//save data to db
		
		int rollno =userDAO.SaveDetails(userDetails);
		model.addAttribute("message", "Details save successfully");
		model.addAttribute("rollno",rollno);
		
	  return "uploadSuccess";
      }catch(IOException e)
		{
    	  e.printStackTrace();
    	  model.addAttribute("message", "Failed to upload user details");
          return "uploadError";
		}
	}

	private String SaveImageFile(MultipartFile photo) throws java.io.IOException {
	
		//define the directory to save the file
		
		String uploadDirectory ="C:\\Users\\PRAVEEN\\eclipse-workspace\\FileUpload_Spring\\src\\main\\webapp\\resources\\static\\images";
		
		File uploadFile = new File(uploadDirectory);
		
		if(!uploadFile.exists())
		{
			uploadFile.mkdir();
		}
		
		//save the file 
		
		String filename = "groc" + photo.getOriginalFilename();
		String path = uploadDirectory + "/" + filename;
		
	Files.copy(photo.getInputStream(), Paths.get(path));
		
		return path;
	}
	
	 @GetMapping("/getPhoto")
	 public ResponseEntity<Resource> getPhotograph(@RequestParam("rollno") int rollno) throws java.io.IOException
	 {
		 //check userdetails
		 
		 UserDTO userdetails = userDAO.getUserDetails(rollno);
		 
		 //check user exit and have valid photograph path
		 if(userdetails !=null & userdetails.getPhoto_path()!=null)
		 {
			 //finally load image from path
			 
			 Resource resource = LoadImageFile(userdetails.getPhoto_path());
			 
			 return ResponseEntity.ok()
					 .header(HttpHeaders.CONTENT_TYPE,"image/jpg")
					 .contentLength(resource.contentLength())
					 .body(resource);
			 
		 }
		 
		 
		 
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		 
	 }

	private Resource LoadImageFile(String photo_path) {
		
		return new FileSystemResource(new File(photo_path));
	}
	
}
