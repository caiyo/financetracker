package controllers;

import java.util.Date;

import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Security;
import models.Bill;
import models.FinanceFolder;
import models.Transaction;
import play.mvc.Result;
import models.Account;
import views.html.*;
import static play.libs.Json.toJson;

@Security.Authenticated(Secured.class)
public class BillController extends Controller {
	
	public static Result index(){
		return ok(bills.render(Account.getAccount(session("email"))));
	}
	
	public static Result addBill(){
		Form<Bill> f = Form.form(Bill.class).bindFromRequest();
		System.out.println("[DEBUG] :" + f);

		Bill b = f.get();
		Account a = Account.getAccount(session("email"));
		if(b.getIsRecuring()){
			return ok(toJson(Bill.createRecuringBills(b,a,12)));
		}
		else{
			return ok(toJson(Bill.create(b, a)));
		}
	}
	
	public static Result listBills(){
		Account a = Account.getAccount(session("email"));
		return ok(toJson(a.getBills()));
	}
	
	public static Result deleteBill(int id){
		Bill.delete(id);
		return ok();
	}
	
	public static Result updateBill(int id){
		DynamicForm f = Form.form().bindFromRequest();
		Bill b = new Bill();
		b.setAmount(Float.parseFloat(f.get("amount")));
		b.setDueDate(new Date(f.get("dueDate")));
		b.setPaid(Boolean.valueOf(f.get("paid")));
		b.setTitle(f.get("title"));
		
		//if bill is being paid, create a transaction for it 
		if (b.getPaid()){
			Bill.pay(b, FinanceFolder.findByName(session("email"), f.get("financeFolder")));
		}
		//update bill
		Bill.update(b, id);
		return ok();
		
	}
	
	public static Result payBill(int id){
		DynamicForm f = Form.form().bindFromRequest();/*
		Bill b = Bill.find.ref(Integer.parseInt(f.get("id")));
		FinanceFolder folder = FinanceFolder.findByName(session("email"), f.get("financeFolder"));
		Bill.pay(b, folder);*/
		System.out.println(f.toString());
		return ok();//toJson(b));
	}
}
