import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;

public class ScheduleServlet extends HttpServlet {
    // Simulated storage for simplicity. Replace with database integration in production.
    private Map<String, List<String>> userSchedules = new HashMap<>();
    private Map<String, List<String>> userWatchlists = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        String action = request.getParameter("action"); // "schedule" or "watchlist"

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        if (userId == null || action == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"error\": \"Missing userId or action\"}");
            return;
        }

        if (action.equals("schedule")) {
            List<String> schedule = userSchedules.getOrDefault(userId, new ArrayList<>());
            out.write("{\"schedule\": " + schedule.toString() + "}");
        } else if (action.equals("watchlist")) {
            List<String> watchlist = userWatchlists.getOrDefault(userId, new ArrayList<>());
            out.write("{\"watchlist\": " + watchlist.toString() + "}");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"error\": \"Invalid action\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        String action = request.getParameter("action"); // "schedule" or "watchlist"
        String courseId = request.getParameter("courseId");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        if (userId == null || action == null || courseId == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"error\": \"Missing userId, action, or courseId\"}");
            return;
        }

        if (action.equals("schedule")) {
            userSchedules.computeIfAbsent(userId, k -> new ArrayList<>()).add(courseId);
            out.write("{\"message\": \"Course added to schedule\"}");
        } else if (action.equals("watchlist")) {
            userWatchlists.computeIfAbsent(userId, k -> new ArrayList<>()).add(courseId);
            out.write("{\"message\": \"Course added to watchlist\"}");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"error\": \"Invalid action\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        String action = request.getParameter("action"); // "schedule" or "watchlist"
        String courseId = request.getParameter("courseId");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        if (userId == null || action == null || courseId == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"error\": \"Missing userId, action, or courseId\"}");
            return;
        }

        if (action.equals("schedule")) {
            List<String> schedule = userSchedules.getOrDefault(userId, new ArrayList<>());
            if (schedule.remove(courseId)) {
                out.write("{\"message\": \"Course removed from schedule\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"error\": \"Course not found in schedule\"}");
            }
        } else if (action.equals("watchlist")) {
            List<String> watchlist = userWatchlists.getOrDefault(userId, new ArrayList<>());
            if (watchlist.remove(courseId)) {
                out.write("{\"message\": \"Course removed from watchlist\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"error\": \"Course not found in watchlist\"}");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"error\": \"Invalid action\"}");
        }
    }
}
