package controllers;

import models.FinanceFolder;
import models.User;
import play.mvc.Controller;
import play.data.Form;
import play.mvc.*;

public class FinanceFolderController extends Controller{
	
	//TODO fix later once user login is implemented
	public static Result addFolder(String name, User user){
		Form<FinanceFolder> f = Form.form(FinanceFolder.class).bindFromRequest();
		FinanceFolder ff = f.get();
		ff.save();
		return redirect(routes.Application.index());
		
	}

}
