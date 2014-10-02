
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
object bills extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Account,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(user: Account):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.17*/("""

"""),_display_(Seq[Any](/*3.2*/main("Bills")/*3.15*/ {_display_(Seq[Any](format.raw/*3.17*/("""
<div class='wrapper'>
		<div class='sidebar-wrapper'>
			<div class="sidebar-nav">
				<h3>Bill Information</h3> 
				<ul id="bill-info" class="nav nav-stacked">
				</ul>
			</div>
		</div>
		<div class='content-wrapper'>
			<div class='container-fluid'>
				<div><h2>Bills</h2></div>
				<div class='row'>
					"""),_display_(Seq[Any](/*16.7*/_dateSelect())),format.raw/*16.20*/("""
				</div>
				<div class='row' >
					<div class='col-sm-12'>
						"""),_display_(Seq[Any](/*20.8*/_table("bills"))),format.raw/*20.23*/("""
					</div>
				</div>
				 
			</div>
		</div>
</div>
	
<script type='text/javascript' src=""""),_display_(Seq[Any](/*28.38*/routes/*28.44*/.Assets.at("javascripts/jquery.tablesorter.js"))),format.raw/*28.91*/(""""></script> 
<script type="text/javascript" src=""""),_display_(Seq[Any](/*29.38*/routes/*29.44*/.Assets.at("javascripts/bills.js"))),format.raw/*29.78*/(""""></script>
	
""")))})),format.raw/*31.2*/("""
"""))}
    }
    
    def render(user:Account): play.api.templates.HtmlFormat.Appendable = apply(user)
    
    def f:((Account) => play.api.templates.HtmlFormat.Appendable) = (user) => apply(user)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Oct 01 19:51:16 PDT 2014
                    SOURCE: /home/kyle/Langs/Java/financeTracker/app/views/bills.scala.html
                    HASH: 67c2e450ba793858f0b5b225982488105bd93712
                    MATRIX: 775->1|884->16|921->19|942->32|981->34|1331->349|1366->362|1472->433|1509->448|1640->543|1655->549|1724->596|1810->646|1825->652|1881->686|1927->701
                    LINES: 26->1|29->1|31->3|31->3|31->3|44->16|44->16|48->20|48->20|56->28|56->28|56->28|57->29|57->29|57->29|59->31
                    -- GENERATED --
                */
            