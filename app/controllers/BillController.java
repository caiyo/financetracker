package controllers;

import play.data.Form;
import play.mvc.Controller;
import models.Bill;
import play.mvc.Result;
import models.Account;
import views.html.*;
import static play.libs.Json.toJson;

public class BillController extends Controller {
	
	public static Result index(){
		return ok(bills.render(Account.getAccount(session("email"))));
	}
	
	public static Result addBill(){
		Form<Bill> f = Form.form(Bill.class).bindFromRequest();
		Bill b = f.get();
		b.setAccount(Account.getAccount(session("email")));
		return ok(toJson(b));
	}
	
	public static Result listBills(){
		Account a = Account.getAccount(session("email"));
		return ok(toJson(a.getBills()));
	}
	
	public static Result deleteBill(int id){
		return ok(toJson(Bill.delete(id)));
	}
}