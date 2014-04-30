package controllers;

import models.FinanceFolder;
import models.Account;
import play.mvc.Controller;
import play.data.Form;
import play.mvc.*;
import views.html.*;
import static play.libs.Json.toJson;

@Security.Authenticated(Secured.class)
public class FinanceFolderController extends Controller{
	
	//
	public static Result index(){
		return ok(folders.render(Account.getAccount(session().get("email"))));
	}
	
	public static Result addFolder(){
		Form<FinanceFolder> f = Form.form(FinanceFolder.class).bindFromRequest();
		Account u = Account.getAccount(session().get("email"));
		return ok(toJson(FinanceFolder.create(f.get(), u)));
	}
	
	public static Result listFolders(){
		String accountEmail = session("email");
		Account user = Account.getAccount(accountEmail);
		return ok(toJson(user.getFolders()));
	}

}
