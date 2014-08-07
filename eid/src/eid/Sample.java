package eid;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.fedict.eid.applet.service.Address;
import be.fedict.eid.applet.service.Identity;

/**
 * Servlet implementation class Sample
 */
@WebServlet("/sample")
public class Sample extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sample() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Address address = (Address) request.getSession().getAttribute("eid.address");
	    Identity identity = (Identity) request.getSession().getAttribute("eid.identity");
	    request.setAttribute("address", address);
        request.setAttribute("identity", identity);
	    
	    request.getRequestDispatcher("/identity-result.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
