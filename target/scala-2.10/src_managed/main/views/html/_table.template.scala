
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
object _table extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[String,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(id: String):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.14*/("""
		<div id='"""),_display_(Seq[Any](/*2.13*/id)),format.raw/*2.15*/("""-list'>
							<table class='table' id='"""),_display_(Seq[Any](/*3.34*/id)),format.raw/*3.36*/("""-table'>
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
    
    def render(id:String): play.api.templates.HtmlFormat.Appendable = apply(id)
    
    def f:((String) => play.api.templates.HtmlFormat.Appendable) = (id) => apply(id)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Oct 20 16:35:46 PDT 2014
                    SOURCE: /home/kyle/Langs/Java/financeTracker/app/views/_table.scala.html
                    HASH: f1018d4abf014afbd0fd8ee186501c242a0630cf
                    MATRIX: 775->1|881->13|929->26|952->28|1028->69|1051->71|1824->808|1850->825|1889->826|1962->867
                    LINES: 26->1|29->1|30->2|30->2|31->3|31->3|52->24|52->24|52->24|54->26
                    -- GENERATED --
                */
            