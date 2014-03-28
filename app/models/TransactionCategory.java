package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

@Entity
public class TransactionCategory extends Model{
	
	
	@Id
	public int id;
	public String category;
	
	
	/**
	 * Getters and setters
	 */
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
	/*
	 * CRUD operations
	 */
	
	//Create  
	
	//Read & Find
	
	//Update
	
	//Delete
}
