package backend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ScheduleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addSchedule(request, response);
        } else if ("remove".equals(action)) {
            removeSchedule(request, response);
        } else if ("search".equals(action)) {
            searchSchedule(request, response);
        }
    }

    private void addSchedule(HttpServletRequest request, HttpServletResponse response) {
        // Get course details (name, professor, time, etc.)
        // Insert the schedule entry into the database
        // Return a success or error message
    }

    private void removeSchedule(HttpServletRequest request, HttpServletResponse response) {
        // Get course name and time from request
        // Remove the entry from the Schedule table in the database
        // Return a status message
    }

    private void searchSchedule(HttpServletRequest request, HttpServletResponse response) {
        // Get search criteria (e.g., course name)
        // Query the database and return matching entries
        // Display results as a response
    }
}
