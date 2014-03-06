package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

@Entity
public class User extends Model{
	
	@Id
	public String name;
	public String email;
	public String password;	

	public static Finder<String, User> find  = new Finder<>(String.class, User.class);


	public User(String email, String name, String password){
		this.email=email;
		this.name=name;
		this.password=password;
	}


	public static User authenticate(String email, String password){
		return find.where().eq("email", email).eq("password", password).findUnique();
	}
	
	/*
	 * CRUD operations
	 */
	
	//Create  
	
	//Read & Find
	
	//Update
	
	//Delete
}
