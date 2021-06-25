package test;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;
import java.util.Scanner;

public class testMain {

	public static int openTabs(LinkedList<String> URL) {

		String urlString = URL.poll();
		int count = 0;
		int waitCycle = 0;
		File fileCheck = null;

		while (urlString != null) {

			try {
				if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {

					fileCheck = new File("C:\\Users\\HewYL\\Downloads\\" + Integer.toString(count) + ".ts");
					Desktop.getDesktop().browse(new URI(urlString));

					while (!fileCheck.exists()) {
						Thread.sleep(500);
						waitCycle++;

						if (waitCycle >= 10) {
							throw new Exception("Internet problem.");
						}
					}

					System.out.println(Integer.toString(count) + ".ts succesfully downloaded.");
					waitCycle = 0;
					count++;

				}

				else {
					throw new Exception("Unknown Error.");
				}

			}

			catch (Exception e) {
				System.out.println("Exception caught.");
			}

			urlString = URL.poll();

		}

		return count;

	}

	public static LinkedList<String> getLinkedList() {

		File file = new File("links.txt");
		LinkedList<String> listOfURL = new LinkedList<String>();

		try {
			Scanner sc = new Scanner(file);

			while (!sc.next().equals("#EXT-X-ENDLIST")) {
				listOfURL.add(sc.next());
			}

			sc.close();
			return listOfURL;
		}

		catch (FileNotFoundException fnfe) {
			System.out.println("File not found.");
		}

		catch (Exception e) {
			System.out.println("Exception caught");
		}

		return null;

	}

	public static void checkAllFiles() {

		int count = 0;
		LinkedList<String> missing = new LinkedList<String>();
		int missingSize = 0;
		File file = new File("C:\\Users\\HewYL\\Downloads\\" + String.valueOf(count) + ".ts");
		boolean pass = false;

		while (!pass) {

			if (count <= 1162) {
				file = new File("C:\\Users\\HewYL\\Downloads\\" + String.valueOf(count) + ".ts");

				if (!file.exists()) {
					missing.add(String.valueOf(count) + ".ts");
					missingSize++;
				}

				count++;
			}

			else {
				break;
			}

		}

		if (missing.size() == 0) {
			System.out.println("No missing files.");
		}

		else {
			System.out.println("WARNING: ");

			for (int x = 0; x < missingSize; x++) {
				System.out.println(missing.poll() + " is missing.");
			}
		}

	}

	public static void createVidList() {

		try {
			FileWriter fw = new FileWriter("C:\\Users\\HewYL\\Downloads\\sep\\vidlist.txt");
			fw.write( "copy /b ");

			for (int x = 0; x <= 1162; x++) {

				fw.write( Integer.toString(x) + ".ts ");
				if ( x != 1162 ) {
					fw.write("+ " );
				}

			}
			
			fw.write( "joined_files.ts");

			fw.close();
		}

		catch (IOException e) {
		}

	}

	public static void main(String[] args) {

		// System.out.println("Successfully downloaded " + openTabs(getLinkedList()) + "
		// files.");
		// checkAllFiles();
		createVidList();

	}
}