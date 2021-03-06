

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class albumservlet
 */
@WebServlet("/artistservlet")
public class ArtistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.append("<link rel=stylesheet type=text/css href=servlet.css>");
		writer.append("<head><title>Jive Turkey Music Player|Albums</title></head>");
		writer.append("<h1 class=pagetitle>Jive Turkey Music Player</h1>");
		/*writer.append("<ul class=menulist>");
		writer.append("<li><a href=\"Homepage.jsp\">Home</a></li>"
						+ "	<li><a href=\"Aboutpage.jsp\">About</a></li>\r\n" + 
				" 			<li><a href=\"Servicespage.jsp\">Services</a></li>\r\n" + 
				" 			<li><a href=\"Contactpage.jsp\">Contact</a></li>\r\n" + 
				" 			<li style=\"float: right\"><a href=\"Loginpage.jsp\">Login</a></li>\r\n" + 
				" 			<li style=\"float: right\"><a href=\"Signuppage.jsp\">Sign Up</a></li>");
		writer.append("</ul>");
		*/writer.append("<body>");
		writer.append("<ul class=displaymenu><li><a class=atable href=albumservlet style=display:block;>Albums</a></li><li><a href=artistservlet style=display:block;>Artists</a></li><li><a href=songservlet style=display:block;>Songs</a></li></ul>");

		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicplayer1?autoReconnect=true&useSSL=false", "root", "Scoobydoo12");
			Statement myStatement = myConnection.createStatement();
			ResultSet myResultSet = myStatement.executeQuery("select ArtistId, Name from artists;");
			writer.append("<table align=center>");
			writer.append("<tr><th>Artist Id</th><th>Name</th></tr>");
			while (myResultSet.next()) {
			int artistId = myResultSet.getInt("ArtistId");
			String name = myResultSet.getString("Name");

			
			
			writer.append("<tr><td>"+artistId+"</td><td>"+name+"</td></tr>");
			}
			writer.append("</table>");
			writer.append("</html>");
			writer.append("</body>");
			
			
			myConnection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}

	
	//  @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
