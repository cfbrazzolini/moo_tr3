// @SOURCE:C:/Users/Lucas/Desktop/MOO/play-2.2.3/mooDadosAbertos/conf/routes
// @HASH:95ea2d107cd1395a6cfc0eb1b921a6284fe8d448
// @DATE:Tue Jul 01 17:43:45 BRT 2014


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
        

// @LINE:9
private[this] lazy val controllers_Assets_at1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        

// @LINE:11
private[this] lazy val controllers_dxr_index2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("distxrend"))))
        

// @LINE:13
private[this] lazy val controllers_hxr_index3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("horaxrend"))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """distxrend""","""controllers.dxr.index(area:String ?= null, cod:String ?= null, serie_inicial:String ?= "b1", serie_final:String ?= null, param:String ?= null)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """horaxrend""","""controllers.hxr.index(area:String ?= null, cod:String ?= null, serie_inicial:String ?= "b1", serie_final:String ?= "b9", param:String ?= null)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
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
        

// @LINE:9
case controllers_Assets_at1(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        

// @LINE:11
case controllers_dxr_index2(params) => {
   call(params.fromQuery[String]("area", Some(null)), params.fromQuery[String]("cod", Some(null)), params.fromQuery[String]("serie_inicial", Some("b1")), params.fromQuery[String]("serie_final", Some(null)), params.fromQuery[String]("param", Some(null))) { (area, cod, serie_inicial, serie_final, param) =>
        invokeHandler(controllers.dxr.index(area, cod, serie_inicial, serie_final, param), HandlerDef(this, "controllers.dxr", "index", Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String]),"GET", """""", Routes.prefix + """distxrend"""))
   }
}
        

// @LINE:13
case controllers_hxr_index3(params) => {
   call(params.fromQuery[String]("area", Some(null)), params.fromQuery[String]("cod", Some(null)), params.fromQuery[String]("serie_inicial", Some("b1")), params.fromQuery[String]("serie_final", Some("b9")), params.fromQuery[String]("param", Some(null))) { (area, cod, serie_inicial, serie_final, param) =>
        invokeHandler(controllers.hxr.index(area, cod, serie_inicial, serie_final, param), HandlerDef(this, "controllers.hxr", "index", Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String]),"GET", """""", Routes.prefix + """horaxrend"""))
   }
}
        
}

}
     