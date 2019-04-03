
import java.sql.*;
class opgaver {

    public static void main(String[] args) {
    String url = "jdbc:mysql://localhost:3306/test";
    String user = "eget username";
    String password = "eget password";

    try{
      Class.forName("com.mysql.jdbc.Driver");

      Connection con = DriverManager.getConnection(url, user, password);

    }catch(Exception e){
      System.out.println(e);
    }

    findClosestPrice(con, 3);
    minRequirements(con, 1, 2, 3, 4);

    con.close();
  }

  static void findClosestPrice(Connection con, int price){
    String sql = "SELECT maker, pc.model, speed\n" +
                 "FROM product, pc\n" +
                 "WHERE product.model = pc.model\n" +
                 "ORDER BY ABS(price - " + price + ")\n" +
                 "LIMIT 1";

    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery(sql);

    while(rs.next()){
      System.out.println("Maker: " + rs.getString("maker"));
      System.out.println("Model number: " + rs.getInt(2));
      System.out.println("Speed: " + rs.getInt(3));
    }
  }

  static void minRequirements(Connection con, int speed, int ram, int hd, int screenSize){
    String sql = "SELECT *\n" +
                 "FROM product NATURAL JOIN laptop\n" +
                 "WHERE speed >=" + speed + " AND ram >=" + ram + " AND hd >=" + hd + " AND screen >=" + screenSize;

    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery(sql);

    while(rs.next()){
      System.out.println("Model number: " + rs.getInt("model"));
      System.out.println("Speed: " + rs.getInt("speed"));
      System.out.println("Ram: " + rs.getInt("ram"));
      System.out.println("Hard Disk: " + rs.getInt("hd"));
      System.out.println("price: " + rs.getInt("price"));
      System.out.println("Screen Size: " + rs.getInt("screen"));
      System.out.println("Maker: " + rs.getInt("maker"));
      System.out.println("***************************");

    }
  }
}
