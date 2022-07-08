package jdbcTests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dynamic_List {

    String dbUrl = "jdbc:oracle:thin:@3.87.215.11:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from employees");

        // in order to column names we need resultSetMetaData
        ResultSetMetaData rsmd = resultSet.getMetaData();

        List<Map<String, Object>> queryData = new ArrayList<>();

        int colCount = rsmd.getColumnCount();

        while (resultSet.next()) {

            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= colCount; i++) {
                row.put(rsmd.getColumnName(i), resultSet.getString(i));
            }
            queryData.add(row);
        }

        for (Map<String, Object> row : queryData) {
            System.out.println(row);

        }

        resultSet.close();
        statement.close();
        connection.close();

    }

}
