package models;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
	private Boolean paid;
	
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
		if(errors.isEmpty())
			return null;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public boolean getIsRecuring() {
		return isRecuring;
	}
	public void setIsRecuring(boolean isRecuring) {
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
	public Boolean getPaid(){
		return paid;
	}
	public void setPaid(Boolean paid){
		this.paid = paid;
	}
	
	/*
	 * STATIC METHODS
	 */
	public static Bill copy(Bill b){
		Bill bill = new Bill();
		bill.setAccount(b.getAccount());
		bill.setAmount(b.getAmount());
		bill.setDueDate(b.getDueDate());
		bill.setIsRecuring(b.getIsRecuring());
		bill.setPaid(b.getPaid());
		bill.setTitle(b.getTitle());
		return bill;
	}
	public static Bill create(Bill b, Account a){
		b.setAccount(a);
		b.setPaid(false);
		b.save();
		return b;
	}
	
	//If bill is recuring, it will create a bill for the same day for every month in the next year
	public static List<Bill> createRecuringBills(Bill b, Account a, int length){
		List<Bill> bills = new ArrayList<>();
		Bill newBill;
		long date = b.getDueDate().getTime();
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(date);
		int billDate = cal.get(Calendar.DATE);
		int billYear = cal.get(Calendar.YEAR);
		int billMonth = cal.get(Calendar.MONTH);
		String [] months = new DateFormatSymbols().getMonths();
		
		for(int i=0;i<length; i++){
			newBill = Bill.copy(b);
			//if bill month hits january of a new year, increase the billyear
			if(i>0 && billMonth==0)
				billYear++;
			Date newDate = new Date(new GregorianCalendar(billYear,billMonth,billDate).getTimeInMillis());
			newBill.setDueDate(newDate);
			bills.add(Bill.create(newBill, a));
			billMonth= ++billMonth%12;
		}
		System.out.println(bills.size());
		return bills;
	}
	
	public static Bill delete(int id){
		Bill b = find.ref(id);
		b.delete();
		return b;
	}
	public static Bill update(Bill updatedBill, int id){
		Bill b = find.ref(id);
		b.setAmount(updatedBill.getAmount());
		b.setDueDate(updatedBill.getDueDate());
		b.setTitle(updatedBill.getTitle());
		b.setPaid(updatedBill.getPaid());
		b.update();
		return b;
	}
	
	public static Bill pay(Bill bill, FinanceFolder financeFolder){
		Transaction t = new Transaction();
		t.setAmount(bill.getAmount());
		t.setCreationDate(bill.getDueDate());
		t.setShortDescription(bill.getTitle());
		Transaction.create(t, financeFolder);
		return bill;
	}
	
}
