package org.example;

import java.sql.*;
import java.util.Optional;

public class DataBase {
    String uri = "jdbc:mysql://localhost:3306/clg";
    String user = "root";
    String password = "root";

    static Connection con = null;

    DataBase() {
        this.connect();
    }

    DataBase(String uri,String user,String password){
        this.uri = uri;
        this.user = user;
        this.password = password;

        this.connect();
    }

    void connect(){
        try {
            con = DriverManager.getConnection(this.uri,this.user,this.password);
            if(con!=null){
                System.out.println("database connected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    int add(String name,String email,String password) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement("INSERT INTO user(name,email,password) VALUES(?,?,?)");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,password);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void update(int id,Optional<String> name,Optional<String> email,Optional<String> password){
        String updateQuery = "UPDATE user SET name = ?, email = ?, password = ? WHERE id = ?";
        if(name.toString().equalsIgnoreCase("Optional[]") && email.toString().equalsIgnoreCase("Optional[]") && password.toString().equalsIgnoreCase("Optional[]")){
            System.out.println("Please provide data for update");
            return;
        }

        try {
            PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
            if(name.isPresent() && !name.toString().equalsIgnoreCase("Optional[]")) preparedStatement.setString(1,name.get());
            if(email.isPresent() && !email.toString().equalsIgnoreCase("Optional[]")) preparedStatement.setString(2,email.get());
            if(password.isPresent() && !password.toString().equalsIgnoreCase("Optional[]")) preparedStatement.setString(3, password.get());
            preparedStatement.setInt(4,id);
            int result =  preparedStatement.executeUpdate();
            if(result>0){
                System.out.println("data updated");
            }else{
                System.out.println("Faild to update data");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void delete(int id){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement("DELETE FROM user WHERE id = ?");
            preparedStatement.setInt(1,id);
            int isDeleted = preparedStatement.executeUpdate();
            if(isDeleted>0){
                System.out.println("data deleted");
            }else{
                System.out.println("data not deleted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void getAll(){
        try{
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM user");
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("--------------------------------------");
            System.out.println("Id\tName\tEmail\tPassword");
            System.out.println("--------------------------------------");
            while(rs.next()){
                int id = Integer.parseInt(rs.getString("id"));
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password1 = rs.getString("password");
                System.out.println(id+"\t"+name+"\t"+email+"\t"+password1);
            }
            System.out.println("--------------------------------------");
        }catch (SQLException e){
            System.out.println("Something went worng");
        }
    }
}
