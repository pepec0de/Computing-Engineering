package isdd.practice3;

//STEP 1. Import required packages

import java.sql.*;

public class firstExample {
// JDBC driver name and database URL

    static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static String DB_URL = "jdbc:oracle:thin:@172.17.20.39:1521:ETSI";

    //  Database credentials
    static String USER = "ISDD_003";
    static String PASS = "holaBuenas";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {

            System.out.println("starting");
            // STEP 2. Register the JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            if (conn == null)
                throw new Exception("Connection error");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "create table MEMBER (" +
"    m_num char(4)," +
"    m_name varchar(300) not null," +
"    m_id varchar(9) not null," +
"    m_birhtdate varchar(10)," +
"    m_phone varchar(9)," +
"    m_emailMember varchar(50)," +
"    m_startingDateMember varchar(10) not null," +
"    m_cathegoryMember char(1) not null," +
"    CONSTRAINT PK_Member PRIMARY KEY (m_num)," +
"    CONSTRAINT m_idUnique UNIQUE (m_id)," +
"    CONSTRAINT C_cathegory check (m_cathegoryMember in ('A','B','C','D','E')));";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
//            while (rs.next()) {
//                //Retrieve by column name
//                String name = rs.getString("name");
//                String address = rs.getString("address");
//                String phone = rs.getString("phone");
//
//                //Display values
//                System.out.print("Name: " + name);
//                System.out.print(", Address: " + address);
//                System.out.print(", Phone: " + phone);
//                System.out.println("-");
//            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            System.err.println("SQLEXCP: " + se.getMessage());
        } catch (Exception e) {
            //Handle errors for Class.forName
            System.err.println("EXCP: " + e.getMessage());

        }
    }
}