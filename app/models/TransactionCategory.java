package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

@Entity
public class TransactionCategory extends Model{
	
	@Id
	public int id;
	public String category;
	
	/*
	 * CRUD operations
	 */
	
	//Create  
	
	//Read & Find
	
	//Update
	
	//Delete
}
