package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;

import com.avaje.ebean.*;

@Entity
public class FinanceFolder extends Model{

	@Id
	private int id;
	private String name;
	private double total;
	@ManyToOne
	private User user;
	@OneToMany
	private List<Transaction> transactions = new ArrayList<>();
	

	public static Finder<Integer, FinanceFolder> find = new Finder<Integer, FinanceFolder>(Integer.class, FinanceFolder.class);

	
	public FinanceFolder (){
		this.total=0;
	}
	
	/**
	 * Getters and Setters
	 */
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total += total;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	

/**
 * Static Methods
 * 
 */
	

	//Might not need. Added @onetomany to User class so can find through user
	//Need to check if it works properly
	public static List<FinanceFolder> findAllForUser(String email){
		User user = User.getUser(email);
		return find.where().eq("user", user).findList();
	}
	
	public static FinanceFolder findByName(User user, String name){
		return find.where().eq("name", name).eq("user", user).findUnique();
	}
	
	
	//Delete
	public static FinanceFolder delete(int folderId){
		FinanceFolder folder = find.ref(folderId);
		folder.delete();
		return folder;
	}


}