package jdbcTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.record.RecordMetaData;

import java.sql.*;

public class Jdbc_examples {

    String dbUrl="jdbc:oracle:thin:@3.87.215.11:1521:xe";
    String dbUsername="hr";
    String dbPassword="hr";





    @Test
    public void test1() throws SQLException {

        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from departments");

        //System.out.println("Department_id  "+"Department_Name    "+"Manager_id");
        while (resultSet.next()){
            System.out.println(resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3));
        }

        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void test2() throws SQLException {
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet=statement.executeQuery("select * from departments");

        //how many find rows we have for the query
        int rowNumber=0;
        while (resultSet.next()){
            rowNumber++;
        }
        System.out.println("row number: "+rowNumber);

        //-------------------------------------

        resultSet.last();
        System.out.println("row number: "+resultSet.getRow());


        // print all second column info ( pointer is in the last row now, we go to first row again )
        resultSet.beforeFirst();
        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void test3() throws SQLException {
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet=statement.executeQuery("select * from employees");

        //get the database related data inside the dbMetaData object
        DatabaseMetaData dbMetadata=connection.getMetaData();
        System.out.println("dbMetadata.getUserName() = " + dbMetadata.getUserName());
        System.out.println("dbMetadata.getDatabaseProductName() = " + dbMetadata.getDatabaseProductName());
        System.out.println("dbMetadata.getDriverName() = " + dbMetadata.getDriverName());
        System.out.println("dbMetadata.getDatabaseProductVersion() = " + dbMetadata.getDatabaseProductVersion());

        System.out.println("------------------------------------------------");


        // get the resultsetmetadata ( rsmd )
        ResultSetMetaData rsMetaData=resultSet.getMetaData();

        // how many columns we have?
        System.out.println("rsMetaData.getColumnCount() = " + rsMetaData.getColumnCount());

        rsMetaData.getColumnName(1);

        // get all column names dynamically

        for (int i = 1; i <=rsMetaData.getColumnCount() ; i++) {
            System.out.println("rsMetaData.getColumnName("+i+") = " + rsMetaData.getColumnName(i));
        }


        resultSet.close();
        statement.close();
        connection.close();
    }




}
