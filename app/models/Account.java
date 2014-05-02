package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import play.data.validation.ValidationError;
import play.db.ebean.Model;

@Entity
public class Account extends Model{
	
	@Id
	private String email;
	private String name;
	private String password;
	@Transient
	private String confirmPassword;
	@OneToMany
	@JsonManagedReference
	private List<FinanceFolder> folders = new ArrayList<>();

	
	

	public static Finder<String, Account> find  = new Finder<>(String.class, Account.class);

	public List<ValidationError> validate(){
		System.out.println("test");
		 List<ValidationError> errors = new ArrayList<ValidationError>();
		if(Account.getAccount(email) !=null){
			errors.add(new ValidationError("emailTaken", "This email address is already taken"));
		}
		
		else if (email == null){
			errors.add(new ValidationError("emailNull", "Please enter an email address"));
		}
		else if (name == null){
			errors.add(new ValidationError("name", "Please enter your name"));
		}
		else if (!password.equals(confirmPassword)){
			errors.add(new ValidationError("password", "Password and confirm password must match"));
			
		}
		System.out.println(errors.isEmpty());
		return errors.isEmpty() ? null : errors;
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
	
	public String getConfirmPassword(){
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword){
		this.confirmPassword = confirmPassword;
	}

	public void setFolders(List<FinanceFolder> folders) {
		this.folders = folders;
	}
	
/**
 * STATIC METHODS
 */
	public static Account createAccount(Account account){
		account.save();
		return account;
	}
	
	public static Account authenticate(String email, String password){
		return find.where().eq("email", email).eq("password", password).findUnique();
	}

	public static Account getAccount(String email){
		return find.byId(email);
	}
	
	public static HashMap<String, List<Transaction>> getAllTransactions(Account user){
		HashMap<String, List<Transaction>> transactions = new HashMap<>();
		for(FinanceFolder f: user.getFolders()){
			transactions.put(f.getName(), f.getTransactions());
		}
		return transactions;
		
	}
	
}
