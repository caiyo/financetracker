package controllers;

import java.util.ArrayList;
import java.util.List;

import models.*;
import play.data.Form;
import play.mvc.*;
import static play.libs.Json.toJson;


public class TransactionController extends Controller{
	
	//TODO 
	public static Result addTransaction(String folderName){
		Form<Transaction> form = Form.form(Transaction.class).bindFromRequest();
		FinanceFolder folder = FinanceFolder.findByName(session().get("email"), folderName);
		Transaction.create(form.get(), folder);
		return redirect(routes.FinanceFolderController.index());
	}
	
	
	public static Result listTransactions(){
		User user = User.getUser(session("email"));
		List <Transaction> transactions = new ArrayList<>();
		
		for(FinanceFolder f : user.getFolders()){
			transactions.addAll(f.getTransactions());
		}
		
		return ok(toJson(transactions));
	}
}
