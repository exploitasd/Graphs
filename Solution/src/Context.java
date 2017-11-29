import javax.swing.JPanel;

/**
 * @author Firat Top - 101101047
 * @author Aysu Cesmeli - 101101012
 * @author Ibrahim Ihsan Taskiran - 101201001
 */

public class Context {
	private Strategy strategy;

	public Context(Strategy strategy) {
		this.strategy = strategy;
	}

	public void drawStrategy(Graph graph, JPanel panel) {
		this.strategy.draw(graph, panel);
	}
}
