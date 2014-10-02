// @SOURCE:/home/kyle/Langs/Java/financeTracker/conf/routes
// @HASH:5ee827719c07cc3e03f7d20911dff16fd4e19b52
// @DATE:Sat Sep 20 12:22:21 PDT 2014


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:7
private[this] lazy val controllers_Application_login1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
        

// @LINE:8
private[this] lazy val controllers_Application_logout2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("logout"))))
        

// @LINE:11
private[this] lazy val controllers_FinanceFolderController_index3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("expenses"))))
        

// @LINE:16
private[this] lazy val controllers_AccountController_signUp4 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("signup"))))
        

// @LINE:17
private[this] lazy val controllers_AccountController_createAccount5 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("signup"))))
        

// @LINE:20
private[this] lazy val controllers_BillController_index6 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("bills"))))
        

// @LINE:23
private[this] lazy val controllers_Application_javascriptRoutes7 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("javascriptRoutes"))))
        

// @LINE:26
private[this] lazy val controllers_Assets_at8 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        

// @LINE:29
private[this] lazy val controllers_FinanceFolderController_addFolder9 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/folders"))))
        

// @LINE:30
private[this] lazy val controllers_FinanceFolderController_listFolders10 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/folders"))))
        

// @LINE:31
private[this] lazy val controllers_FinanceFolderController_deleteFolder11 = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/folders/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:33
private[this] lazy val controllers_TransactionController_addTransaction12 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/transaction/"),DynamicPart("folder", """[^/]+""",true))))
        

// @LINE:34
private[this] lazy val controllers_TransactionController_deleteTransaction13 = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/transaction/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:35
private[this] lazy val controllers_TransactionController_updateTransaction14 = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/transaction/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:37
private[this] lazy val controllers_BillController_listBills15 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/bills"))))
        

// @LINE:38
private[this] lazy val controllers_BillController_addBill16 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/bills"))))
        

// @LINE:39
private[this] lazy val controllers_BillController_deleteBill17 = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/bills/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:40
private[this] lazy val controllers_BillController_updateBill18 = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/bills/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:42
private[this] lazy val controllers_AccountController_listAccounts19 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/listAccounts"))))
        

// @LINE:43
private[this] lazy val controllers_Application_authenticate20 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/login"))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Application.login()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """logout""","""controllers.Application.logout()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """expenses""","""controllers.FinanceFolderController.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """signup""","""controllers.AccountController.signUp()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """signup""","""controllers.AccountController.createAccount()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """bills""","""controllers.BillController.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """javascriptRoutes""","""controllers.Application.javascriptRoutes"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/folders""","""controllers.FinanceFolderController.addFolder()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/folders""","""controllers.FinanceFolderController.listFolders()"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/folders/$id<[^/]+>""","""controllers.FinanceFolderController.deleteFolder(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/transaction/$folder<[^/]+>""","""controllers.TransactionController.addTransaction(folder:String)"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/transaction/$id<[^/]+>""","""controllers.TransactionController.deleteTransaction(id:Integer)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/transaction/$id<[^/]+>""","""controllers.TransactionController.updateTransaction(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/bills""","""controllers.BillController.listBills()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/bills""","""controllers.BillController.addBill()"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/bills/$id<[^/]+>""","""controllers.BillController.deleteBill(id:Integer)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/bills/$id<[^/]+>""","""controllers.BillController.updateBill(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/listAccounts""","""controllers.AccountController.listAccounts()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/login""","""controllers.Application.authenticate()""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:7
case controllers_Application_login1(params) => {
   call { 
        invokeHandler(controllers.Application.login(), HandlerDef(this, "controllers.Application", "login", Nil,"GET", """""", Routes.prefix + """login"""))
   }
}
        

// @LINE:8
case controllers_Application_logout2(params) => {
   call { 
        invokeHandler(controllers.Application.logout(), HandlerDef(this, "controllers.Application", "logout", Nil,"GET", """""", Routes.prefix + """logout"""))
   }
}
        

// @LINE:11
case controllers_FinanceFolderController_index3(params) => {
   call { 
        invokeHandler(controllers.FinanceFolderController.index(), HandlerDef(this, "controllers.FinanceFolderController", "index", Nil,"GET", """FinanceFolder""", Routes.prefix + """expenses"""))
   }
}
        

// @LINE:16
case controllers_AccountController_signUp4(params) => {
   call { 
        invokeHandler(controllers.AccountController.signUp(), HandlerDef(this, "controllers.AccountController", "signUp", Nil,"GET", """Accounts""", Routes.prefix + """signup"""))
   }
}
        

// @LINE:17
case controllers_AccountController_createAccount5(params) => {
   call { 
        invokeHandler(controllers.AccountController.createAccount(), HandlerDef(this, "controllers.AccountController", "createAccount", Nil,"POST", """""", Routes.prefix + """signup"""))
   }
}
        

// @LINE:20
case controllers_BillController_index6(params) => {
   call { 
        invokeHandler(controllers.BillController.index(), HandlerDef(this, "controllers.BillController", "index", Nil,"GET", """Bills""", Routes.prefix + """bills"""))
   }
}
        

// @LINE:23
case controllers_Application_javascriptRoutes7(params) => {
   call { 
        invokeHandler(controllers.Application.javascriptRoutes, HandlerDef(this, "controllers.Application", "javascriptRoutes", Nil,"GET", """Javascript router""", Routes.prefix + """javascriptRoutes"""))
   }
}
        

// @LINE:26
case controllers_Assets_at8(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        

// @LINE:29
case controllers_FinanceFolderController_addFolder9(params) => {
   call { 
        invokeHandler(controllers.FinanceFolderController.addFolder(), HandlerDef(this, "controllers.FinanceFolderController", "addFolder", Nil,"POST", """API""", Routes.prefix + """api/folders"""))
   }
}
        

// @LINE:30
case controllers_FinanceFolderController_listFolders10(params) => {
   call { 
        invokeHandler(controllers.FinanceFolderController.listFolders(), HandlerDef(this, "controllers.FinanceFolderController", "listFolders", Nil,"GET", """""", Routes.prefix + """api/folders"""))
   }
}
        

// @LINE:31
case controllers_FinanceFolderController_deleteFolder11(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        invokeHandler(controllers.FinanceFolderController.deleteFolder(id), HandlerDef(this, "controllers.FinanceFolderController", "deleteFolder", Seq(classOf[Integer]),"DELETE", """""", Routes.prefix + """api/folders/$id<[^/]+>"""))
   }
}
        

// @LINE:33
case controllers_TransactionController_addTransaction12(params) => {
   call(params.fromPath[String]("folder", None)) { (folder) =>
        invokeHandler(controllers.TransactionController.addTransaction(folder), HandlerDef(this, "controllers.TransactionController", "addTransaction", Seq(classOf[String]),"POST", """""", Routes.prefix + """api/transaction/$folder<[^/]+>"""))
   }
}
        

// @LINE:34
case controllers_TransactionController_deleteTransaction13(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        invokeHandler(controllers.TransactionController.deleteTransaction(id), HandlerDef(this, "controllers.TransactionController", "deleteTransaction", Seq(classOf[Integer]),"DELETE", """""", Routes.prefix + """api/transaction/$id<[^/]+>"""))
   }
}
        

// @LINE:35
case controllers_TransactionController_updateTransaction14(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        invokeHandler(controllers.TransactionController.updateTransaction(id), HandlerDef(this, "controllers.TransactionController", "updateTransaction", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """api/transaction/$id<[^/]+>"""))
   }
}
        

// @LINE:37
case controllers_BillController_listBills15(params) => {
   call { 
        invokeHandler(controllers.BillController.listBills(), HandlerDef(this, "controllers.BillController", "listBills", Nil,"GET", """""", Routes.prefix + """api/bills"""))
   }
}
        

// @LINE:38
case controllers_BillController_addBill16(params) => {
   call { 
        invokeHandler(controllers.BillController.addBill(), HandlerDef(this, "controllers.BillController", "addBill", Nil,"POST", """""", Routes.prefix + """api/bills"""))
   }
}
        

// @LINE:39
case controllers_BillController_deleteBill17(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        invokeHandler(controllers.BillController.deleteBill(id), HandlerDef(this, "controllers.BillController", "deleteBill", Seq(classOf[Integer]),"DELETE", """""", Routes.prefix + """api/bills/$id<[^/]+>"""))
   }
}
        

// @LINE:40
case controllers_BillController_updateBill18(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        invokeHandler(controllers.BillController.updateBill(id), HandlerDef(this, "controllers.BillController", "updateBill", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """api/bills/$id<[^/]+>"""))
   }
}
        

// @LINE:42
case controllers_AccountController_listAccounts19(params) => {
   call { 
        invokeHandler(controllers.AccountController.listAccounts(), HandlerDef(this, "controllers.AccountController", "listAccounts", Nil,"GET", """""", Routes.prefix + """api/listAccounts"""))
   }
}
        

// @LINE:43
case controllers_Application_authenticate20(params) => {
   call { 
        invokeHandler(controllers.Application.authenticate(), HandlerDef(this, "controllers.Application", "authenticate", Nil,"POST", """""", Routes.prefix + """api/login"""))
   }
}
        
}

}
     