package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class TextNote extends Note {

	String content;
	private static final long serialVersionUID = 1L;

	public TextNote(String title) {
		super(title);
	}

	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}

	public TextNote(File f) throws IOException {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}

	@Override
	public String getContent() {
		return content;
	}

	public String getTextFromFile(String absolutePath) throws IOException {
		String result = "";

		try(BufferedReader br = new BufferedReader(new FileReader(absolutePath))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			result = sb.toString();
		}
		return result;
	}

	public void exportTextToFile(String pathFolder) {
		BufferedWriter bw = null;

		if (pathFolder == "")
			pathFolder = ".";
		File file = new File(pathFolder + File.separator + this.getTitle().replaceAll(" ", "_") + ".txt");

		try {
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(this.content);
			bw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
