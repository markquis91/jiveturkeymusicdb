

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QueryServlet
 */
@WebServlet("/albumservlet")
public class AlbumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// todo : decide on your caching strategy. Are pages refreshed with every load? Every hour? Or only at startup?
	// Then implement accordingly. (cache strategy is a good thing to comment, too, by the way)
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlbumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PrintWriter writer = response.getWriter();
		
		MusicPlayer player = new MusicPlayer();
		List<Album> albumset = new ArrayList<Album>();
		List<Artist> artistset = new ArrayList<Artist>();
		List<Song> songset = new ArrayList<Song>();
		
		try {
			// todo: This only needs to happen once. 
			// todo (important): Only need to read all database once....
			Class.forName("com.mysql.jdbc.Driver");
			player.readAndSaveArtistsFromDatabase("jdbc:mysql://localhost:3306/musicplayer1?autoReconnect=true&useSSL=false", "root", "Scoobydoo12");
		} catch (SQLException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			player.readAndSaveSongsProducersAndSongwritersFromDatabase("jdbc:mysql://localhost:3306/musicplayer1?autoReconnect=true&useSSL=false", "root", "Scoobydoo12");
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (request.getParameter("action").equals("albums")) {
			
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicplayer1?autoReconnect=true&useSSL=false", "root", "Scoobydoo12");
			Statement myStatement = myConnection.createStatement();
			ResultSet myResultSet = myStatement.executeQuery("select AlbumId, Title, Length, ArtistId from albums;");
			
			while (myResultSet.next()) {
			int albumId = myResultSet.getInt("AlbumId");
			String title = myResultSet.getString("Title");
			double length = myResultSet.getDouble("Length");
			int artistId = myResultSet.getInt("ArtistId");
			Artist artist = player.getArtistFromId(artistId);
			ListOfSongs songs = player.getSongsForAlbumId(albumId);
			Album album = new Album(albumId, artist, title, length, songs);
			albumset.add(album);
			
			}
			
			
			myConnection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		request.setAttribute("albumset", albumset);
		String url = "/PrintData.jsp";
		getServletContext().getRequestDispatcher(url).forward(request, response);
		
		
		}
		
		//if equals
		else if (request.getParameter("action").equals("artists")) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicplayer1?autoReconnect=true&useSSL=false", "root", "Scoobydoo12");
				Statement myStatement = myConnection.createStatement();
				ResultSet myResultSet = myStatement.executeQuery("select ArtistId, Name from artists;");
				
				while (myResultSet.next()) {
				int artistId = myResultSet.getInt("ArtistId");
				String name = myResultSet.getString("Name");

				Artist artist = new Artist(name, artistId);
				artistset.add(artist);
				}
				
				
				myConnection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("artistset", artistset);
			String url = "/artist.jsp";
			getServletContext().getRequestDispatcher(url).forward(request, response);
			
		}
			else if(request.getParameter("action").equals("songs")) {
			
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicplayer1?autoReconnect=true&useSSL=false", "root", "Scoobydoo12");
					Statement myStatement = myConnection.createStatement();
					ResultSet myResultSet = myStatement.executeQuery("select SongId, Title, Length, Rating, ProducerId, SongwriterId, ArtistId from songs;");
					
					while (myResultSet.next()) {
					int songId = myResultSet.getInt("SongId");
					String title = myResultSet.getString("Title");
					double length = myResultSet.getDouble("Length");
					double rating = myResultSet.getDouble("Rating");
					int producerId = myResultSet.getInt("ProducerId");
					int songwriterId = myResultSet.getInt("SongwriterId");
					int ArtistId = myResultSet.getInt("ArtistId");
					Artist artist = player.getArtistFromId(ArtistId);
					Producer producer = player.getProducerFromId(producerId);
					SongWriter songwriter = player.getSongwriterFromId(songwriterId);
					
					Song song = new Song(songId, artist, title, length, rating, producer, songwriter);
					songset.add(song);
					}
					
					
					myConnection.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				request.setAttribute("songset", songset);
				String url = "/song.jsp";
				getServletContext().getRequestDispatcher(url).forward(request, response);
				
			}

		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body>");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicplayer1?autoReconnect=true&useSSL=false", "root", "Scoobydoo12");
			Statement myStatement = myConnection.createStatement();
			ResultSet myResultSet = myStatement.executeQuery("select AlbumId, Title, Length from albums;");
			out.println("<table>");
			out.println("<tr><th>Album Id</th><th>Title</th><th>Length</th></tr>");
			
			while (myResultSet.next()) {
			int albumId = myResultSet.getInt("AlbumId");
			String title = myResultSet.getString("Title");
			String length = myResultSet.getString("Length");
				out.println("<tr><td>"+albumId+"</td><td>"+title+"</td><td>"+length+"</td>");
			}
			out.println("</table>");
			out.println("</html></body>");
			myConnection.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
		}
	}


