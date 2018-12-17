package shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InterestList implements Serializable {
	private List<Interest> interests;

	public InterestList() {
		interests = new ArrayList<Interest>();
	}
	
	public InterestList(List<Interest> interests) {
		this.interests = (List<Interest>)interests;
	}

	public Interest get(int index) {
		if (index < interests.size()) {
			return interests.get(index);
		} else {
			return null;
		}
	}

	public void add(Interest interest) {
		interests.add(interest);
	}

	@Override
	public String toString() {
		return "InterestList [interests=" + interests + "]";
	}

	public int size() {
		return interests.size();
	}
	
	public Interest[] getArrayOfInterests() {
		Interest[] array = new Interest[size()];
		interests.toArray(array);
		return array;
	}
}
