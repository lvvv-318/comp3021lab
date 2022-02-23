package base;

import java.util.ArrayList;

public class Folder {
	private ArrayList<Note> notes;
	private String folderName;

	public Folder(String name) {
		this.folderName = name;
		notes = new ArrayList<Note>();
	}

	public void addNote(Note newNote) {
		notes.add(newNote);
	}

	public String getName() {
		return folderName;
	}

	public ArrayList<Note> getNotes() {
		return notes;
	}

	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;

		for (Note note : notes) {
			if (note instanceof TextNote)
				nText++;
			if (note instanceof ImageNote)
				nImage++;
		}

		return folderName + ":" + nText + ":" + nImage;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (folderName == null) {
			if (other.folderName != null)
				return false;
		} else if (!folderName.equals(other.folderName))
			return false;
		return true;
	}

}
