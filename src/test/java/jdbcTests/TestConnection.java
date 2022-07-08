package jdbcTests;

import java.sql.*;
import java.util.Arrays;

public class TestConnection {
    public static void main(String[] args) throws SQLException {
        String dbUrl="jdbc:oracle:thin:@3.87.215.11:1521:xe";
        String dbUsername="hr";
        String dbPassword="hr";

        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        Statement statement=connection.createStatement();

        ResultSet resultSet=statement.executeQuery("select * from regions");


        // next() --> move pointer to first row
        resultSet.next();


        // getting information with column name
        System.out.println(resultSet.getString("region_name"));

        //getting info with column index(start 1)
        System.out.println(resultSet.getString(2));

        //1-Europe 2-Americans
        System.out.println(resultSet.getString(1)+" - "+resultSet.getString(2));
        resultSet.next();
        System.out.println(resultSet.getString(1)+" - "+resultSet.getString(2));
        resultSet.next();
        System.out.println(resultSet.getString(1)+" - "+resultSet.getString(2));


        System.out.println("_______________________________________________________");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " - " + resultSet.getString(2));

        }


        // close connection
        resultSet.close();
        statement.close();
        connection.close();

    }
}
