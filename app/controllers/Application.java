package controllers;

import models.User;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	
	public static class Login{
    	String email;
    	String password;
    	
    	public String validate(){
    		if(User.authenticate(email, password)==null)
    			return ("Invalid email or password");
    		return null;
    	}
    }

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public static Result login(){
    	return ok(login.render(Form.form(Login.class)));
    }
    
    public static Result authenticate(){
    	Form<Login> f = Form.form(Login.class).bindFromRequest();
    	if(f.hasErrors()){
    		return badRequest(login.render(f));
    	}
    	else{
    		session().clear();
    		session("email", f.get().email);
    	
    		return redirect(routes.Application.index());
    	}
    }

}
