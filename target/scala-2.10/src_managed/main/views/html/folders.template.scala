
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._
/**/
object folders extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Account,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(user: Account):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.17*/("""

"""),_display_(Seq[Any](/*3.2*/main("Finances")/*3.18*/ {_display_(Seq[Any](format.raw/*3.20*/("""

	<div class='wrapper'>
		<div class='sidebar-wrapper'>
			<div class="sidebar-nav">
				<h3>Expense Folders</h3> 
				<ul id="folder-list" class="nav nav-stacked">
				</ul>
			</div>
			<div class='sidebar-buttons'>
				<div class='btn-group btn-group-sm'>
					<button id='addFolder' type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-plus'></span></button>
					<button id='deleteFolder' type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-minus'></span></button>
					<button id='updateFolder' type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'></span></button>
				</div>
			</div>
		</div>
		 
		<div class='content-wrapper'>
			<div class='container-fluid'>
				<div><h2>Expenses</h2></div>
				<div class='row'>
					"""),_display_(Seq[Any](/*25.7*/_dateSelect())),format.raw/*25.20*/("""
				</div>
				<div class='row'>
					<div class='col-sm-12'>
						<div id='expense-descript'>
							<h4>
								Click on a folder and begin adding expenses! If you haven't created any <br>
								folders yet, do so by click on the '+' button on the bottom left.
							</h4>
						</div>
						"""),_display_(Seq[Any](/*35.8*/_table("transaction", "none"))),format.raw/*35.37*/(""";
					</div>
				</div>
				 
			</div>
		</div>

	</div>
		



<!-- <script type='text/javascript' src=""""),_display_(Seq[Any](/*47.43*/routes/*47.49*/.Assets.at("javascripts/sorttable.js"))),format.raw/*47.87*/(""""></script>  -->
<script type='text/javascript' src=""""),_display_(Seq[Any](/*48.38*/routes/*48.44*/.Assets.at("javascripts/jquery.tablesorter.js"))),format.raw/*48.91*/(""""></script> 
<script type="text/javascript" src=""""),_display_(Seq[Any](/*49.38*/routes/*49.44*/.Assets.at("javascripts/finance.js"))),format.raw/*49.80*/(""""></script>

""")))})))}
    }
    
    def render(user:Account): play.api.templates.HtmlFormat.Appendable = apply(user)
    
    def f:((Account) => play.api.templates.HtmlFormat.Appendable) = (user) => apply(user)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Oct 01 19:51:15 PDT 2014
                    SOURCE: /home/kyle/Langs/Java/financeTracker/app/views/folders.scala.html
                    HASH: a892d1c99ddce0b0a57de92fa488130d8b9f42d1
                    MATRIX: 777->1|886->16|923->19|947->35|986->37|1837->853|1872->866|2208->1167|2259->1196|2402->1303|2417->1309|2477->1347|2567->1401|2582->1407|2651->1454|2737->1504|2752->1510|2810->1546
                    LINES: 26->1|29->1|31->3|31->3|31->3|53->25|53->25|63->35|63->35|75->47|75->47|75->47|76->48|76->48|76->48|77->49|77->49|77->49
                    -- GENERATED --
                */
            