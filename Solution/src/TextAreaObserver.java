import java.util.Observable;
import java.util.ArrayList;
import java.util.Observer;

/**
 * @author Firat Top - 101101047
 * @author Aysu Cesmeli - 101101012
 * @author Ibrahim Ihsan Taskiran - 101201001
 */

public class TextAreaObserver extends Observable {

	String text;
	ArrayList<Observer> liste = new ArrayList<Observer>();

	TextAreaObserver(String s) {
		text = s;
	}

	public void attach(Observer o) {
		liste.add(o);
	}

	public boolean isChanged(String s) {
		return !text.equalsIgnoreCase(s);
	}

	public void editRecord(String s) {
		if (isChanged(s)) {
			text = s;
			notifyObservers();
		}
	}

	public void notifyObservers() {
		for (Observer o : liste)
			o.update(this, text);
	}
}
