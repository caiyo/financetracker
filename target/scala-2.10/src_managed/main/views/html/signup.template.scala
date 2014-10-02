
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
object signup extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[Account],play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(form: Form[Account]):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.23*/("""

"""),_display_(Seq[Any](/*3.2*/main("Sign Up")/*3.17*/ {_display_(Seq[Any](format.raw/*3.19*/("""
	<div class='container'>
		<div class='inner-container'>
		<h3 class='text-center'>Sign Up</h3>
			"""),_display_(Seq[Any](/*7.5*/helper/*7.11*/.form(routes.AccountController.createAccount())/*7.58*/{_display_(Seq[Any](format.raw/*7.59*/("""
				"""),_display_(Seq[Any](/*8.6*/if(form.hasErrors)/*8.24*/{_display_(Seq[Any](format.raw/*8.25*/("""
					<ul class='errors'>
					"""),_display_(Seq[Any](/*10.7*/for((key, value) <- form.errors) yield /*10.39*/{_display_(Seq[Any](format.raw/*10.40*/("""
						"""),_display_(Seq[Any](/*11.8*/for(error <- value) yield /*11.27*/{_display_(Seq[Any](format.raw/*11.28*/("""
							<li class="error">
								"""),_display_(Seq[Any](/*13.10*/error/*13.15*/.message)),format.raw/*13.23*/("""
							</li>
						""")))})),format.raw/*15.8*/("""
						
					""")))})),format.raw/*17.7*/("""
					</ul>
				""")))})),format.raw/*19.6*/("""
				<div class='form-group'>
					<label for='email' class=' control-label'>Email</label>
					<input id ='email' class='form-control' type="email" name="email" placeholder="Email" value=""""),_display_(Seq[Any](/*22.100*/form("email")/*22.113*/.value)),format.raw/*22.119*/("""">
				</div>
				<div class='form-group'>
					<label for='name' class=' control-label'>Name</label>
					<input id='name' class='form-control' type='text' name="name" placeholder="Name" value=""""),_display_(Seq[Any](/*26.95*/form("name")/*26.107*/.value)),format.raw/*26.113*/("""">
				</div>
				<div class='form-group'>
					<label for='password' class=' control-label'>Password</label>
					<input id='password' class='form-control' type="password" name="password" placeholder="Password">
				</div>
				<div class='form-group'>
					<label for='confirmPassword' class=' control-label'>Confirm Password</label>
					<input id='confirmPassword' class='form-control' type="password" name="confirmPassword" placeholder="Confirm Password">
				</div>
				<div class='form-group'>
					<input type="submit" value="Sign Up" class="btn btn-primary">
				</div>	
			""")))})),format.raw/*39.5*/("""
		</div>
	</div>
""")))})))}
    }
    
    def render(form:Form[Account]): play.api.templates.HtmlFormat.Appendable = apply(form)
    
    def f:((Form[Account]) => play.api.templates.HtmlFormat.Appendable) = (form) => apply(form)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Fri May 02 22:59:54 PDT 2014
                    SOURCE: /home/kyle/Langs/Java/financeTracker/app/views/signup.scala.html
                    HASH: 9679f938b1a4e8b32b4d39ec8580960ea8affb19
                    MATRIX: 782->1|897->22|934->25|957->40|996->42|1131->143|1145->149|1200->196|1238->197|1278->203|1304->221|1342->222|1409->254|1457->286|1496->287|1539->295|1574->314|1613->315|1685->351|1699->356|1729->364|1781->385|1826->399|1874->416|2101->606|2124->619|2153->625|2385->821|2407->833|2436->839|3050->1422
                    LINES: 26->1|29->1|31->3|31->3|31->3|35->7|35->7|35->7|35->7|36->8|36->8|36->8|38->10|38->10|38->10|39->11|39->11|39->11|41->13|41->13|41->13|43->15|45->17|47->19|50->22|50->22|50->22|54->26|54->26|54->26|67->39
                    -- GENERATED --
                */
            