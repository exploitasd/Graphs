import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * @author Firat Top - 101101047
 * @author Aysu Cesmeli - 101101012
 * @author Ibrahim Ihsan Taskiran - 101201001
 */

public class CircularLayout implements Observer, Strategy {

	@Override
	public void draw(final Graph graph, JPanel panel) {
		panel.add(new JComponent() {

			public void paintComponent(Graphics g) {

				int verticeNumber = graph.getVerticeList().size();
				int radius = verticeNumber * 15;
				Point centre = new Point(300, 330);

				double angle = Math.toRadians(360 / verticeNumber);
				ArrayList<Point> points = new ArrayList<Point>();

				for (int i = 0; i < verticeNumber; i++) {
					double theta = i * angle;
					int dx = (int) (radius * Math.sin(theta));
					int dy = (int) (-radius * Math.cos(theta));
					Point coordinate = new Point(centre.x + dx, centre.y + dy);
					points.add(coordinate);
				}

				int verticeCounter = 0;
				for (Point point : points) {
					String text = graph.getVerticeList().get(verticeCounter)
							.toString();
					FontMetrics fm = g.getFontMetrics();
					double textWidth = fm.getStringBounds(text, g).getWidth();
					g.setColor(Color.BLACK);
					g.drawString(text, (int) (point.x - textWidth / 2),
							(int) (point.y + fm.getMaxAscent() / 2));

					int ovalWidth = ((graph.getVerticeList()
							.get(verticeCounter).toString()).length()) * 3 + 30;
					int ovalHeight = ovalWidth;
					g.drawOval(point.x - ovalWidth / 2, point.y - ovalHeight
							/ 2, ovalWidth, ovalHeight);

					for (int i = 0; i < graph.getVerticeList()
							.get(verticeCounter).getAdjList().size(); i++) {
						int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
						for (int k = 0; k < graph.getVerticeList().size(); k++) {
							if (graph.getVerticeList().get(verticeCounter)
									.getAdjList().get(i).getEnd() == graph
									.getVerticeList().get(k)) {
								x2 = points.get(k).x;
								y2 = points.get(k).y;
							}

							if (graph.getVerticeList().get(verticeCounter)
									.getAdjList().get(i).getStart() == graph
									.getVerticeList().get(k)) {
								x1 = points.get(k).x;
								y1 = points.get(k).y;
							}
						}
						g.drawLine(x1, y1, x2, y2);
					}

					verticeCounter++;
				}
			}
		});

	}

	@Override
	public void update(Observable o, Object arg) {

	}

}