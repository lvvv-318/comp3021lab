package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder> {

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

	@Override
	public int compareTo(Folder o) {
		int result = this.folderName.compareTo(o.folderName);
		return result;
	}

	public void sortNotes() {
		Collections.sort(this.notes);
	}

	public List<Note> searchNotes(String keywords) {

		String[] keywordsArray = keywords.toLowerCase().split(" ");

		List<Note> result = new ArrayList<Note>();

		for (Note n : this.notes) {

			boolean check = true;

			for (int i = 0; i < keywordsArray.length; i++) {

				if (n instanceof ImageNote) {
					if ((i+2) <= keywordsArray.length && keywordsArray[i+1].equals("or")) {
						if (n.getTitle().toLowerCase().contains(keywordsArray[i]) ||
							n.getTitle().toLowerCase().contains(keywordsArray[i+2])) {
							check = true;
						} else {
							check = false;
							break;
						}
						i += 2;
					} else {
						if (n.getTitle().toLowerCase().contains(keywordsArray[i])) {
							check = true;
						} else {
							check = false;
							break;
						}
					}
				}

				if (n instanceof TextNote) {
					if ((i+2) <= keywordsArray.length && keywordsArray[i+1].equals("or")) {
						if (n.getTitle().toLowerCase().contains(keywordsArray[i]) ||
							n.getTitle().toLowerCase().contains(keywordsArray[i+2]) ||
							n.getContent().toLowerCase().contains(keywordsArray[i]) ||
							n.getContent().toLowerCase().contains(keywordsArray[i+2])) {
							check = true;
						} else {
							check = false;
							break;
						}
						i += 2;
					} else {
						if (n.getTitle().toLowerCase().contains(keywordsArray[i]) ||
							n.getContent().toLowerCase().contains(keywordsArray[i])) {
							check = true;
						} else {
							check = false;
							break;
						}
					}
				}
			}

			if (check)
				result.add(n);

		}

		return result;

	}
}
