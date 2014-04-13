package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.*;
import play.data.Form;
import play.mvc.*;
import static play.libs.Json.toJson;


public class TransactionController extends Controller{
	
	//TODO 
	public static Result addTransaction(String folderName){
		System.out.println(folderName);
		Form<Transaction> form = Form.form(Transaction.class).bindFromRequest();
		
		//remove later
		System.out.println("[DEBUG] :" + form);
		System.out.println("~~~~~~~~~~~~~~~~~~");
		FinanceFolder folder = FinanceFolder.findByName(session().get("email"), folderName);
		Transaction t = Transaction.create(form.get(), folder);
		System.out.println("[debug] transaction:\n amount:" + t.getAmount() + "\n description: "+ t.getShortDescription()
				+ "\ndate: " + t.getCreationDate().toString() + "\nfolder: " + t.getFinanceFolder().getName()
				+ "\nfolder amount: " + folder.getTotal());
		return ok(toJson(t));
	}
	
	
	public static Result listTransactions(String folderName){
		User user = User.getUser(session("email"));
		Map<String, List<Transaction>> transactions = User.getAllTransactions(user);
		return ok(toJson(transactions.get(folderName)));
	}
}
