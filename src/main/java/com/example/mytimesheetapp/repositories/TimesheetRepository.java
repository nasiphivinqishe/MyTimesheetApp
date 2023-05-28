package com.example.mytimesheetapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TimesheetRepository {
    private Connection conn;

    public ResultSet getTimesheetById(String timesheetId) throws Exception {
        try {
            this.conn = DBUtil.createConnectionViaUserPwd();

            String query = "SELECT * FROM timesheets WHERE timesheet_id =? LIMIT 1";
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setString(1, timesheetId);
            System.out.println("timesheetId on Repository: "+timesheetId);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            if (this.conn != null) {
                this.conn.close();
            }
            throw new SQLException(e);
        }
    }
}
