
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
object login extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[Application.Login],play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(form: Form[Application.Login]):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.33*/("""

"""),_display_(Seq[Any](/*3.2*/main("Login")/*3.15*/ {_display_(Seq[Any](format.raw/*3.17*/("""
	<div class='container'>
		<div class='inner-container'>
			<h3 class='text-center'>Login</h3>
			"""),_display_(Seq[Any](/*7.5*/helper/*7.11*/.form(routes.Application.authenticate())/*7.51*/{_display_(Seq[Any](format.raw/*7.52*/("""
				
				"""),_display_(Seq[Any](/*9.6*/if(form.hasGlobalErrors)/*9.30*/{_display_(Seq[Any](format.raw/*9.31*/("""
				<ul class='errors'>
					<li class="error">
						"""),_display_(Seq[Any](/*12.8*/form/*12.12*/.globalError.message)),format.raw/*12.32*/("""
					</li>
				</ul>
				""")))})),format.raw/*15.6*/("""
				<div class='form-group'>
					<label for='email' class=' control-label'>Email</label>
					<input id ='email' class='form-control' type="email" name="email" placeholder="Email" value=""""),_display_(Seq[Any](/*18.100*/form("email")/*18.113*/.value)),format.raw/*18.119*/("""">
				</div>
				<div class='form-group'>
					<label for='password' class=' control-label'>Password</label>
					<input id='password' class='form-control' type="password" name="password" placeholder="Password">
				</div>
				<div class='form-group'>
					<input type="submit" value="Login" class="btn btn-primary">
				</div>		
			""")))})),format.raw/*27.5*/("""
		</div>
	</div>
""")))})))}
    }
    
    def render(form:Form[Application.Login]): play.api.templates.HtmlFormat.Appendable = apply(form)
    
    def f:((Form[Application.Login]) => play.api.templates.HtmlFormat.Appendable) = (form) => apply(form)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Sat May 17 19:37:44 PDT 2014
                    SOURCE: /home/kyle/Langs/Java/financeTracker/app/views/login.scala.html
                    HASH: f4fcb491f47058404d2e7fd2075e5b92e518c76e
                    MATRIX: 791->1|916->32|953->35|974->48|1013->50|1147->150|1161->156|1209->196|1247->197|1292->208|1324->232|1362->233|1453->289|1466->293|1508->313|1566->340|1793->530|1816->543|1845->549|2211->884
                    LINES: 26->1|29->1|31->3|31->3|31->3|35->7|35->7|35->7|35->7|37->9|37->9|37->9|40->12|40->12|40->12|43->15|46->18|46->18|46->18|55->27
                    -- GENERATED --
                */
            