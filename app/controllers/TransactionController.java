package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.*;
import play.data.Form;
import play.mvc.*;
import static play.libs.Json.toJson;

@Security.Authenticated(Secured.class)
public class TransactionController extends Controller{
	
	//TODO 
	public static Result addTransaction(){
		Form<Transaction> form = Form.form(Transaction.class).bindFromRequest();
		
		//remove later
		System.out.println("[DEBUG] :" + form);
		System.out.println("~~~~~~~~~~~~~~~~~~");
		FinanceFolder folder = FinanceFolder.findByName(session().get("email"), form.field("folder").value());
		Transaction t = Transaction.create(form.get(), folder);
		System.out.println("[debug] transaction:\n amount:" + t.getAmount() + "\n description: "+ t.getShortDescription()
				+ "\ndate: " + t.getCreationDate().toString() + "\nfolder: " + t.getFinanceFolder().getName()
				+ "\nfolder amount: " + folder.getTotal());
		return ok(toJson(t));
	}
	
	
	public static Result listTransactions(String folderName){
		Account account = Account.getAccount(session("email"));
		Map<String, List<Transaction>> transactions = Account.getAllTransactions(account);
		return ok(toJson(transactions.get(folderName)));
	}
	
	public static Result deleteTransaction(int id){
		return ok(toJson(Transaction.delete(id)));
	}
	
	public static Result updateTransaction(int id){
		Form<Transaction> form = Form.form(Transaction.class).bindFromRequest();
		Transaction t = form.get();
		return ok(toJson(Transaction.update(t, id)));
	}
}
