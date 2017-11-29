import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * @author Firat Top - 101101047
 * @author Aysu Cesmeli - 101101012
 * @author Ibrahim Ihsan Taskiran - 101201001
 */

public class Grup5Layout implements Observer, Strategy {

	@Override
	public void draw(final Graph graph, JPanel panel) {
		panel.add(new JComponent() {

			public void paintComponent(Graphics g) {
				// herbir köþenin nerede olduðunu tutmak için
				Point[] points = new Point[graph.getVerticeList().size()];
				// maxAdj maksimum komþuluk sayýsý
				int maxAdj = 0;
				for (int k = 0; k < graph.getVerticeList().size(); k++) {
					if (maxAdj < graph.getVerticeList().get(k).getAdjList()
							.size())
						maxAdj = graph.getVerticeList().get(k).getAdjList()
								.size();
				}

				for (int i = maxAdj; i > 0; i--) {
					int count = 0; // i tane komþuluðu olan kaç vertice var

					for (int k = 0; k < graph.getVerticeList().size(); k++) {
						if (i == graph.getVerticeList().get(k).getAdjList()
								.size())
							count++;
					}

					int count2 = 0; // ayný komþu sayýsýna sahip köþeler
									// arasýnda kaçýncý köþede olduðu

					for (int k = 0; k < graph.getVerticeList().size(); k++) {
						if (i == graph.getVerticeList().get(k).getAdjList()
								.size()) {
							count2++;
							Point p = new Point((575 / count) * (count2),
									(600 / maxAdj) * (maxAdj - i + 1));
							String text = graph.getVerticeList().get(k)
									.toString();
							FontMetrics fm = g.getFontMetrics();
							double textWidth = fm.getStringBounds(text, g)
									.getWidth();
							g.setColor(Color.BLACK);
							g.drawString(text, (int) (p.x - textWidth / 2),
									(int) (p.y + fm.getMaxAscent() / 2));

							points[k] = new Point();
							points[k] = p;

							int RectWidth = ((graph.getVerticeList().get(k)
									.toString()).length()) * 3 + 30;
							int RectHeight = 30;
							g.drawRect(p.x - RectWidth / 2, p.y - RectHeight
									/ 2, RectWidth, RectHeight);

						}
					}

				}
				for (int i = 0; i < graph.getVerticeList().size(); i++) {
					for (int k = 0; k < graph.getVerticeList().get(i)
							.getAdjList().size(); k++) {
						if (graph.getVerticeList().get(i).getAdjList().size() == graph
								.getVerticeList().get(i).getAdjList().get(k)
								.getEnd().getAdjList().size()) {
							int count3 = 0; // komþunun points dizisindeki
											// sýrasý
							for (int j = 0; j < graph.getVerticeList().size(); j++) {
								if (graph
										.getVerticeList()
										.get(j)
										.equals(graph.getVerticeList().get(i)
												.getAdjList().get(k).getEnd()))
									count3 = j;
							}
							int x1 = points[i].x;
							int x2 = points[count3].x;
							int y1 = points[i].y;

							g.drawArc((x1 + x2) / 2 - (x1 - x2) / 2, y1
									- (x1 - x2) / 2, (x1 - x2), (x1 - x2), 0,
									180);

						} else {
							int count3 = 0;
							for (int j = 0; j < graph.getVerticeList().size(); j++) {
								if (graph
										.getVerticeList()
										.get(j)
										.equals(graph.getVerticeList().get(i)
												.getAdjList().get(k).getEnd()))
									count3 = j;
							}
							int x1 = points[i].x;
							int x2 = points[count3].x;
							int y1 = points[i].y;
							int y2 = points[count3].y;

							g.drawLine(x1, y1, x2, y2);
						}
					}
				}

			}
		});

	}

	@Override
	public void update(Observable o, Object arg) {

	}
}