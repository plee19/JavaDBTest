import java.sql.*;

public class CreateCatalogDB {
    public CreateCatalogDB() {
        try {
            final String DB_URL = "jdbc:derby:CatalogDB;create=true";
            Connection conn = DriverManager.getConnection(DB_URL);
            dropTables(conn);
            buildCatalogTable(conn);
            buildCartTable(conn);
            conn.close(); }
        catch (Exception e) {
            System.out.println("Error Creating the Catalog Table");
            System.out.println(e.getMessage());
        }
    }

    public static void dropTables(Connection conn) {
        System.out.println("Checking for existing tables.");
        try {
            Statement stmt = conn.createStatement();
            try {
                stmt.execute("DROP TABLE Catalog");
                System.out.println("Catalog table dropped.");
                stmt.execute("DROP TABLE Cart");
                System.out.println("Cart table dropped.");
            } catch (SQLException ex) {}
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public static void buildCatalogTable(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE Catalog (" +
                    "Description CHAR(25)," +
                    "ProdNum CHAR(10) NOT NULL PRIMARY KEY, " +
                    "Price DOUBLE " +
                    ")");

            stmt.execute("INSERT INTO Catalog VALUES ( " +
                    "'Pizza', " +
                    "'1', " +
                    "12.95 )");

            stmt.execute("INSERT INTO Catalog VALUES ( " +
                    "'Coffee', " +
                    "'2', " +
                    "1.99 )");

            stmt.execute("INSERT INTO Catalog VALUES ( " +
                    "'Tiramisu', " +
                    "'3', " +
                    "5.99 )");

            stmt.execute("INSERT INTO Catalog VALUES ( " +
                    "'Calzone', " +
                    "'4', " +
                    "10.95 )");

            stmt.execute("INSERT INTO Catalog VALUES ( " +
                    "'Soda', " +
                    "'5', " +
                    "2.29 )");

            stmt.execute("INSERT INTO Catalog VALUES ( " +
                    "'Fries', " +
                    "'6', " +
                    "3.99 )");

            System.out.println("Catalog table created.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public static void buildCartTable(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE Cart" +
                    "(ProdNum CHAR(10) NOT NULL PRIMARY KEY, " +
                    "Description CHAR(25), " +
                    "Price DOUBLE" +
                    ")");
            System.out.println("Cart table created.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

}
