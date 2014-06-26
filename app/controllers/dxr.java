package controllers;

import play.mvc.Controller;
import play.mvc.Result;

import com.mongodb.DB;
import com.mongodb.DBCollection;

public class dxr extends Controller {

  public static Result index(String area, String code, String serie_inicial, String serie_final,
      String params) {
    DB db = MongoConnect.connect();
    if (db == null) {
      return TODO;
    } else {
      DBCollection coll = db.getCollection("merge");
      // DBCursor cursor = coll.find();
      // while (cursor.hasNext()) {
      // System.out.println(cursor.next());
      // }
      System.out.println(coll.findOne());
    }
    return TODO;
  }
}
