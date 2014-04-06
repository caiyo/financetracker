package controllers;

import models.FinanceFolder;
import models.User;
import play.mvc.Controller;
import play.data.Form;
import play.mvc.*;
import views.html.*;
import static play.libs.Json.toJson;

@Security.Authenticated(Secured.class)
public class FinanceFolderController extends Controller{
	
	//
	public static Result index(){
		return ok(folders.render(User.getUser(session().get("email"))));
	}
	
	public static Result addFolder(){
		Form<FinanceFolder> f = Form.form(FinanceFolder.class).bindFromRequest();
		User u = User.getUser(session().get("email"));
		FinanceFolder.create(f.get(), u);
		return redirect(routes.FinanceFolderController.index());
	}
	
	public static Result listFolders(){
		String userEmail = session("email");
		User user = User.getUser(userEmail);
		return ok(toJson(user.getFolders()));
	}

}
