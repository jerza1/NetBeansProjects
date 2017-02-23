package util;

import java.util.TreeMap;
import java.util.Vector;

/**
 * Team 3
 * @author Héctor J. Vásquez
 * @author René Egremy
 * @author Luis Cruz
 * @version 1.0
 */

public class HelpingSet implements Comparable<HelpingSet> {
	public boolean hasStrings;
	private String realName;// The name the subSet would have in our new AFD.
	private Vector<String> reductions;
	private Vector<String> stringsIn;// The states contained in this subSet.
	private TreeMap<String, HelpingSet> transitions = new TreeMap<String, HelpingSet>();

	private HelpingSet() {
		hasStrings = false;
		realName = "";
		stringsIn = new Vector<String>();
	}

	public HelpingSet(String s) {
		// This is the main constructor.
		realName = "1";
		stringsIn = new Vector<String>();
		hasStrings = true;
		stringsIn.add(s);
	}

	public void add(HelpingSet set) {
		// In case it isn't an Integer, this method will split an add one by one
		// every state.
		for (String s : set.stringsIn)
			if (!stringsIn.contains(s))
				stringsIn.add(s);
		hasStrings = true;
	}

	// Methods:
	public void add(String s) {
		// This method will add the state s to subSet.}
		if (!stringsIn.contains(s))
			stringsIn.add(s);
		hasStrings = true;
	}

	public void addReduction(String r) {
		if (reductions == null)
			reductions = new Vector<String>();
		reductions.add(r);
	}

	/**
	 * Adds a transition to state so makes easier the syntax table construction.
	 * 
	 * @param s
	 *            The token that leads to next state.
	 * @param set
	 *            The set leading by next state.
	 */
	public void addTransition(String s, HelpingSet set) {
		transitions.put(s, set);
	}

	@Override
	public int compareTo(HelpingSet h) {
		return this.stringsIn.toString().compareTo(h.stringsIn.toString());
	}

	public boolean contains(String s) {
		return stringsIn.contains(s);
	}

	public boolean containsReductions() {
		if (reductions != null)
			return true;
		for (String s : stringsIn)
			if (s.endsWith("@"))
				return true;
		return false;
	}

	@Override
	public boolean equals(Object o) {
		HelpingSet hs = (HelpingSet) o;
		return stringsIn.equals(hs.stringsIn);
	}

	public Vector<String> getReductions() {
		return reductions;
	}

	public TreeMap<String, HelpingSet> getTransitions() {
		return transitions;
	}

	public HelpingSet goTo(String transition) {
		// We could call this method the move function.
		HelpingSet out = new HelpingSet();

		if (transition.equals("$")) {
			out.add("Aceptación @");
			out.setRealName("Aceptación");
		}

		for (String s : stringsIn) {
			if (s.contains("Aceptación"))
				break;
			if (s.endsWith("@"))
				continue;

			boolean transitionValid = false;
			String[] aux = s.split(" ");
			for (int i = 1; i < aux.length - 1; i++)
				if (aux[i].equals("@") && aux[i + 1].equals(transition)) {
					String tmp = aux[i];
					aux[i] = aux[i + 1];
					aux[i + 1] = tmp;
					transitionValid = true;
					break;
				}
			if (transitionValid) {
				String tmp = aux[0];
				for (int i = 1; i < aux.length; i++)
					tmp += " " + aux[i];
				out.add(tmp);
			}
		}

		return out;
	}

	public String realName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String statesInNames() {
		return stringsIn.toString();
	}

	public Vector<String> stringsIn() {
		// This method return an array with the states contained in this subSet.
		return stringsIn;
	}

	@Override
	public String toString() {
		if (realName.equals("Aceptación"))
			return realName;
		return realName + "-" + stringsIn;
	}
}
