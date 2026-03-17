package me.marco.core.v2.database;

import me.marco.core.v2.CoreV2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.File;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager(CoreV2 plugin) {
        try {
            File dataFolder = new File(plugin.getDataFolder(), "database.db");
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            createTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createTables() {
        try (PreparedStatement ps = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS permissions (uuid TEXT PRIMARY KEY, rank TEXT)")) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
