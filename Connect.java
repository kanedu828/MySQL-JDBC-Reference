/*----------------------------------------------------------------------
Connect.java

Purpose: Reference guide to connecting to a database, extracting data, and inserting data through MySQL

Reference: Genuine Coder's youtube series "JDBC Tutorial for Beginners"
    Playlist Link: https://www.youtube.com/watch?v=BOUMR85B-V0&list=PLhs1urmduZ2-yp3zID5rMEmXDETN8xvMo

Last Edit: 1/10/2019

Author: Kane Du
 ------------------------------------------------------------------------*/

import java.sql.*;


public class Connect {

    public static void main(String args[]){
        Connect connect = new Connect();
        //System.out.println("Check");
        connect.createConnection();
    }

    void createConnection()             {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //This statement may be different depending on driver version
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdatabase", "kanedu", "kanedu");

            //EXTRACTING DATA FROM A DATABASE:----------------------------------------
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from people;");

            //INSERTING DATA TO DATABASE:---------------------------------------------
            Statement insert = con.createStatement();
            insert.execute("insert into people\n" +
                    "(First, Last, Age)\n" +
                    "values ('Kane', 'Du', 18);");
            insert.close();
            //ALTERNATIVE WAY TO INSERT DATA TO DATABASE (DO THIS WAY):--------------
            PreparedStatement pstmt = con.prepareStatement("insert into people values(?, ?, ?)");
            pstmt.setString(1, "Han");
            pstmt.setString(2, "Solo");
            pstmt.setInt(3, 52);
            pstmt.execute();
            System.out.println("DATA INSERTED!");
            pstmt.close();

            //DISPLAYING DATA:---------------------------------------------------------
            while(rs.next()){ //rs.next() checks if there is another row of data after current row. Remains true if there is.
                //If you decide to use column index, 1st column is 1, NOT 0. Generally it is better to use the column name.
                String firstName = rs.getString("first");
                String lastName = rs.getString("last");
                int age = rs.getInt("age");
                System.out.println(firstName + " " + lastName + " " + age);
            }


            System.out.println("Connection success");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}