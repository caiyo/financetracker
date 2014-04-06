package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import play.data.format.*;
import play.db.ebean.*;

import com.avaje.ebean.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Transaction extends Model{
	
	@Id
	private int id;
	@ManyToOne
	@JsonManagedReference
	private FinanceFolder folder;
	@ManyToOne
	private String longDescription;
	private String shortDescription;
	private double amount;
	@Formats.DateTime(pattern="MM/dd/yy")
	private Date creationDate;
	
	
	
	public static Finder<Integer, Transaction> find = new Finder<Integer, Transaction>(Integer.class, Transaction.class);
	
	
	/**
	 * Getters and Setters
	 */
	
	public int getId(){
		return id;
	}
	
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


	public String getShortDescription() {
		return shortDescription;
	}


	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
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
	public static Transaction create(Transaction t, FinanceFolder f){
		t.setFolder(f);
		t.folder.setTotal(t.amount);
		t.save();
		return t;
	}
	
	
	//Read & Find
	public static List<Transaction> findTransactionsInFolder(String email, String folderName){
		return find.where().eq("folder", FinanceFolder.findByName(email, folderName)).findList();
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
