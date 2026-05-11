import db.DB;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
         conn= DB.getConnection();

         st = conn.createStatement();

         rs = st.executeQuery("select * from department");

         while (rs.next()){// enquanto estiver verdadeiro ele continua, quando chegar na última posição ele verifica se tem mais
             //alguma coisa, ai rertona falso;
             System.out.println(rs.getInt("Id") + "-"+ rs.getString("Name"));

         }



        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    }
