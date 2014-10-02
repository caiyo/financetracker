
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
object _table extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,String,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(id: String, display: String = ""):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.36*/("""
		<div id='"""),_display_(Seq[Any](/*2.13*/id)),format.raw/*2.15*/("""-list'>
							<table class='table tablesorter' id='"""),_display_(Seq[Any](/*3.46*/id)),format.raw/*3.48*/("""-table' style='display:"""),_display_(Seq[Any](/*3.72*/display)),format.raw/*3.79*/("""'>
								<thead>
									<tr>
										<th> 
											<div class='btn-group'>
												<button type='button' class='btn btn-default' id='add'>
													Add 
												</button>
												 <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
												    <span class="caret"></span>
												    <span class="sr-only">Toggle Dropdown</span>
												</button>
												<ul class='dropdown-menu' role='menu'>
													<li><a href='#' id='updateButton'>Update</a><li>
													<li><a href='#' id='delete'>Delete</a><li>
												</ul>
											</div>
										</th>
										<th>Date</th>
										<th>Description</th>
										<th>Amount</th>
										"""),_display_(Seq[Any](/*24.12*/if(id == "bills")/*24.29*/{_display_(Seq[Any](format.raw/*24.30*/("""
										<th>Recurring</th>
										""")))})),format.raw/*26.12*/("""
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div> """))}
    }
    
    def render(id:String,display:String): play.api.templates.HtmlFormat.Appendable = apply(id,display)
    
    def f:((String,String) => play.api.templates.HtmlFormat.Appendable) = (id,display) => apply(id,display)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Sat Sep 20 14:05:59 PDT 2014
                    SOURCE: /home/kyle/Langs/Java/financeTracker/app/views/_table.scala.html
                    HASH: d724c53b77688a8a69c678b0740d65d99c2bde7b
                    MATRIX: 782->1|910->35|958->48|981->50|1069->103|1092->105|1151->129|1179->136|1946->867|1972->884|2011->885|2084->926
                    LINES: 26->1|29->1|30->2|30->2|31->3|31->3|31->3|31->3|52->24|52->24|52->24|54->26
                    -- GENERATED --
                */
            