import hu.alkfejl.model.Switcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DegreeServlet")
public class DegreeServlet extends HttpServlet {
    private Switcher switcher = new Switcher();
    private String degree = "";

    /**
     * Homerseklet szamolas servletje
     * Egyszeru kerest indit a core szamolo reszenek, de valto servletek
     * kulon lettek megvalositva egyszeruseg, es parhuzamos hasznalat miatt
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        degree = switcher.degree(req.getParameter("num1"), req.getParameter("to"));
        resp.sendRedirect("pages/multivalto.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("degree", this.degree);
    }
}
