import java.io.IOException;

import java.awt.FlowLayout;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javax.swing.text.BadLocationException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.HttpStatusException;

public class spider extends JFrame {

	private static HashMap<String, String> hmap = new HashMap<String, String>();
	private static Set<String> alreadyVisited = new HashSet<String>();
	private static Queue<String> visiting = new LinkedList<String>();

	private static ListIterator<String> iterator;
	private static search proWords = new search();

	private static ArrayList<String> linksfound;
	private static String query;
	static final int num_page_search = 3000;

	public static void main(String[] args) throws IOException {
		PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
		
		new spider();
		System.setOut(out);
	}

	public spider() {
		initComponents();
	}

	private JButton buttonSubmit;
	private JButton buttonClear;
	private JFrame mainFrame;
	private JLabel headerLabel;

	private JPanel controlPanel;

	private PrintStream standardOut;

	public void initComponents() {

		mainFrame = new JFrame("Web Crawler");
		mainFrame.setSize(600, 700);
		mainFrame.setLayout(new GridLayout(3, 3));
		buttonSubmit = new JButton("Submit the Query");
		buttonClear = new JButton("Clear the Output");

		headerLabel = new JLabel("", JLabel.CENTER);

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		mainFrame.add(headerLabel);

		JPanel panel = new JPanel();

		JTextArea inputString = new JTextArea("", 2, 50);
		JScrollPane scrollPane = new JScrollPane(inputString);

		JTextArea result = new JTextArea("", 5, 50);
		PrintStream printStream = new PrintStream(new CustomOutputStream(result));
		standardOut = System.out;
		System.setOut(printStream);
		System.setErr(printStream);
		JScrollPane scrollPane1 = new JScrollPane(result);

		headerLabel.setText("Please Enter the search string");
		panel.setLayout(new GridBagLayout());
		panel.add(buttonSubmit);

		controlPanel.add(scrollPane);
		controlPanel.add(panel);
		controlPanel.add(scrollPane1);
		controlPanel.add(buttonClear);
		mainFrame.add(controlPanel);

		mainFrame.setVisible(true);
		buttonSubmit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				for (String line : inputString.getText().split("\\n"))
					query = line;
				beginspider(query);

			}
		});
		buttonClear.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				// clears the text area
				try {
					result.getDocument().remove(0, result.getDocument().getLength());
					standardOut.println("Text area cleared");
				} catch (BadLocationException ex) {
					ex.printStackTrace();
				}
			}
		});

	}

	public static void beginspider(String str) {
		try {
			String sWords = "stopwords.txt";
			proWords.stopWordsConstructor(sWords);
			proWords.getQueries(str);
			spidering("http://www.unt.edu");
			preparePages();
			linksfound = proWords.top10();
			int end = 0;
			iterator = linksfound.listIterator();
			while (iterator.hasNext()) {
				if (end == 3000)
					break;
				System.out.println("" + iterator.next());
				end++;
			}
		} catch (IOException e) {
		}
	}

	public static void preparePages() throws IOException {
		proWords.reset();
		for (String link : alreadyVisited) {
			try {
				Document docs = Jsoup.connect(link).ignoreContentType(true).timeout(0).get();
				hmap.put(link, docs.text());
				proWords.readInputFiles(2, docs.text(), link);
			} catch (Exception e) {
			}
		}

	}

	public static void spidering(String URL) throws IOException {

		alreadyVisited.add(URL);
		visiting.add(URL);

		while (visiting.size() != 0 && alreadyVisited.size() != num_page_search) {
			String nextURL = visiting.poll();
			try {
				Document abc = Jsoup.connect(nextURL).ignoreContentType(true).timeout(0).get();
				Elements all = abc.select("a[href]");
				for (Element name : all) {
					String ele = name.attr("href");
					if (ele.contains("unt.edu") && !ele.contains("mailto:")) {
						if (!alreadyVisited.contains(ele)) {
							alreadyVisited.add(name.attr("abs:href"));
							visiting.add(name.attr("abs:href"));
							System.out.println(visiting);
							if (alreadyVisited.size() == num_page_search)
								break;
						}
					}
				}
			} catch (HttpStatusException e) {
			}
		}
	}
}

class CustomOutputStream extends OutputStream {
	private JTextArea textArea;

	public CustomOutputStream(JTextArea textArea) {
		this.textArea = textArea;
	}

	@Override
	public void write(int b) throws IOException {
		// redirects data to the text area
		textArea.append(String.valueOf((char) b));
		// scrolls the text area to the end of data
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
}