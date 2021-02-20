import hu.alkfejl.model.Switcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SzamrendszerServlet")
public class SzamrendszerServlet extends HttpServlet {
    private Switcher switcher = new Switcher();
    private String number = "";

    /**
     * Szamrendszer szamolas servletje
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

        number = switcher.baseSwitch(req.getParameter("num1"), req.getParameter("from"), req.getParameter("to"));
        resp.sendRedirect("pages/multivalto.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("number", this.number);
    }
}
