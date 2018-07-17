package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import user.User;



public class DBHelper {

  private ResultSet users = null;

public List<User> getUsers(DataSource dataSource) throws SQLException {
	
	Connection conn = null;
	Statement stmt = null;
    List<User> usersList = new ArrayList<>();

	try{
	conn = dataSource.getConnection();
	stmt = conn.createStatement();
	String query = "select * from users";
	users = stmt.executeQuery(query);
	
	while(users.next()){
		int user_id = users.getInt("user_id");
		String name = users.getString("name");
		String email = users.getString("email");
		User temp = new User(user_id, name,email);
		usersList.add(temp );
	}
	
	}catch(Exception e){
		
		e.printStackTrace();
		return usersList;
	}finally{
		conn.close();
		stmt.close();
	}
	return usersList;
}

public boolean addUser(User user, DataSource dataSource) throws SQLException{
	Connection conn = null;
	PreparedStatement stmt = null;
	try{
		conn = dataSource.getConnection();
		String name = user.getName();
		String email = user.getEmail();
		String query = "insert into users (name,email) values (?,?)";
		stmt = conn.prepareStatement(query);
		stmt.setString(1, name);
		stmt.setString(2, email);
	    return stmt.execute();
		
		
	}catch(Exception e){
		e.printStackTrace();
		return false;
		
	}finally{
		conn.close();
		stmt.close();
	}
	
}


  
}
