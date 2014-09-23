package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Security;
import models.Bill;
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
		Form<Bill> f = Form.form(Bill.class).bindFromRequest();
		Bill b = f.get();
		return ok(toJson(Bill.update(b,id)));
	}
}
