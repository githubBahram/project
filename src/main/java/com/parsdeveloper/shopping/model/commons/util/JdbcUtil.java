package com.parsdeveloper.shopping.model.commons.util;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JdbcUtil {

    public static final String USERNAME = "m_emami";
    public static final String PASSWORD = "m_emami";
    public static final String DATABASE_CONNECTION_STRING = "192.168.25.184:1521/emami.istd.com";

    public static Connection connection = null;
    public static ResultSet resultSet = null;

    public static Connection getDataBaseConnection() {

        if (connection != null)
            return connection;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@//" + DATABASE_CONNECTION_STRING, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return connection;
    }

    public static ResultSet executePrepareStatement(String query, List<Object> parameters) throws Exception {
        Connection con = getDataBaseConnection();
        PreparedStatement p = con.prepareStatement(query);

        if (parameters != null && parameters.size() > 0) {

            for (int i = 0; i < parameters.size(); i++) {
                if (parameters.get(i) instanceof String)
                    p.setString(i + 1, (String) parameters.get(i));
                else if (parameters.get(i) instanceof Long)
                    p.setLong(i + 1, (Long) parameters.get(i));
                else if (parameters.get(i) instanceof BigDecimal)
                    p.setBigDecimal(i + 1, (BigDecimal) parameters.get(i));
                else if (parameters.get(i) instanceof Integer)
                    p.setInt(i + 1, (Integer) parameters.get(i));
                else if (parameters.get(i) instanceof Date)
                    p.setDate(i + 1, (Date) parameters.get(i));
                else
                    throw new Exception();
            }
        }

        resultSet = p.executeQuery();

        return resultSet;
    }

    public static List<Object> executeQuery(String query, int outputCount) throws Exception {
        List<Object> parameters = new ArrayList<Object>();

        List<Object> result = executeQuery(parameters, query, outputCount);

        return result;
    }

    public static List<Object> executeQuery(Object parameter, String query, int outputCount) throws Exception {
        List<Object> parameters = new ArrayList<Object>();

        parameters.add(parameter);
        List<Object> result = executeQuery(parameters, query, outputCount);

        return result;
    }

    public static List<Object> executeQuery(List<Object> parameters, String query, int outputColumnCount) throws Exception {
        resultSet = executePrepareStatement(query, parameters);

        List<Object> result = new ArrayList<Object>();

        while (resultSet.next()) {
            for (int i = 1; i <= outputColumnCount; i++) {
                result.add(resultSet.getObject(i));
            }
        }
        return result;
    }

    public static List<HashMap<String, Object>> executeQueryGetKeyValue(Object parameter, String query, int requiredColumnSize) throws Exception {
        List<Object> parameters = new ArrayList<Object>();

        parameters.add(parameter);
        List<HashMap<String, Object>> result = executeQueryGetKeyValue(parameters, query, requiredColumnSize);

        return result;
    }

    public static List<HashMap<String, Object>> executeQueryGetKeyValue(List<Object> parameters, String query, int requiredColumnSize) throws Exception {
        resultSet = executePrepareStatement(query, parameters);
        ResultSetMetaData metaData = resultSet.getMetaData();
        List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();

        while (resultSet.next()) {
            HashMap<String, Object> row = new HashMap<String, Object>();
            for (int i = 1; i <= requiredColumnSize; i++) {
                row.put(metaData.getColumnName(i), resultSet.getObject(i));
            }
            result.add(row);
        }
        return result;
    }
}
