package dao;

import db.DbConnection;
import model.Employer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

public class EmployerDao implements DAO<Employer>{

    // variables
    Connection connection = null;
    Statement statement = null;
    String requet = null;
    Employer employer = null;

    public EmployerDao() {
        try {
            connection = DbConnection.getInstance().getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Employer> getAll() {
        List<Employer> employers = new ArrayList<>();
        String requet = "SELECT * FROM Employées;";
        System.out.println(requet);
        try {
            ResultSet resultSet = statement.executeQuery(requet);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                Employer employer = new Employer(id, nom);
                System.out.println(employer.toString());
                employers.add(employer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employers;
    }

    @Override
    public Employer getById(int id) {
        requet = "SELECT * FROM Employées WHERE id =" + id + " ;";
        System.out.println(requet);
        try {
            ResultSet resultSet = statement.executeQuery(requet);
            resultSet.next();
            int idemp = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            employer = new Employer(idemp, nom);
            System.out.println(employer.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employer;
    }

    @Override
    public void save(Employer t) {
        requet = "insert into Employées values(" + t.getId() + ",'" + t.getNom() + "');";
        System.out.println(requet);

        try {
            int resultat = statement.executeUpdate(requet);
            if (resultat != 0) {
                System.out.println("Insertion effectuée");
            } else {
                System.out.println("Erreur d'insertion");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Employer employer) {
        String query = "UPDATE Employées SET nom = ? WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employer.getNom());
            preparedStatement.setInt(2, employer.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Employer employer) {
        String query = "DELETE FROM Employées WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, employer.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        System.out.println(new EmployerDao().getById(1));
        //new EmployerDao().save(new Employer(1, "Mohamed Lehbib"));
    }
}
