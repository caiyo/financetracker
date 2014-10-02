
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
object _dateSelect extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template0[play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply():play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.1*/("""<div class='form-inline col-md-offset-8 col-sm-4'>
						<select id='tableMonth' class='form-control'>
							<option id="monthall" value="all">All</option>
							<option id="month0" value=0>January</option>
							<option id="month1" value=1>February</option>
							<option id="month2" value=2>March</option>
							<option id="month3" value=3>April</option>
							<option id="month4" value=4>May</option>
							<option id="month5" value=5>June</option>
							<option id="month6" value=6>July</option>
							<option id="month7" value=7>August</option>
							<option id="month8" value=8>September</option>
							<option id="month9" value=9>October</option>
							<option id="month10" value=10>November</option>
							<option id="month11" value=11>December</option>
						</select>
						<select id='tableYear' class='form-control'>
							<option id="yearall" value="all">All</option>
							<option id="year2014" value=2014>2014</option>
							<option id="year2015" value=2015>2015</option>
							<option id="year2016" value=2016>2016</option>
							<option id="year2017" value=2017>2017</option>
						</select>
					</div>"""))}
    }
    
    def render(): play.api.templates.HtmlFormat.Appendable = apply()
    
    def f:(() => play.api.templates.HtmlFormat.Appendable) = () => apply()
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Oct 01 19:51:15 PDT 2014
                    SOURCE: /home/kyle/Langs/Java/financeTracker/app/views/_dateSelect.scala.html
                    HASH: ea1692774cd6efe3c4809a8b337e7c846d78556f
                    MATRIX: 861->0
                    LINES: 29->1
                    -- GENERATED --
                */
            