package controllers;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoConnect {
  public static DB connect() {
    try {
      MongoClient mongo = new MongoClient("localhost", 27017);
      return mongo.getDB("dadosinep");
    } catch (UnknownHostException e) {
      return null;
    }
  }
}
