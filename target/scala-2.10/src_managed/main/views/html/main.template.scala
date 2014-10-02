
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
object main extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Html,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(title: String)(content: Html):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.32*/("""

<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(Seq[Any](/*7.17*/title)),format.raw/*7.22*/("""</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*8.54*/routes/*8.60*/.Assets.at("stylesheets/main.css"))),format.raw/*8.94*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(Seq[Any](/*9.59*/routes/*9.65*/.Assets.at("images/favicon.png"))),format.raw/*9.97*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*10.54*/routes/*10.60*/.Assets.at("stylesheets/bootstrap-theme.min.css"))),format.raw/*10.109*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*11.54*/routes/*11.60*/.Assets.at("stylesheets/bootstrap.min.css"))),format.raw/*11.103*/("""">
        <link rel="stylesheet" href=""""),_display_(Seq[Any](/*12.39*/routes/*12.45*/.Assets.at("stylesheets/custom.css"))),format.raw/*12.81*/("""">
		<link rel='stylesheet' href=""""),_display_(Seq[Any](/*13.33*/routes/*13.39*/.Assets.at("stylesheets/datepicker3.css"))),format.raw/*13.80*/("""">
		
        <script src=""""),_display_(Seq[Any](/*15.23*/routes/*15.29*/.Assets.at("javascripts/jquery-1.9.0.min.js"))),format.raw/*15.74*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*16.23*/routes/*16.29*/.Assets.at("javascripts/jquery.cookie.js"))),format.raw/*16.71*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*17.23*/routes/*17.29*/.Assets.at("javascripts/bootstrap.min.js"))),format.raw/*17.71*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*18.23*/routes/*18.29*/.Assets.at("javascripts/bootstrap-datepicker.js"))),format.raw/*18.78*/("""" type="text/javascript"></script>
        <script type="text/javascript" src='"""),_display_(Seq[Any](/*19.46*/routes/*19.52*/.Application.javascriptRoutes())),format.raw/*19.83*/("""'></script>
    </head>
    <body>
		<div class='navbar navbar-default navbar-inverse navbar-static-top'>
			<div class='container'>
				<div class='navbar-header'>
					<button type='button' class='navbar-toggle collapsed' data-toggle='collapse' data-target='.navbar-collapse'>
						<span class='sr-only'>Toggle Navigation</span>
						<span class='icon-bar'></span>
						<span class='icon-bar'></span>
						<span class='icon-bar'></span>
					</button>
					<a class='navbar-brand' href='#'>Finance Tracker</a>
				</div>
				<div class='navbar-collapse collapse'>
					<ul class='nav navbar-nav'>
						<li>
							<a href=""""),_display_(Seq[Any](/*36.18*/routes/*36.24*/.Application.index())),format.raw/*36.44*/("""">Home</a>
						</li>
						<li>
							<a href=""""),_display_(Seq[Any](/*39.18*/routes/*39.24*/.FinanceFolderController.index())),format.raw/*39.56*/("""">Expenses</a>
						</li>
						<li>
							<a href=""""),_display_(Seq[Any](/*42.18*/routes/*42.24*/.BillController.index())),format.raw/*42.47*/("""">Bills</a>
						</li>
					</ul>
					
					<ul class='nav navbar-nav navbar-right'>
					"""),_display_(Seq[Any](/*47.7*/if(session.get("email") == null || Account.getAccount(session.get("email")) == null)/*47.91*/{_display_(Seq[Any](format.raw/*47.92*/("""
						"""),_display_(Seq[Any](/*48.8*/{session.clear()})),format.raw/*48.25*/("""
						<li>
							<a href=""""),_display_(Seq[Any](/*50.18*/routes/*50.24*/.AccountController.signUp())),format.raw/*50.51*/("""">
								Sign Up
							</a>
						</li>
						<li>
							<a href=""""),_display_(Seq[Any](/*55.18*/routes/*55.24*/.Application.login())),format.raw/*55.44*/("""">
								Login
							</a>
						</li>
		   			""")))}/*59.11*/else/*59.15*/{_display_(Seq[Any](format.raw/*59.16*/("""
		   				<li>
		   					<a href='#'>
		   						"""),_display_(Seq[Any](/*62.13*/{Account.getAccount(session.get("email")).getName()})),format.raw/*62.65*/("""
		   					</a>
		   				</li>
		   				<li>
		   					<a href=""""),_display_(Seq[Any](/*66.21*/routes/*66.27*/.Application.logout())),format.raw/*66.48*/("""">Logout</a>
		   				</li>
		   				
		   			""")))})),format.raw/*69.10*/("""
					</ul>
				</div>			
			</div>
		</div>
	    
	    	"""),_display_(Seq[Any](/*75.8*/content)),format.raw/*75.15*/("""
	    
    </body>
</html>




"""))}
    }
    
    def render(title:String,content:Html): play.api.templates.HtmlFormat.Appendable = apply(title)(content)
    
    def f:((String) => (Html) => play.api.templates.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Sep 24 19:56:11 PDT 2014
                    SOURCE: /home/kyle/Langs/Java/financeTracker/app/views/main.scala.html
                    HASH: aa8d4910067d7885c33a35e55ddc42f10293a00d
                    MATRIX: 778->1|902->31|990->84|1016->89|1113->151|1127->157|1182->191|1278->252|1292->258|1345->290|1437->346|1452->352|1524->401|1616->457|1631->463|1697->506|1774->547|1789->553|1847->589|1918->624|1933->630|1996->671|2060->699|2075->705|2142->750|2235->807|2250->813|2314->855|2407->912|2422->918|2486->960|2579->1017|2594->1023|2665->1072|2781->1152|2796->1158|2849->1189|3516->1820|3531->1826|3573->1846|3660->1897|3675->1903|3729->1935|3820->1990|3835->1996|3880->2019|4008->2112|4101->2196|4140->2197|4183->2205|4222->2222|4287->2251|4302->2257|4351->2284|4458->2355|4473->2361|4515->2381|4584->2432|4597->2436|4636->2437|4722->2487|4796->2539|4897->2604|4912->2610|4955->2631|5034->2678|5127->2736|5156->2743
                    LINES: 26->1|29->1|35->7|35->7|36->8|36->8|36->8|37->9|37->9|37->9|38->10|38->10|38->10|39->11|39->11|39->11|40->12|40->12|40->12|41->13|41->13|41->13|43->15|43->15|43->15|44->16|44->16|44->16|45->17|45->17|45->17|46->18|46->18|46->18|47->19|47->19|47->19|64->36|64->36|64->36|67->39|67->39|67->39|70->42|70->42|70->42|75->47|75->47|75->47|76->48|76->48|78->50|78->50|78->50|83->55|83->55|83->55|87->59|87->59|87->59|90->62|90->62|94->66|94->66|94->66|97->69|103->75|103->75
                    -- GENERATED --
                */
            