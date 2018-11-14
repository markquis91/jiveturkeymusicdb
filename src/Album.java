
public class Album {
	
	private int albumId;
	public Artist albumArtist;
	private String albumTitle;
	private double albumLength;
	private String albumLength1;
	private ListOfSongs albumSongs;
	
	public Album(int id, Artist artist, String title, double length, ListOfSongs songs) {
		albumId = id;
		albumArtist = artist;
		albumTitle = title;
		albumLength = length;
		albumSongs = songs;
	}

	public Album() {

	}
	
	

	public Artist getAlbumArtist() {
		return albumArtist;
	}
	
	public int getAlbumId() {
		return albumId;
	}
	
	
	public ListOfSongs getListOfSongs() {
		return albumSongs;
	}

	public String getAlbumTitle() {
		return albumTitle;
	}

	public String getAlbumLengthString() {
		return albumLength1;
	}
	
	public double getAlbumLength() {
		return albumLength;
	}


	

}
