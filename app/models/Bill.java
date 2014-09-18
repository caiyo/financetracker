package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import play.data.format.Formats;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
import play.data.validation.ValidationError;


@Entity
public class Bill extends Model{
	
	@Id
	private int id;
	private String title;
	@Formats.DateTime(pattern="MM/dd/yyyy")
	private Date dueDate;
	private Boolean isRecuring;
	@Column(columnDefinition = "decimal(12,2)")
	private double amount;
	@ManyToOne
	@JsonBackReference
	private Account account;
	
	public static Finder<Integer, Bill> find = new Finder<>(Integer.class, Bill.class);
	
	public List<ValidationError> validate(){
		List<ValidationError> errors = new ArrayList<ValidationError>();
		if(title==null || title.isEmpty()){
			errors.add(new ValidationError("title Empty", "Bill must have a title"));
		}
		if(dueDate==null){
			errors.add(new ValidationError("Date null", "Must enter a due date"));
		}
		if(isRecuring == null){
			errors.add(new ValidationError("recurring null", "Must specify if bill is recurring"));
		}
		if(amount<=0){
			errors.add(new ValidationError("Incorrect Amoun", "Amount must be greater than $0"));
		}
		return errors;
	}
	
	/*
	 * Getters and Setters
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String gettitle() {
		return title;
	}
	public void settitle(String title) {
		this.title = title;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public boolean isRecuring() {
		return isRecuring;
	}
	public void setRecuring(boolean isRecuring) {
		this.isRecuring = isRecuring;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	/*
	 * STATIC METHODS
	 */
	
	public static Bill create(Bill b, Account a){
		b.setAccount(a);
		b.save();
		return b;
	}
	
}
