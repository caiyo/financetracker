package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;

import com.avaje.ebean.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class FinanceFolder extends Model{

	@Id
	private int id;
	private String name;
	private double total;
	@ManyToOne
	@JsonBackReference
	private User user;
	
	
	@OneToMany
	@JsonBackReference
	private List<Transaction> transactions = new ArrayList<>();
	

	public static Finder<Integer, FinanceFolder> find = new Finder<Integer, FinanceFolder>(Integer.class, FinanceFolder.class);
	
	public String validate(){
		String s = "";
		if (name == null){
			s +="invalid name\n";
		}
		if (total !=0){
			s +="invalid total\n";
		}
		if (s.isEmpty())
			return null;
		else{
			System.out.println(s);
			return s;
		}
	}
	
	/**
	 * Getters and Setters
	 */
	
	public int getId(){
		return id;
	}
	
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
	
	public static FinanceFolder create(FinanceFolder f, User u){
		f.setTotal(0);
		f.setUser(u);
		f.save();
		return f;
	}

	//Might not need. Added @onetomany to User class so can find through user
	//Need to check if it works properly
	public static List<FinanceFolder> findAllForUser(String email){
		User user = User.getUser(email);
		return find.where().eq("user", user).findList();
	}
	
	public static FinanceFolder findByName(String email, String name){
		User user = User.getUser(email);
		return find.where().eq("name", name).eq("user", user).findUnique();
	}
	
	
	//Delete
	public static FinanceFolder delete(int folderId){
		FinanceFolder folder = find.ref(folderId);
		folder.delete();
		return folder;
	}


}