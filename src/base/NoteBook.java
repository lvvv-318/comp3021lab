package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NoteBook implements java.io.Serializable {

	private ArrayList<Folder> folders;
	private static final long serialVersionUID = 1L;

	public NoteBook() {
		folders = new ArrayList<Folder>();
	}

	public NoteBook(String file) {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("file.ser");
			in = new ObjectInputStream(fis);
			NoteBook n = (NoteBook)in.readObject();
			this.folders = n.folders;
			in.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public boolean save(String file) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("file.ser");
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void addFolder(String name) {
		folders.add(new Folder(name));
	}

	public Folder getFolder(String name) {
		for(Folder f : folders) {
			if(f.getName().equals(name)) {return f;}
		}
		return null;
	}

	public Note getNote(String folderName,String noteName) {
		Folder folder = getFolder(folderName);
		for(Note note : folder.getNotes()) {
			if(note.getTitle().equals(noteName)) {return note;}
		}
		return null;
	}

	public void saveNote(String folderName,String noteName, String content) {
		Note note = getNote(folderName,noteName);
		if(note instanceof TextNote) {
			TextNote textnote = (TextNote) note;
			textnote.content = content;
		}
		else
			return;
	}

	public void removeNote(String folderName, String noteName) {
		Folder folder = getFolder(folderName);
		Note note = getNote(folderName,noteName);
		folder.getNotes().remove(note);
	}

}
