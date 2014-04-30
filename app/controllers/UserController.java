package controllers;

import java.util.List;

import models.Account;
import play.mvc.Controller;
import play.mvc.Result;
import static play.libs.Json.toJson;


public class UserController extends Controller{
	public static Result listUsers(){
		List<Account> user = Account.find.all();
    	return ok(toJson(user));
	}
}
