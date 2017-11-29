/**
 * @author Firat Top - 101101047
 * @author Aysu Cesmeli - 101101012
 * @author Ibrahim Ihsan Taskiran - 101201001
 */

public class Edge {

	private Vertice start;
	private Vertice end;

	public Edge(Vertice start, Vertice end) {
		this.end = end;
		this.start = start;
	}

	public Vertice getStart() {
		return start;
	}

	public Vertice getEnd() {
		return end;
	}

	public String toString() {
		return start + " - " + end;
	}

}
