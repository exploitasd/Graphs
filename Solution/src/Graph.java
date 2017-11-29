import java.util.ArrayList;

/**
 * @author Firat Top - 101101047
 * @author Aysu Cesmeli - 101101012
 * @author Ibrahim Ihsan Taskiran - 101201001
 */

public class Graph {

	private ArrayList<Vertice> verticeList;

	public Graph() {
		verticeList = new ArrayList<Vertice>();
	}

	public void insertVertice(Vertice vertice) {
		verticeList.add(vertice);
	}

	public void createEdge(Vertice v, Vertice w) {
		if (verticeList.contains(v) && verticeList.contains(w)) {
			Edge edge = new Edge(v, w);
			v.getAdjList().add(edge);

			Edge edge2 = new Edge(w, v);
			w.getAdjList().add(edge2);
		} else
			System.out.println("Vertice'ler cizgede yok!");
	}

	public ArrayList<Vertice> getVerticeList() {
		return verticeList;
	}
}
