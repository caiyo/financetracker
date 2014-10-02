// @SOURCE:/home/kyle/Langs/Java/financeTracker/conf/routes
// @HASH:5ee827719c07cc3e03f7d20911dff16fd4e19b52
// @DATE:Sat Sep 20 12:22:21 PDT 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:43
// @LINE:42
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:26
// @LINE:23
// @LINE:20
// @LINE:17
// @LINE:16
// @LINE:11
// @LINE:8
// @LINE:7
// @LINE:6
package controllers {

// @LINE:26
class ReverseAssets {
    

// @LINE:26
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:20
class ReverseBillController {
    

// @LINE:38
def addBill(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "api/bills")
}
                                                

// @LINE:39
def deleteBill(id:Integer): Call = {
   Call("DELETE", _prefix + { _defaultPrefix } + "api/bills/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                                                

// @LINE:20
def index(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "bills")
}
                                                

// @LINE:37
def listBills(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "api/bills")
}
                                                

// @LINE:40
def updateBill(id:Integer): Call = {
   Call("PUT", _prefix + { _defaultPrefix } + "api/bills/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                                                
    
}
                          

// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:11
class ReverseFinanceFolderController {
    

// @LINE:31
def deleteFolder(id:Integer): Call = {
   Call("DELETE", _prefix + { _defaultPrefix } + "api/folders/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                                                

// @LINE:29
def addFolder(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "api/folders")
}
                                                

// @LINE:11
def index(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "expenses")
}
                                                

// @LINE:30
def listFolders(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "api/folders")
}
                                                
    
}
                          

// @LINE:43
// @LINE:23
// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:8
def logout(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "logout")
}
                                                

// @LINE:43
def authenticate(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "api/login")
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                

// @LINE:23
def javascriptRoutes(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "javascriptRoutes")
}
                                                

// @LINE:7
def login(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "login")
}
                                                
    
}
                          

// @LINE:42
// @LINE:17
// @LINE:16
class ReverseAccountController {
    

// @LINE:17
def createAccount(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "signup")
}
                                                

// @LINE:16
def signUp(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "signup")
}
                                                

// @LINE:42
def listAccounts(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "api/listAccounts")
}
                                                
    
}
                          

// @LINE:35
// @LINE:34
// @LINE:33
class ReverseTransactionController {
    

// @LINE:35
def updateTransaction(id:Integer): Call = {
   Call("PUT", _prefix + { _defaultPrefix } + "api/transaction/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                                                

// @LINE:34
def deleteTransaction(id:Integer): Call = {
   Call("DELETE", _prefix + { _defaultPrefix } + "api/transaction/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                                                

// @LINE:33
def addTransaction(folder:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "api/transaction/" + implicitly[PathBindable[String]].unbind("folder", dynamicString(folder)))
}
                                                
    
}
                          
}
                  


// @LINE:43
// @LINE:42
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:26
// @LINE:23
// @LINE:20
// @LINE:17
// @LINE:16
// @LINE:11
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.javascript {

// @LINE:26
class ReverseAssets {
    

// @LINE:26
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:20
class ReverseBillController {
    

// @LINE:38
def addBill : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.BillController.addBill",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/bills"})
      }
   """
)
                        

// @LINE:39
def deleteBill : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.BillController.deleteBill",
   """
      function(id) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "api/bills/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:20
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.BillController.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "bills"})
      }
   """
)
                        

// @LINE:37
def listBills : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.BillController.listBills",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/bills"})
      }
   """
)
                        

// @LINE:40
def updateBill : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.BillController.updateBill",
   """
      function(id) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "api/bills/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        
    
}
              

// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:11
class ReverseFinanceFolderController {
    

// @LINE:31
def deleteFolder : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.FinanceFolderController.deleteFolder",
   """
      function(id) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "api/folders/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:29
def addFolder : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.FinanceFolderController.addFolder",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/folders"})
      }
   """
)
                        

// @LINE:11
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.FinanceFolderController.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "expenses"})
      }
   """
)
                        

// @LINE:30
def listFolders : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.FinanceFolderController.listFolders",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/folders"})
      }
   """
)
                        
    
}
              

// @LINE:43
// @LINE:23
// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:8
def logout : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.logout",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "logout"})
      }
   """
)
                        

// @LINE:43
def authenticate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.authenticate",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/login"})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:23
def javascriptRoutes : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.javascriptRoutes",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "javascriptRoutes"})
      }
   """
)
                        

// @LINE:7
def login : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.login",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
   """
)
                        
    
}
              

// @LINE:42
// @LINE:17
// @LINE:16
class ReverseAccountController {
    

// @LINE:17
def createAccount : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.AccountController.createAccount",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "signup"})
      }
   """
)
                        

// @LINE:16
def signUp : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.AccountController.signUp",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "signup"})
      }
   """
)
                        

// @LINE:42
def listAccounts : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.AccountController.listAccounts",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/listAccounts"})
      }
   """
)
                        
    
}
              

// @LINE:35
// @LINE:34
// @LINE:33
class ReverseTransactionController {
    

// @LINE:35
def updateTransaction : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.TransactionController.updateTransaction",
   """
      function(id) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "api/transaction/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:34
def deleteTransaction : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.TransactionController.deleteTransaction",
   """
      function(id) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "api/transaction/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:33
def addTransaction : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.TransactionController.addTransaction",
   """
      function(folder) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/transaction/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("folder", encodeURIComponent(folder))})
      }
   """
)
                        
    
}
              
}
        


// @LINE:43
// @LINE:42
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:26
// @LINE:23
// @LINE:20
// @LINE:17
// @LINE:16
// @LINE:11
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.ref {


// @LINE:26
class ReverseAssets {
    

// @LINE:26
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:20
class ReverseBillController {
    

// @LINE:38
def addBill(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.BillController.addBill(), HandlerDef(this, "controllers.BillController", "addBill", Seq(), "POST", """""", _prefix + """api/bills""")
)
                      

// @LINE:39
def deleteBill(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.BillController.deleteBill(id), HandlerDef(this, "controllers.BillController", "deleteBill", Seq(classOf[Integer]), "DELETE", """""", _prefix + """api/bills/$id<[^/]+>""")
)
                      

// @LINE:20
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.BillController.index(), HandlerDef(this, "controllers.BillController", "index", Seq(), "GET", """Bills""", _prefix + """bills""")
)
                      

// @LINE:37
def listBills(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.BillController.listBills(), HandlerDef(this, "controllers.BillController", "listBills", Seq(), "GET", """""", _prefix + """api/bills""")
)
                      

// @LINE:40
def updateBill(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.BillController.updateBill(id), HandlerDef(this, "controllers.BillController", "updateBill", Seq(classOf[Integer]), "PUT", """""", _prefix + """api/bills/$id<[^/]+>""")
)
                      
    
}
                          

// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:11
class ReverseFinanceFolderController {
    

// @LINE:31
def deleteFolder(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.FinanceFolderController.deleteFolder(id), HandlerDef(this, "controllers.FinanceFolderController", "deleteFolder", Seq(classOf[Integer]), "DELETE", """""", _prefix + """api/folders/$id<[^/]+>""")
)
                      

// @LINE:29
def addFolder(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.FinanceFolderController.addFolder(), HandlerDef(this, "controllers.FinanceFolderController", "addFolder", Seq(), "POST", """API""", _prefix + """api/folders""")
)
                      

// @LINE:11
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.FinanceFolderController.index(), HandlerDef(this, "controllers.FinanceFolderController", "index", Seq(), "GET", """FinanceFolder""", _prefix + """expenses""")
)
                      

// @LINE:30
def listFolders(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.FinanceFolderController.listFolders(), HandlerDef(this, "controllers.FinanceFolderController", "listFolders", Seq(), "GET", """""", _prefix + """api/folders""")
)
                      
    
}
                          

// @LINE:43
// @LINE:23
// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:8
def logout(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.logout(), HandlerDef(this, "controllers.Application", "logout", Seq(), "GET", """""", _prefix + """logout""")
)
                      

// @LINE:43
def authenticate(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.authenticate(), HandlerDef(this, "controllers.Application", "authenticate", Seq(), "POST", """""", _prefix + """api/login""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

// @LINE:23
def javascriptRoutes(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.javascriptRoutes(), HandlerDef(this, "controllers.Application", "javascriptRoutes", Seq(), "GET", """Javascript router""", _prefix + """javascriptRoutes""")
)
                      

// @LINE:7
def login(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.login(), HandlerDef(this, "controllers.Application", "login", Seq(), "GET", """""", _prefix + """login""")
)
                      
    
}
                          

// @LINE:42
// @LINE:17
// @LINE:16
class ReverseAccountController {
    

// @LINE:17
def createAccount(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.AccountController.createAccount(), HandlerDef(this, "controllers.AccountController", "createAccount", Seq(), "POST", """""", _prefix + """signup""")
)
                      

// @LINE:16
def signUp(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.AccountController.signUp(), HandlerDef(this, "controllers.AccountController", "signUp", Seq(), "GET", """Accounts""", _prefix + """signup""")
)
                      

// @LINE:42
def listAccounts(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.AccountController.listAccounts(), HandlerDef(this, "controllers.AccountController", "listAccounts", Seq(), "GET", """""", _prefix + """api/listAccounts""")
)
                      
    
}
                          

// @LINE:35
// @LINE:34
// @LINE:33
class ReverseTransactionController {
    

// @LINE:35
def updateTransaction(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.TransactionController.updateTransaction(id), HandlerDef(this, "controllers.TransactionController", "updateTransaction", Seq(classOf[Integer]), "PUT", """""", _prefix + """api/transaction/$id<[^/]+>""")
)
                      

// @LINE:34
def deleteTransaction(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.TransactionController.deleteTransaction(id), HandlerDef(this, "controllers.TransactionController", "deleteTransaction", Seq(classOf[Integer]), "DELETE", """""", _prefix + """api/transaction/$id<[^/]+>""")
)
                      

// @LINE:33
def addTransaction(folder:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.TransactionController.addTransaction(folder), HandlerDef(this, "controllers.TransactionController", "addTransaction", Seq(classOf[String]), "POST", """""", _prefix + """api/transaction/$folder<[^/]+>""")
)
                      
    
}
                          
}
        
    