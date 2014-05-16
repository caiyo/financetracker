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
	@JsonBackReference
	private FinanceFolder financeFolder;
	@ManyToOne
	private String longDescription;
	private String shortDescription;
	 //@Column(columnDefinition = "NUMERIC")
	private float amount;
	@Formats.DateTime(pattern="MM/dd/yyyy")
	private Date creationDate;
	
	
	
	public static Finder<Integer, Transaction> find = new Finder<Integer, Transaction>(Integer.class, Transaction.class);
	
	public String validate(){
		System.out.println("validating transaction");

		String s = "";
		if (shortDescription == null){
			s +="invalid description\n";
		}
		if (amount ==0 || Float.isNaN(amount)){
			s +="invalid amount\n";
		}
		
		if(creationDate == null){
			s+="invalid date\n";
		}
		if (s.isEmpty()){
			System.out.println("no failures");
			return null;
		}
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
	
	public FinanceFolder getFinanceFolder() {
		return financeFolder;
	}


	public void setFinanceFolder(FinanceFolder folder) {
		this.financeFolder = folder;
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


	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
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
		t.setFinanceFolder(f);
		t.financeFolder.setTotal(t.amount);
		t.save();
		f.update();
		return t;
	}
	
	
	//Read & Find
	public static List<Transaction> findTransactionsInFolder(String email, String folderName){
		return find.where().eq("folder", FinanceFolder.findByName(email, folderName)).findList();
	}
	
	//Update
	
	public static Transaction updateTotal(Transaction t, float newAmount){
		float oldAmount = t.getAmount();
		t.setAmount(newAmount);
		t.getFinanceFolder().setTotal(newAmount-oldAmount);
		t.getFinanceFolder().update();
		return t;
	}
	
	public static Transaction update(Transaction updatedTransaction, int id){
		Transaction t = Transaction.find.ref(id);
		if(Math.abs(t.getAmount()-updatedTransaction.getAmount()) < 1.11e-16){
			updateTotal(t, updatedTransaction.getAmount());
		}
		t.setCreationDate(updatedTransaction.getCreationDate());
		t.setShortDescription(updatedTransaction.getShortDescription());
		t.update();
		return t;
	}
	//Delete
	
	public static Transaction delete(int id){
		Transaction t = Transaction.find.ref(id);
		//subtracts total from finance folder before deleting
		t.getFinanceFolder().setTotal(t.getAmount()*-1);
		t.getFinanceFolder().save();
		t.delete();
		return t;
	}
}
