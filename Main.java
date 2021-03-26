import java.sql.*;

public class Main {

    public static void main(String[]args){
        try{
            /*accepts connection string and returns a connection instance. How do I know that? DOCUMENTATION! :)

            Instead of DriverManager, we can utilize Data Source Objects as well, if anyone writes a 200 word
            explanation of what that is and the difference between data source object and driver manager you will be
            awarded 1% as EC.

            If this database did not exist, the Driver Manager will create one for us.
            Remember that this path won't work you need to change it!
             */
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Alaadin\\Desktop\\testjava.db");
            /* This is a statement object, recall that Java is an OOP language therefore we created an object
            statement and we will execute this instance of statement we have created wth a .execute method.
             */

            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS contacts (name TEXT, phone INTEGER, email TEXT)");
            statement.execute("INSERT INTO contacts (name, phone, email) VALUES ('Alaadin', '565656', 'aaddas@trentu.ca')");
            statement.execute("INSERT INTO contacts (name, phone, email) VALUES ('SomeoneElse', '565656', 'aaddas@trentu.ca')");
            //we utilize a result set in order to store the output of a query.

            ResultSet rs = statement.executeQuery("SELECT * FROM contacts");
            //while the result set has a next row set values equal to the variables below and print
            while (rs.next())
            {
                String name = rs.getString("name");
                int phone = rs.getInt("phone");
                String email = rs.getString("email");


                // print the results
                System.out.format("%s, %s, %s\n", name, phone, email);
            }
            statement.execute("UPDATE contacts SET name = 'qwerty' WHERE name = 'Alaadin'");

            /* we must close the statement and the connection as well. Does anyone know why we do that?
                If you close the connection first you will get an error.
             */
            statement.close();
            conn.close();

        }catch (SQLException e){

            System.out.println("Something went wrong: " + e.getMessage());
        }//end catch bracket
    }//end main method bracket


}//end main class bracket
