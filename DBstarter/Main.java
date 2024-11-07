
import org.postgresql.util.PSQLException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        final String URL = "jdbc:postgresql://pg/studs";
        final String USERNAME = "";
        final String PASSWORD = "";
        Connection connection = null;
        ResultSet rs;
        String query;
        try {
            connection =  DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            System.out.println("!");

            try {
                Table Users = new Table("Users");

                Users.addField("username TEXT PRIMARY KEY");
                Users.addField("password TEXT");

                Users.createTable(stm);
            } catch (PSQLException e) {
                System.out.println("Table Users is already exists");
            }

            try {
                Table Albums = new Table("Albums");

                Albums.addField("albumId INTEGER PRIMARY KEY");
                Albums.addField("name TEXT");
                Albums.addField("sales INTEGER");

                Albums.createTable(stm);
            } catch (PSQLException e) {
                System.out.println("Table Albums is already exists");
            }

            Table MusicGenres = new Table("MusicGenres");
            MusicGenres.addField("id INTEGER PRIMARY KEY");
            MusicGenres.addField("genre TEXT");
            try {
                MusicGenres.createTable(stm);
            } catch (PSQLException e) {
                System.out.println("Table MusicGenres is already exists");
            }
            try {
                MusicGenres.insert(stm, "1, 'Rock'");
            } catch (PSQLException e) {            }
            try {
                MusicGenres.insert(stm, "2, 'Progressive rock'");
            } catch (PSQLException e) {}
            try {
                MusicGenres.insert(stm, "3, 'Post punk'");
            } catch (PSQLException e) {}
            try {
                MusicGenres.insert(stm, "4, 'Post rock'");
            } catch (PSQLException e) {}
            try {
                MusicGenres.insert(stm, "5, 'Punk rock'");
            } catch (PSQLException e) {}
            try {
                Table MusicBands = new Table("MusicBands");

                MusicBands.addField("username TEXT");
                MusicBands.addField("id INTEGER CHECK (id > 0) PRIMARY KEY");
                MusicBands.addField("name TEXT NOT NULL");
                MusicBands.addField("x INTEGER");
                MusicBands.addField("y INTEGER");
                MusicBands.addField("creationDate DATE");
                MusicBands.addField("numberOfParticipants INTEGER CHECK (numberOfParticipants > 0)");
                MusicBands.addField("singlesCount INTEGER CHECK (singlesCount > 0)");
                MusicBands.addField("albumsCount INTEGER CHECK (albumsCount > 0)");
                MusicBands.addField("genreId INTEGER");
                MusicBands.addField("albumId INTEGER");
                MusicBands.createTable(stm);
            } catch (PSQLException e) {
                System.out.println("Table MusicBands is already exists");
            }


            System.out.println(":)");


            Scanner sc = new Scanner(System.in);
            while (true){
                System.out.println();
                query = sc.nextLine();
                if (query.equals("exit")) {
                    break;
                } else {
                    try {
                        rs = stm.executeQuery(query);
                        ResultSetMetaData meta= rs.getMetaData();
                        int columnNum=meta.getColumnCount();
                        for(int i=1;i<=columnNum;i++){
                            System.out.print(meta.getColumnName(i)+" ");
                        }
                        System.out.println();
                        System.out.println(">-------------------------------------------<");
                        System.out.println();
                        while(rs.next()){
                            for(int i=1;i<=columnNum;i++){
                                System.out.print(rs.getString(i)+" ");
                            }
                            System.out.println();
                        }
                        System.out.println();
                        System.out.println(">-------------------------------------------<");
                        System.out.println();
                    } catch (SQLException e) {
                        System.out.println("Nothing to print");
                    }
                }
            }
            connection.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("Не удалось подключиться к базе данных");
            throw e;
        }
    }
}
