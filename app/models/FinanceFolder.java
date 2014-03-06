package models;

import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;

import com.avaje.ebean.*;

@Entity
public class FinanceFolder extends Model{

	@Id
	public int id;
	public String name;
	public double total;
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
	public static List<FinanceFolder> findAllForUser(User user){
		return find.where().eq("user", user).findList();
	}
	
	public static FinanceFolder findByName(User user, String name){
		return find.where().eq("name", name).eq("user", user).findUnique();
	}
	

	
	//Update name of folder
	public static FinanceFolder rename(int folderId, String newName){
		FinanceFolder folder = find.ref(folderId);
		folder.name = newName;
		folder.update();
		return folder;
	}
	
	//update total
	public  FinanceFolder updateTotal(double newAmount){
		total+=newAmount;
		update();
		return this;
		
	}
	
	//Delete
	public static FinanceFolder delete(int folderId){
		FinanceFolder folder = find.ref(folderId);
		folder.delete();
		return folder;
	}


}