import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

/**
 * @author Firat Top - 101101047
 * @author Aysu Cesmeli - 101101012
 * @author Ibrahim Ihsan Taskiran - 101201001
 */

public class UndirectedGraph {
	public static Graph graph = new Graph();

	public static int verticenumber;
	public static int edgenumber;
	public static int button;
	public static Context context;
	public static TextAreaObserver textObserver;

	public static void main(String[] args) throws IOException {

		final JFrame frame = new JFrame("Bil211 Project");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JPanel panel = new JPanel();

		final JToolBar toolBar = new JToolBar();

		final JTextArea textArea = new JTextArea();

		final JSplitPane horizontalSplitPane1 = new JSplitPane(
				JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(textArea), panel);

		final JButton circularLayoutButton = new JButton("CIRCULAR");
		final JButton grup5LayoutButton = new JButton("GRUP5");
		final JButton bothButton = new JButton("BOTH");
		JButton updateButton = new JButton("UPDATE");

		toolBar.add(circularLayoutButton);
		toolBar.add(grup5LayoutButton);
		toolBar.add(bothButton);
		toolBar.add(updateButton);

		panel.add(toolBar, BorderLayout.PAGE_START);
		frame.add(horizontalSplitPane1, BorderLayout.CENTER);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		textArea.setLineWrap(true);
		horizontalSplitPane1.setDividerLocation(130);

		BufferedReader txt = new BufferedReader(new FileReader("example2.txt"));

		String row = "";
		row = txt.readLine();
		verticenumber = Integer.parseInt(row);
		textArea.append(row + "\n");
		row = txt.readLine();

		for (int i = 0; i < verticenumber; i++) {
			textArea.append(row + "\n");
			Vertice vertice = new Vertice(row);
			graph.insertVertice(vertice);
			row = txt.readLine();
		}

		textArea.append(row + "\n");
		edgenumber = Integer.parseInt(row);
		row = txt.readLine();

		for (int i = 0; i < edgenumber; i++) {
			textArea.append(row + "\n");
			String x = row.substring(0, row.indexOf(" "));
			String y = row.substring(row.indexOf(" ") + 1);

			Vertice vertice1 = null;
			Vertice vertice2 = null;
			for (int k = 0; k < verticenumber; k++) {
				if (x.equals(graph.getVerticeList().get(k).getName()))
					vertice1 = graph.getVerticeList().get(k);
				if (y.equals(graph.getVerticeList().get(k).getName()))
					vertice2 = graph.getVerticeList().get(k);
			}
			graph.createEdge(vertice1, vertice2);
			row = txt.readLine();

		}

		textObserver = new TextAreaObserver(textArea.getText());
		circularLayoutButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel label = new JLabel("Circular Layout");
				JPanel CircularPanel = new JPanel(new BorderLayout());

				CircularPanel.add(toolBar, BorderLayout.PAGE_START);
				CircularPanel.add(label, BorderLayout.BEFORE_FIRST_LINE);

				horizontalSplitPane1.setBottomComponent(CircularPanel);
				context = new Context(new CircularLayout());
				context.drawStrategy(graph, CircularPanel);

				button = 1;

			}
		});

		grup5LayoutButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel label = new JLabel("Grup5 Layout");
				JPanel Grup5Panel = new JPanel(new BorderLayout());

				Grup5Panel.add(toolBar, BorderLayout.PAGE_START);
				Grup5Panel.add(label, BorderLayout.BEFORE_FIRST_LINE);

				horizontalSplitPane1.setBottomComponent(Grup5Panel);

				context = new Context(new Grup5Layout());
				context.drawStrategy(graph, Grup5Panel);
				button = 2;

			}
		});

		bothButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel label1 = new JLabel("Circular Layout");
				JLabel label2 = new JLabel("Grup5 Layout");
				JPanel mainPanel = new JPanel(new BorderLayout());
				JPanel CircularPanel = new JPanel(new BorderLayout());
				JPanel Grup5Panel = new JPanel(new BorderLayout());

				mainPanel.add(toolBar, BorderLayout.PAGE_START);
				CircularPanel.add(label1, BorderLayout.NORTH);
				Grup5Panel.add(label2, BorderLayout.NORTH);
				JSplitPane horizontalSplitPane2 = new JSplitPane(
						JSplitPane.HORIZONTAL_SPLIT, CircularPanel, Grup5Panel);

				mainPanel.add(horizontalSplitPane2, BorderLayout.CENTER);
				horizontalSplitPane1.setBottomComponent(mainPanel);
				horizontalSplitPane2.setDividerLocation(600);

				context = new Context(new CircularLayout());
				context.drawStrategy(graph, CircularPanel);

				context = new Context(new Grup5Layout());
				context.drawStrategy(graph, Grup5Panel);

				button = 3;

			}
		});

		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String s = textArea.getText();
				if (textObserver.isChanged(s)) {
					Graph newGraph = new Graph();

					String[] lines = textArea.getText().split("\\n");

					int newVerticeNumber = Integer.parseInt(lines[0]);
					for (int i = 1; i < newVerticeNumber + 1; i++) {
						Vertice vertice = new Vertice(lines[i]);
						newGraph.insertVertice(vertice);
					}

					int newEdgeNumber = Integer
							.parseInt(lines[newVerticeNumber + 1]);
					for (int i = 0; i < newEdgeNumber; i++) {

						String x = lines[newVerticeNumber + 2 + i].substring(0,
								lines[newVerticeNumber + 2 + i].indexOf(" "));
						String y = lines[newVerticeNumber + 2 + i]
								.substring(lines[newVerticeNumber + 2 + i]
										.indexOf(" ") + 1);

						Vertice newVertice1 = null;
						Vertice newVertice2 = null;

						for (int k = 0; k < newVerticeNumber; k++) {
							if (x.equals(newGraph.getVerticeList().get(k)
									.getName()))
								newVertice1 = newGraph.getVerticeList().get(k);
							if (y.equals(newGraph.getVerticeList().get(k)
									.getName()))
								newVertice2 = newGraph.getVerticeList().get(k);
						}
						newGraph.createEdge(newVertice1, newVertice2);

						graph = newGraph;
						verticenumber = newVerticeNumber;
						edgenumber = newEdgeNumber;

					}
					if (button == 1)
						circularLayoutButton.doClick(0);
					else if (button == 2)
						grup5LayoutButton.doClick(0);
					else if (button == 3)
						bothButton.doClick(0);

				}

			}

		});

	}
}
