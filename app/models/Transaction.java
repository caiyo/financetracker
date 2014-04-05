package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import play.data.format.*;
import play.db.ebean.*;

import com.avaje.ebean.*;

@Entity
public class Transaction extends Model{
	
	@Id
	private int id;
	@ManyToOne
	private FinanceFolder folder;
	@ManyToOne
	private String longDescription;
	private String shortDescirption;
	private double amount;
	@Formats.DateTime(pattern="MM/dd/yy")
	private Date creationDate;
	
	
	
	public static Finder<Integer, Transaction> find = new Finder<Integer, Transaction>(Integer.class, Transaction.class);
	
	
	/**
	 * Getters and Setters
	 */
	
	public FinanceFolder getFolder() {
		return folder;
	}


	public void setFolder(FinanceFolder folder) {
		this.folder = folder;
	}

	public String getLongDescription() {
		return longDescription;
	}


	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}


	public String getShortDescirption() {
		return shortDescirption;
	}


	public void setShortDescirption(String shortDescirption) {
		this.shortDescirption = shortDescirption;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	/*
	 * CRUD operations
	 */
	
	//Create  
	public static Transaction add(Transaction t){
		t.save();
		t.folder.setTotal(t.amount);
		return t;
	}
	
	
	//Read & Find
	public static List<Transaction> findTransactionsInFolder(User user, String folderName){
		return find.where().eq("folder", FinanceFolder.findByName(user, folderName)).findList();
	}
	
	//Update
	
	public static void updateTotal(Transaction t, double newAmount){
		double oldAmount = t.getAmount();
		t.setAmount(newAmount);
		t.getFolder().setTotal(newAmount-oldAmount);
		t.update();
	}
	//Delete
}
