package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

@Entity
public class User extends Model{
	
	@Id
	private String email;
	private String name;
	private String password;
	@OneToMany
	private List<FinanceFolder> folders = new ArrayList<>();

	


	public static Finder<String, User> find  = new Finder<>(String.class, User.class);


	public User(String email, String name, String password){
		this.email=email;
		this.name=name;
		this.password=password;
	}

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
	
	public static User authenticate(String email, String password){
		return find.where().eq("email", email).eq("password", password).findUnique();
	}

	public static User getUser(String email){
		return find.ref(email);
	}
	
	public static HashMap<FinanceFolder, List<Transaction>> getAllTransactions(User user){
		HashMap<FinanceFolder, List<Transaction>> transactions = new HashMap<>();
		for(FinanceFolder f: user.getFolders()){
			transactions.put(f, f.getTransactions());
		}
		return transactions;
		
	}
}
