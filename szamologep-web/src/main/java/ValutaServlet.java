import hu.alkfejl.model.Switcher;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ValutaServlet")
public class ValutaServlet extends HttpServlet {
    private Switcher switcher = new Switcher();
    private String money = "";

    /**
     * Valuta szamolas servletje
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

        money = switcher.exchange(req.getParameter("num1"), req.getParameter("from"));
        resp.sendRedirect("pages/multivalto.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("money", this.money);
    }
}
