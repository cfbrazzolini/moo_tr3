// @SOURCE:/home/jorge/Documents/MOO_TR3/play-2.2.3/moo_tr3/conf/routes
// @HASH:4c3b0c42171ebd75856be51bccab53d67965330b
// @DATE:Sun Jun 29 16:23:46 BRT 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:13
// @LINE:11
// @LINE:9
// @LINE:6
package controllers {

// @LINE:13
class Reversehxr {
    

// @LINE:13
def index(area:String = null, cod:String = null, serie_inicial:String = "b1", serie_final:String = "b9", param:String = null): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "horaxrend" + queryString(List(if(area == null) None else Some(implicitly[QueryStringBindable[String]].unbind("area", area)), if(cod == null) None else Some(implicitly[QueryStringBindable[String]].unbind("cod", cod)), if(serie_inicial == "b1") None else Some(implicitly[QueryStringBindable[String]].unbind("serie_inicial", serie_inicial)), if(serie_final == "b9") None else Some(implicitly[QueryStringBindable[String]].unbind("serie_final", serie_final)), if(param == null) None else Some(implicitly[QueryStringBindable[String]].unbind("param", param)))))
}
                                                
    
}
                          

// @LINE:9
class ReverseAssets {
    

// @LINE:9
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:11
class Reversedxr {
    

// @LINE:11
def index(area:String = null, cod:String = null, serie_inicial:String = "b1", serie_final:String = null, param:String = null): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "distxrend" + queryString(List(if(area == null) None else Some(implicitly[QueryStringBindable[String]].unbind("area", area)), if(cod == null) None else Some(implicitly[QueryStringBindable[String]].unbind("cod", cod)), if(serie_inicial == "b1") None else Some(implicitly[QueryStringBindable[String]].unbind("serie_inicial", serie_inicial)), if(serie_final == null) None else Some(implicitly[QueryStringBindable[String]].unbind("serie_final", serie_final)), if(param == null) None else Some(implicitly[QueryStringBindable[String]].unbind("param", param)))))
}
                                                
    
}
                          

// @LINE:6
class ReverseApplication {
    

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                
    
}
                          
}
                  


// @LINE:13
// @LINE:11
// @LINE:9
// @LINE:6
package controllers.javascript {

// @LINE:13
class Reversehxr {
    

// @LINE:13
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.hxr.index",
   """
      function(area,cod,serie_inicial,serie_final,param) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "horaxrend" + _qS([(area == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("area", area)), (cod == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("cod", cod)), (serie_inicial == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("serie_inicial", serie_inicial)), (serie_final == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("serie_final", serie_final)), (param == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("param", param))])})
      }
   """
)
                        
    
}
              

// @LINE:9
class ReverseAssets {
    

// @LINE:9
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:11
class Reversedxr {
    

// @LINE:11
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.dxr.index",
   """
      function(area,cod,serie_inicial,serie_final,param) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "distxrend" + _qS([(area == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("area", area)), (cod == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("cod", cod)), (serie_inicial == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("serie_inicial", serie_inicial)), (serie_final == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("serie_final", serie_final)), (param == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("param", param))])})
      }
   """
)
                        
    
}
              

// @LINE:6
class ReverseApplication {
    

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:13
// @LINE:11
// @LINE:9
// @LINE:6
package controllers.ref {


// @LINE:13
class Reversehxr {
    

// @LINE:13
def index(area:String, cod:String, serie_inicial:String, serie_final:String, param:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.hxr.index(area, cod, serie_inicial, serie_final, param), HandlerDef(this, "controllers.hxr", "index", Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String]), "GET", """""", _prefix + """horaxrend""")
)
                      
    
}
                          

// @LINE:9
class ReverseAssets {
    

// @LINE:9
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:11
class Reversedxr {
    

// @LINE:11
def index(area:String, cod:String, serie_inicial:String, serie_final:String, param:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.dxr.index(area, cod, serie_inicial, serie_final, param), HandlerDef(this, "controllers.dxr", "index", Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String]), "GET", """""", _prefix + """distxrend""")
)
                      
    
}
                          

// @LINE:6
class ReverseApplication {
    

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      
    
}
                          
}
        
    