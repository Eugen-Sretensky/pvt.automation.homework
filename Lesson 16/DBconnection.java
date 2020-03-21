package junitcucumber.environmentsetup;


import java.sql.*;

public class DBconnection {
    private static final String DB_CONNECTION_URL = "jdbc:mysql://127.0.0.1:3306/pvt_automation?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String MAIN_PAGE_URL_QUERY = "SELECT address FROM urls WHERE ID=1";
    private Statement statement;

    public DBconnection() throws SQLException {
        String query = "SELECT address FROM urls WHERE ID=1";
        Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
        statement = connection.createStatement();
    }

    public String getMainPageUrl() throws SQLException {
        return executeQuery(MAIN_PAGE_URL_QUERY);
    }

    private String executeQuery(String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        String result;
        if (resultSet.next()) {
            result = resultSet.getString(1);
        } else {
            result = null;
        }
        return result;
    }
}
