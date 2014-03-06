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
	public int id;
	@ManyToOne
	public FinanceFolder folder;
	@ManyToOne
	public TransactionCategory category;
	public String longDescription;
	public String shortDescirption;
	public double amount;
	@Formats.DateTime(pattern="MM/dd/yy")
	public Date date;
	
	public static Finder<Integer, Transaction> find = new Finder<Integer, Transaction>(Integer.class, Transaction.class);
	
	/*
	 * CRUD operations
	 */
	
	//Create  
	public static Transaction add(Transaction t){
		t.save();
		t.folder.updateTotal(t.amount);
		return t;
	}
	
	
	//Read & Find
	public static List<Transaction> findTransactionsInFolder(User user, String folderName){
		return find.where().eq("folder", FinanceFolder.findByName(user, folderName)).findList();
	}
	
	//Update
	
	public static Transaction updateTotal(int transID, double newAmount){
		Transaction t  = find.ref(transID);
		t.amount = newAmount;
		
		FinanceFolder.updateTotal(folderId, newAmount);
		t.update();
		return t;
	}
	//Delete
}
