import java.util.ArrayList;

/**
 * @author Firat Top - 101101047
 * @author Aysu Cesmeli - 101101012
 * @author Ibrahim Ihsan Taskiran - 101201001
 */

public class Vertice {

	private ArrayList<Edge> adjList;
	private String name;

	public Vertice(String name) {
		this.name = name;
		this.adjList = new ArrayList<Edge>();
	}

	public String getName() {
		return name;
	}

	public ArrayList<Edge> getAdjList() {
		return adjList;
	}

	public String toString() {
		return this.name;
	}

}
