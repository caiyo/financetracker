package controllers;

import java.util.List;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import static play.libs.Json.toJson;


public class UserController extends Controller{
	public static Result listUsers(){
		List<User> user = User.find.all();
    	return ok(toJson(user));
	}
}
