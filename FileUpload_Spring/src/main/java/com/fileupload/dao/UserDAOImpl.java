package com.fileupload.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fileupload.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO{

	@Autowired
	private JdbcTemplate jdbctemplate;
	
	@Override
	public int SaveDetails(UserDTO userDTO) {
		
		String sql ="insert into users (name,age,photo_path) values (?,?,?) ";
	 	jdbctemplate.update(sql,userDTO.getName(),userDTO.getAge(),userDTO.getPhoto_path());
	 	
	 	KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbctemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userDTO.getName());
            ps.setInt(2, userDTO.getAge());
            ps.setString(3, userDTO.getPhoto_path());
            return ps;
        }, keyHolder);

        // Retrieve the generated key
        int rollno =keyHolder.getKey().intValue();
        return rollno;
	}
	@Override
	public UserDTO getUserDetails(int rollno) {
	
		String SQL_QUERY ="select name,age,photo_path from users where rollno =?";
		
		return jdbctemplate.queryForObject(SQL_QUERY, new Object[] {rollno}, new DetailsRowMapper());
	}
	
	public class DetailsRowMapper implements RowMapper<UserDTO> {

		@Override
		public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			UserDTO userDetails = new UserDTO();
			
			userDetails.setName(rs.getString("name"));
			userDetails.setAge(rs.getInt("age"));
			userDetails.setPhoto_path(rs.getString("photo_path"));
			return userDetails;
		}
		
	}

}
