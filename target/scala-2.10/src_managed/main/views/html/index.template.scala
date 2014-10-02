
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
object index extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template0[play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply():play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](_display_(Seq[Any](/*2.2*/main("Welcome to Finance!")/*2.29*/ {_display_(Seq[Any](format.raw/*2.31*/("""
	<div class='container'>
		<div class='center jumbotron'>
			<h1>Welcome to Finance Tracker!</h1>
			<h3>
			Finance Tracker is a site made for you to track your expenses 
			in a simple manner
			</h3>
			"""),_display_(Seq[Any](/*10.5*/if(session.get("email") == null || Account.getAccount(session.get("email")) == null)/*10.89*/{_display_(Seq[Any](format.raw/*10.90*/("""
				<a href=""""),_display_(Seq[Any](/*11.15*/routes/*11.21*/.Application.login())),format.raw/*11.41*/("""" class='btn btn-default'>Login</a>
		   		<a  href=""""),_display_(Seq[Any](/*12.19*/routes/*12.25*/.AccountController.signUp())),format.raw/*12.52*/("""" class='btn btn-default'>Sign up</a>
		   		
		   	""")))}/*14.9*/else/*14.13*/{_display_(Seq[Any](format.raw/*14.14*/("""
		   		<a href=""""),_display_(Seq[Any](/*15.18*/routes/*15.24*/.FinanceFolderController.index())),format.raw/*15.56*/("""">View your expenses!</a>
		   	""")))})),format.raw/*16.8*/("""
	   	</div>
   	</div>

""")))})),format.raw/*20.2*/("""
"""))}
    }
    
    def render(): play.api.templates.HtmlFormat.Appendable = apply()
    
    def f:(() => play.api.templates.HtmlFormat.Appendable) = () => apply()
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Sat Jun 07 13:50:44 PDT 2014
                    SOURCE: /home/kyle/Langs/Java/financeTracker/app/views/index.scala.html
                    HASH: 3144de6c59475de2ad236e2c935f4948d05ac39f
                    MATRIX: 864->2|899->29|938->31|1181->239|1274->323|1313->324|1364->339|1379->345|1421->365|1511->419|1526->425|1575->452|1646->506|1659->510|1698->511|1752->529|1767->535|1821->567|1885->600|1942->626
                    LINES: 29->2|29->2|29->2|37->10|37->10|37->10|38->11|38->11|38->11|39->12|39->12|39->12|41->14|41->14|41->14|42->15|42->15|42->15|43->16|47->20
                    -- GENERATED --
                */
            