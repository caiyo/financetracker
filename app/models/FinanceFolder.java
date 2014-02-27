package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

@Entity
public class FinanceFolder extends Model{

	@Id
	public int id;
	public String name;
	public int total;
	@ManyToOne
	public User user;
	public static Finder<Integer, FinanceFolder> find = new Finder(Integer.class, FinanceFolder.class);

	public FinanceFolder (String name, User user){
		this.name=name;
		this.user=user;
		this.total=0;
	}

	//public 



}