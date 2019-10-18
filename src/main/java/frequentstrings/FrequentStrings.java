package frequentstrings;

import java.util.*;
import java.util.TreeSet;

public class FrequentStrings {
	ArrayList<String> fs = new ArrayList<>();

	public FrequentStrings() {
		fs = new ArrayList<>();
	}

	public FrequentStrings(String[] seed) {
		for (String s : seed) {
			this.add(s);
		}
	}

	public void add(String s) {
		fs.add(s);
	}

	public void remove(String s) {
		for (int i = 0; i < fs.size(); i++) {
			if (fs.get(i) == s) {
				fs.remove(i);
				break;
			}
		}
	}

	public List<String> stringsSorted() {
		List<String> returned = new ArrayList<>();
		for (int i = 0; i < fs.size(); i++) {
			if (!(returned.contains(fs.get(i)))) {
				returned.add(fs.get(i));
			}
		}
		Collections.sort(returned);
		return returned;
	}

	public String getMode() throws EmptyObjectException {
		int count = 0;
		int max = 0;
		String most = "";
		HashMap<String,Integer> keysvalues = toHashMap(fs);

		for (int i = 0; i < fs.size(); i++) {
			String few = fs.get(i);
			count = keysvalues.get(few);
			if (count > max) {
				max = count;
				most = fs.get(i);
			}
		}
		if (max == 0) {
			throw new EmptyObjectException();
		}
		return most;
	}

	private HashMap toHashMap(ArrayList<String> list) {
		HashMap <String, Integer> map = new HashMap<>();
		for (int i = 0; i < list.size(); i++) {
			if (map.containsKey(list.get(i))) {
				map.put(list.get(i), (map.get(list.get(i))) + 1);
			} else {
				map.put(list.get(i), 1);
			}
		}
		return map;
	}

	public boolean similar(FrequentStrings other) {
		HashMap keysvalues1 = toHashMap(fs);
		ArrayList<String> otherarray = new ArrayList<>();
		HashMap keysvalues2 = toHashMap(other.fs);
		if (keysvalues1.size() == keysvalues2.size()) {
			for (int i = 0; i < fs.size(); i++) {
				if (!keysvalues2.containsKey(fs.get(i))) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	public List<String> sortedByFrequency() {
		String s = new String();
		HashMap<String,Integer> keysvalues = toHashMap(fs);
		List<String> listreturned = new ArrayList<>();
		for (int j = 0; j < fs.size(); j++) {
			int max = 0;
			for (int i = 0; i < fs.size(); i++) {
			if (!keysvalues.containsKey(fs.get(i))) {

			} else if (keysvalues.get(fs.get(i)) > max) {
				max = keysvalues.get(fs.get(i));
				s = fs.get(i);
			}
		}
	listreturned.add(s);
	keysvalues.remove(s);
}
		return listreturned;
	}

	public int getCount(String s) {
		int count = 0;
		HashMap<String,Integer> keysvalues = toHashMap(fs);
		if (keysvalues.containsKey(s)) {
			count = keysvalues.get(s);
		}
		return count;
	}

	public boolean contains(String s) {
		HashMap<String,Integer> keysvalues = toHashMap(fs);
		return (keysvalues.containsKey(s));
	}

	public boolean equals(Object other) {
		FrequentStrings s = (FrequentStrings) other;
		HashMap<String,Integer> keysvalues1 = toHashMap(this.fs);
		HashMap<String,Integer> keysvalues2 = toHashMap(s.fs);
		if (keysvalues1.size() == keysvalues2.size()) {
			for (int i = 0; i < this.fs.size(); i++) {
				if (!keysvalues1.get(fs.get(i)).equals(keysvalues2.get(fs.get(i)))) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	public int hashCode() {
		int hashcode;
		hashcode = this.fs.size();
		for (int i = 0; i < fs.size(); i++) {
			hashcode += fs.get(i).hashCode();
		}
		return hashcode;
	}

}
