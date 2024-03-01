package Classes;

import java.util.ArrayList;

public class DBManager {

  public  static  ArrayList<Footballer> db = new ArrayList<>();
  public static  long id = 1l;

  public  static  void addFootballer(Footballer footballer) {
      db.add(footballer);
      id++;
  }

  public  static ArrayList  getAllFootballers(){
    return  db;
  }


}
