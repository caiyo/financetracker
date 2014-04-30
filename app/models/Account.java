package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import play.db.ebean.Model;

@Entity
public class Account extends Model{
	
	@Id
	private String email;
	private String name;
	private String password;
	@OneToMany
	@JsonManagedReference
	private List<FinanceFolder> folders = new ArrayList<>();

	


	public static Finder<String, Account> find  = new Finder<>(String.class, Account.class);


/**
 * Getters and Setters
 * 
 */

	public String getName() {
		return name;
	}


	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public List<FinanceFolder> getFolders() {
		return folders;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFolders(List<FinanceFolder> folders) {
		this.folders = folders;
	}
	
/**
 * STATIC METHODS
 */
	
	public static Account authenticate(String email, String password){
		System.out.println(email + " " + password);
		return find.where().eq("email", email).eq("password", password).findUnique();
	}

	public static Account getUser(String email){
		return find.ref(email);
	}
	
	public static HashMap<String, List<Transaction>> getAllTransactions(Account user){
		HashMap<String, List<Transaction>> transactions = new HashMap<>();
		for(FinanceFolder f: user.getFolders()){
			transactions.put(f.getName(), f.getTransactions());
		}
		return transactions;
		
	}
}
