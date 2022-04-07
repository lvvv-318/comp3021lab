package base;

import java.util.Date;

public class Note implements Comparable<Note>, java.io.Serializable {

	private Date date;
	private String noteTitle;
	private static final long serialVersionUID = 1L;

	public Note(String title) {
		this.noteTitle = title;
		date = new Date();
	}

	public String getTitle() {
		return noteTitle;
	}

	public String getContent() {
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Note other = (Note) obj;
		if (noteTitle == null) {
			if (other.noteTitle != null)
				return false;
		} else if (!noteTitle.equals(other.noteTitle))
			return false;
		return true;
	}

	@Override
	public int compareTo(Note o) {
		if (this.date.before(o.date))
			return 1;
		else if (this.date.after(o.date))
			return -1;
		else
			return 0;
	}

	public String toString() {
		return date.toString() + "\t" + noteTitle;
	}

}
