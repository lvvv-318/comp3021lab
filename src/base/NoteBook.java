package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook {

	private ArrayList<Folder> folders;

	public NoteBook() {
		folders = new ArrayList<Folder>();
	}

	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}

	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}

	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}

	public ArrayList<Folder> getFolders() {
		return folders;
	}

	public boolean insertNote(String folderName, Note note) {
		Folder f = null;

		for (Folder folder : folders) {
			if (folder.getName().equals(folderName)) {
				f = folder;
			}
		}

		if (f == null) {
			f = new Folder(folderName);
			folders.add(f);
		}

		for (Note a : f.getNotes()) {
			if (a.equals(note)) {
				System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
				return false;
			}
		}
		f.addNote(note);

		return true;

	}

	public void sortFolders() {
		for (Folder folder : folders) {
			folder.sortNotes();
		}
		Collections.sort(this.folders);
	}

	public List<Note> searchNotes(String keywords) {
		List<Note> result = new ArrayList<Note>();

		for (Folder f : folders) {
			List<Note> temp = f.searchNotes(keywords);
			for (int i = 0; i < temp.size(); i++) {
				result.add(temp.get(i));
			}
		}

		return result;
	}

}
