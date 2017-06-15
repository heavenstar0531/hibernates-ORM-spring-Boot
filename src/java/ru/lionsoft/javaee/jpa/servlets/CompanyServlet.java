package ru.lionsoft.javaee.jpa.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.lionsoft.javaee.jpa.entities.Company;
import ru.lionsoft.javaee.jpa.entities.Department;
import ru.lionsoft.javaee.jpa.entities.Employee;
import ru.lionsoft.javaee.jpa.facades.CompanyFacade;

/**
 * Класс сервлета для тестирования работы EclipseLink JPA с объектными таблицами
 * 
 * @author Igor Morenko <morenko at lionsoft.ru>
 */
@WebServlet(name = "CompanyServlet", urlPatterns = {"/company"})
public class CompanyServlet extends HttpServlet {

    @EJB
    private CompanyFacade facade;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CompanyServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CompanyServlet at " + request.getContextPath() + "</h1>");

            out.println("<form><input type='number' name='inn'/><br/><input type='submit'/></form>");

            String innParam = request.getParameter("inn");
            Long inn = innParam != null ? Long.valueOf(innParam) : 1234567l;
            
            Company company = facade.find(inn);
            
            if (company != null) {
                out.println("<table>");
                out.println("<tr><td align='right'>Company INN:</td><td></td><td>" + company.getInn() + "</td></tr>");
                out.println("<tr><td align='right'>Company Name:</td><td></td><td>" + company.getName() + "</td></tr>");
                out.println("<tr><td align='right'>Company Address:</td><td></td><td>" + company.getAddress() + "</td></tr>");
                out.println("</table>");

                for (Department department : company.getDepartments()) {
                    out.println("<h2>Department " + department.getName() + "(#" + department.getId() + ")</h2>");

                    out.println("<h3>Employees in department</h3>");
                    out.println("<table border='1'>");
                    out.println("<tr>"
                            + "<th>No</th>"
                            + "<th>First Name</th>"
                            + "<th>Last Name</th>"
                            + "<th>Job</th>"
                            + "<th>Salary</th>"
                            + "</tr>");
                    for (Employee employee : department.getEmployees()) {
                        out.printf("<tr><td>%d</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>\n", 
                                employee.getNum(), 
                                employee.getFirstName(), 
                                employee.getLastName(), 
                                employee.getJob(), 
                                employee.getSalary());
                    }
                }
                out.println("</table>");
            } else {
                out.println("<font color='red'>Not found company with inn:" + inn + "</font");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
