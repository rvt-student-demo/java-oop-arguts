package rvt;

import java.sql.*;
import java.util.ArrayList;

public class TodoList {
    private static final String DB_URL = "jdbc:sqlite:todo.db";

    public TodoList() {
        initSchema();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    private void initSchema() {
        String sql = """
                CREATE TABLE IF NOT EXISTS todo (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    task TEXT NOT NULL
                )
                """;

        try (Connection conn = connect();
             Statement statement = conn.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Schema init failed: " + e.getMessage());
        }
    }

    public void add(String task) {
        String sql = "INSERT INTO todo(task) VALUES(?)";

        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, task);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Add failed: " + e.getMessage());
        }
    }

    public ArrayList<String> getTasks() {
        ArrayList<String> tasks = new ArrayList<>();

        String sql = "SELECT task FROM todo ORDER BY id";

        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet results = statement.executeQuery()) {

            while (results.next()) {
                tasks.add(results.getString("task"));
            }

        } catch (SQLException e) {
            System.out.println("Find all failed: " + e.getMessage());
        }

        return tasks;
    }

    public void remove(int number) {
        ArrayList<Integer> ids = new ArrayList<>();

        String selectSql = "SELECT id FROM todo ORDER BY id";

        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(selectSql);
             ResultSet results = statement.executeQuery()) {

            while (results.next()) {
                ids.add(results.getInt("id"));
            }

        } catch (SQLException e) {
            System.out.println("ID select failed: " + e.getMessage());
            return;
        }

        if (number < 1 || number > ids.size()) {
            return;
        }

        int idToRemove = ids.get(number - 1);
        removeById(idToRemove);
    }

    public void removeById(int id) {
        String sql = "DELETE FROM todo WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Remove failed: " + e.getMessage());
        }
    }
}