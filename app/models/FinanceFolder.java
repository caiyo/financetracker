package models;

import javax.persistence.*;

import play.db.ebean.*;

import com.avaje.ebean.*;

@Entity
public class FinanceFolder extends Model{

	@Id
	public int id;
	public String name;
	public int total;
	@ManyToOne
	public User user;
	public static Finder<Integer, FinanceFolder> find = new Finder<Integer, FinanceFolder>(Integer.class, FinanceFolder.class);

	/**Not sure if constructors are needed 
	 * ~~~~~~~~NEED TO CHECK~~~~~~~
	public FinanceFolder (String name, User user){
		this.name=name;
		this.user=user;
		this.total=0;
	}*/

	/*
	 * CRUD operations
	 */
	
	//Create  
	public static FinanceFolder add(FinanceFolder folder, User user){
		folder.user = user;
		folder.total = 0;
		return folder;
	}
	
	//Read & Find
	//public static 
	
	//Update
	public static FinanceFolder update(FinanceFolder folder){
		return folder;
	}
	
	//Delete



}