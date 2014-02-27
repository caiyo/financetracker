package models;

import java.util.Date;

import javax.persistence.*;

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
}
