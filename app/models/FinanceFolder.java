package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;

import com.avaje.ebean.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"name", "account_email"}))
public class FinanceFolder extends Model{

	@Id
	private int id;
	private String name;
	 @Column(columnDefinition = "decimal(12,2)")
	private double total;
	@ManyToOne
	@JsonBackReference
	private Account account;
	
	
	@OneToMany(cascade=CascadeType.REMOVE)
	@JsonManagedReference
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public Transaction addTransaction(Transaction t){
		transactions.add(t);
		return t;
	}
	

/**
 * Static Methods
 * 
 */
	
	public static FinanceFolder create(FinanceFolder f, Account u){
		f.setTotal(0);
		f.setAccount(u);
		f.save();
		return f;
	}

	//Might not need. Added @onetomany to ACcount class so can find through account
	//Need to check if it works properly
	public static List<FinanceFolder> findAllForAccount(String email){
		Account account = Account.getAccount(email);
		return find.where().eq("account", account).findList();
	}
	
	public static FinanceFolder findByName(String email, String name){
		Account account = Account.getAccount(email);
		return find.where().eq("name", name).eq("account", account).findUnique();
	}
	
	
	//Delete
	public static FinanceFolder delete(int folderId){
		FinanceFolder folder = find.ref(folderId);
		folder.delete();
		return folder;
	}


}