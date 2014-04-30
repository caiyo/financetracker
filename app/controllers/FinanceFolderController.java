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
		return ok(folders.render(Account.getUser(session().get("email"))));
	}
	
	public static Result addFolder(){
		Form<FinanceFolder> f = Form.form(FinanceFolder.class).bindFromRequest();
		Account u = Account.getUser(session().get("email"));
		return ok(toJson(FinanceFolder.create(f.get(), u)));
	}
	
	public static Result listFolders(){
		String userEmail = session("email");
		Account user = Account.getUser(userEmail);
		return ok(toJson(user.getFolders()));
	}

}
