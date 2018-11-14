
public class Song {
	private int songId;
	private Artist songArtist;
	private String songTitle;
	private double songLength;
	private double songRating;
	private Producer songProducer;
	private SongWriter songWriter;
	
	public Song(int id, Artist sa, String title, double sl, double sr, Producer sp, SongWriter sw) {
		songId = id;
		songArtist = sa;
		songTitle = title;
		songLength = sl;
		songRating = sr;
		songProducer = sp;
		songWriter = sw;
	}
	
	public Song() {
		
	}
		
	public int getSongId() {
		return songId;
	}
	
	public Artist getArtist() {
		return songArtist;
	}
	
	public String getTitle() {
		return songTitle;
	}

	public double getLength() {
		return songLength;
	}

	public double getRating() {
		return songRating;
	}
	
	public Producer getProducer() {
		return songProducer;
	}
	
	public SongWriter getSongWriter() {
		return songWriter;
	}
	
}

