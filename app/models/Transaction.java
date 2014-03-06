package models;

import java.util.Date;

import javax.persistence.*;

import org.jboss.logging.FormatWith;
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
	
	/*
	 * CRUD operations
	 */
	
	//Create  
	
	//Read & Find
	
	//Update
	
	//Delete
}
