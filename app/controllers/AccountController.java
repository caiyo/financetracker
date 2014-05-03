package controllers;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import models.Account;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import static play.libs.Json.toJson;
import views.html.*;
import play.data.validation.ValidationError;



public class AccountController extends Controller{
	
	public static Result signUp(){
		return ok(signup.render(Form.form(Account.class)));
	}
	public static Result listAccounts(){
		List<Account> account = Account.find.all();
    	return ok(toJson(account));
	}
	
	public static Result createAccount(){
		//add  password confirm field to account with @Transient so its not persisted to DB
		Form<Account> f = Form.form(Account.class).bindFromRequest();
		//add validation to acccount class
		if(f.hasErrors()){
				System.out.println("errors? " + f.hasErrors());
				System.out.println(f.errors().size());
				
				for(Entry<String, List<ValidationError>> e :  f.errors().entrySet()){
					System.out.println(e.getKey() + " " +e.getValue());
				}
				return badRequest(signup.render(f));
		}	
		else{
			//add create method to account class that takes an account parameter
			//in create method, salt and hash password using messagedigest and sha256
			Account.createAccount(f.get());
			return redirect(routes.Application.login());
		}
	}
}
