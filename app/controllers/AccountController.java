package controllers;

import java.util.List;

import models.Account;
import play.mvc.Controller;
import play.mvc.Result;
import static play.libs.Json.toJson;


public class AccountController extends Controller{
	public static Result listAccounts(){
		List<Account> account = Account.find.all();
    	return ok(toJson(account));
	}
}
