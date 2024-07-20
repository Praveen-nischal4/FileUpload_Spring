package com.fileupload.dao;

import com.fileupload.dto.UserDTO;

public interface UserDAO {

	public int SaveDetails(UserDTO userDTO);
	public UserDTO getUserDetails(int rollno);
}
