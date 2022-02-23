package base;

import java.util.Date;

public class Note {
	private Date date;
	private String noteTitle;

	public Note(String title) {
		this.noteTitle = title;
		date = new Date();
	}

	public String getTitle() {
		return noteTitle;
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

}
