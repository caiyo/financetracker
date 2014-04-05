package controllers;

import java.util.ArrayList;
import java.util.List;

import models.*;
import play.mvc.Controller;
import play.mvc.*;
import static play.libs.Json.toJson;


public class TransactionController extends Controller{
	
	//TODO implement
	public static Result addTransaction(){
		
		return ok();
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
