package com.example.mytimesheetapp.repositories;

import com.example.mytimesheetapp.models.Timesheet;
import com.example.mytimesheetapp.models.TimesheetStatusRequest;
import com.example.mytimesheetapp.models.TimesheetUpdateRequest;
import com.example.mytimesheetapp.utils.DBUtil;

import java.sql.*;

public class TimesheetRepository {
    private Connection conn;

    public ResultSet getTimesheetById(int timesheetId) throws Exception {
        try {
            this.conn = DBUtil.createConnectionViaUserPwd();

            String query = "SELECT * FROM timesheet_updated WHERE timesheet_id =? LIMIT 1";

            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setInt(1, timesheetId);
            ResultSet resultSet = preparedStatement.executeQuery();
            this.conn.close();
            return resultSet;
        } catch (SQLException e) {
            if (this.conn != null) {
                this.conn.close();
            }
            throw new SQLException(e);
        }
    }

    public ResultSet deleteTimesheetById(String timesheetId) throws Exception {
        try {
            this.conn = DBUtil.createConnectionViaUserPwd();

            String query = "DELETE FROM timesheet_updated WHERE timesheet_id =?";
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setString(1, timesheetId);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            if (this.conn != null) {
                this.conn.close();
            }
            throw new SQLException(e);
        }
    }

    public ResultSet saveTimesheet(Timesheet timesheet) throws Exception {
        try {
            this.conn = DBUtil.createConnectionViaUserPwd();

            String query = "INSERT INTO timesheet_updated(date_signed, shift, hour_type, comment, task, employee_id, monday_hrs, tuesday_hrs,wednesday_hrs, thursday_hrs, friday_hrs, saturday_hrs, sunday_hrs, status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setDate(1, new java.sql.Date(timesheet.getDateSigned().getTime()));
            preparedStatement.setString(2, timesheet.getShift());
            preparedStatement.setString(3, timesheet.getHourType());
            preparedStatement.setString(4, timesheet.getComment());
            preparedStatement.setString(5, timesheet.getTask());
            preparedStatement.setInt(6, timesheet.getEmployee_id());
            preparedStatement.setInt(7, timesheet.getMondayHrs());
            preparedStatement.setInt(8, timesheet.getTuesdayHrs());
            preparedStatement.setInt(9, timesheet.getWednesdayHrs());
            preparedStatement.setInt(10, timesheet.getThursdayHrs());
            preparedStatement.setInt(11, timesheet.getFridayHrs());
            preparedStatement.setInt(12, timesheet.getSaturdayHrs());
            preparedStatement.setInt(13, timesheet.getSundayHrs());
            preparedStatement.setString(14, timesheet.getStatus().toString());

            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            if (this.conn != null) {
                this.conn.close();
            }
            throw new SQLException(e);
        }
    }

    public ResultSet updateTimesheetStatus (TimesheetStatusRequest timesheetUpdateStatusEvent) throws Exception {
        try {
            this.conn = DBUtil.createConnectionViaUserPwd();
            String query = "UPDATE  timesheet_updated SET  status=?, date_updated=?, comment=? WHERE timesheet_id =? ";
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setString(1, timesheetUpdateStatusEvent.getStatus().toString());
            preparedStatement.setDate(2, new java.sql.Date(timesheetUpdateStatusEvent.getDateUpdated().getTime()));
            preparedStatement.setString(3, timesheetUpdateStatusEvent.getComment());
            preparedStatement.setInt(4, timesheetUpdateStatusEvent.getTimesheet_id());


            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            if (this.conn != null) {
                this.conn.close();
            }
            throw new SQLException(e);
        }
    }

    public ResultSet employeeUpdateTimesheet (TimesheetUpdateRequest timesheetUpdateRequest) throws Exception {
        try {
            this.conn = DBUtil.createConnectionViaUserPwd();
            String query = "UPDATE timesheet_updated SET date_signed=?,shift=?,hour_type=?,comment=?,task=?,employee_id=?,monday_hrs=?,tuesday_hrs=?,wednesday_hrs=?,thursday_hrs=?,friday_hrs=?,saturday_hrs=?,sunday_hrs=?,status=? WHERE timesheet_id =?";
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setDate(1, new Date(timesheetUpdateRequest.getTimesheetUpdate().getDateSigned().getTime()));
            preparedStatement.setString(2, timesheetUpdateRequest.getTimesheetUpdate().getShift());
            preparedStatement.setString(3, timesheetUpdateRequest.getTimesheetUpdate().getHourType());
            preparedStatement.setString(4, timesheetUpdateRequest.getTimesheetUpdate().getComment());
            preparedStatement.setString(5, timesheetUpdateRequest.getTimesheetUpdate().getTask());
            preparedStatement.setInt(6, timesheetUpdateRequest.getTimesheetUpdate().getEmployee_id());
            preparedStatement.setInt(7, timesheetUpdateRequest.getTimesheetUpdate().getMondayHrs());
            preparedStatement.setInt(8, timesheetUpdateRequest.getTimesheetUpdate().getTuesdayHrs());
            preparedStatement.setInt(9, timesheetUpdateRequest.getTimesheetUpdate().getWednesdayHrs());
            preparedStatement.setInt(10, timesheetUpdateRequest.getTimesheetUpdate().getThursdayHrs());
            preparedStatement.setInt(11, timesheetUpdateRequest.getTimesheetUpdate().getFridayHrs());
            preparedStatement.setInt(12, timesheetUpdateRequest.getTimesheetUpdate().getSaturdayHrs());
            preparedStatement.setInt(13, timesheetUpdateRequest.getTimesheetUpdate().getSundayHrs());
            preparedStatement.setString(14, timesheetUpdateRequest.getTimesheetUpdate().getStatus().toString());
            preparedStatement.setInt(15, timesheetUpdateRequest.getTimesheetId());

            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            if (this.conn != null) {
                this.conn.close();
            }
            throw new SQLException(e);
        }
    }
}
