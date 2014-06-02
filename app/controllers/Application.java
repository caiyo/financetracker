package controllers;

import models.Account;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;
import play.mvc.Controller;


public class Application extends Controller {
	
	public static class Login{
    	private String email;
		private String password;
    	
    	public Login(){
    		System.out.println("test");
    	}
    	
    	public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
    	public String validate(){
    		if(Account.authenticate(email, password)==null)
    			return ("Invalid email or password");
    		return null;
    	}
    }

    public static Result index() {
        return ok(index.render());
    }
    
    public static Result login(){
    	return ok(login.render(Form.form(Login.class)));
    }
    
    public static Result logout(){
    	session().clear();
    	flash("success", "You've been logged out");
    	return redirect(routes.Application.index());
    }
    
    public static Result authenticate(){
    	Form<Login> f = Form.form(Login.class).bindFromRequest();
    	if(f.hasErrors()){
    		return badRequest(login.render(f));
    	}
    	else{
    		session().clear();
    		session("email", f.get().email.toLowerCase());
    	
    		return redirect(routes.Application.index());
    	}
    }
    
    public static Result javascriptRoutes(){
    	response().setContentType("text/javascript");
        return ok(
        		Routes.javascriptRouter("jsRoutes",
        				controllers.routes.javascript.FinanceFolderController.listFolders(),
        				controllers.routes.javascript.FinanceFolderController.addFolder(),
        				controllers.routes.javascript.FinanceFolderController.deleteFolder(),
        				controllers.routes.javascript.TransactionController.addTransaction(),
        				controllers.routes.javascript.AccountController.listAccounts(),
        				controllers.routes.javascript.TransactionController.deleteTransaction(),
        				controllers.routes.javascript.TransactionController.updateTransaction()
        				)
        		);
    }

}
