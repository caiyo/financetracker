package controllers;


import play.mvc.*;
import play.mvc.Http.*;


public class Secured extends Security.Authenticator{
	@Override
	public String getUsername(Context c){
		return c.session().get("email");
	}
	
	@Override
	public Result onUnauthorized(Context c){
		return redirect(routes.Application.login());
	}
}
