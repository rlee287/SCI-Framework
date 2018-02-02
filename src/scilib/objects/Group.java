package scilib.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import scilib.checkers.Filter;
import scilib.utilities.TeamDataComparator;

public class Group {
	
	public String name;
	private ArrayList<TeamData> originalList;
	private ArrayList<TeamData> currentList;
	public Sorter sorter;
	public Filter filter;
	
	public Group(String name) {
		this(name, new ArrayList<TeamData>());
	}
	
	public Group(String name, ArrayList<TeamData> list) {
		this.name = name;
		this.originalList = new ArrayList<TeamData>(list);
		this.currentList = new ArrayList<TeamData>(list);
		this.sorter = new Sorter();
		this.filter = new Filter();
	}
	
	public boolean addAll(Collection<TeamData> teamDataCollection) {
		boolean success = false;
		for( TeamData td : teamDataCollection ) {
			success |= add(td);
		}
		return success;
	}
	
	public boolean add(TeamData td) {
		if( !originalList.contains(td) ) {
			originalList.add(td);
			return true;
		}
		return false;
	}
	
	public boolean remove(TeamData td) {
		if( originalList.contains(td) ) {
			originalList.remove(td);
			return true;
		}
		return false;
	}
	
	public void set(ArrayList<TeamData> teamDataList) {
		originalList = new ArrayList<TeamData>(teamDataList);
	}
	
	public void reset() {
		sorter = new Sorter();
		filter.reset();
	}
	
	public void clear() {
		originalList = new ArrayList<TeamData>();
	}
	
	public ArrayList<TeamData> getTeamList() {
		update();
		return new ArrayList<TeamData>(currentList);
	}
	
	public ArrayList<TeamData> getTeamPool() {
		return new ArrayList<TeamData>(originalList);
	}
	
	public boolean teamListContains(TeamData td) {
		return currentList.contains(td);
	}
	
	public boolean teamPoolContains(TeamData td) {
		return originalList.contains(td);
	}
	
	private void update() {
		ArrayList<TeamData> newList = new ArrayList<TeamData>();
		for( TeamData td : originalList ) {
			if(filter.check(td)) {
				newList.add(td);
			}
		}
		TeamData[] newArray = newList.toArray(new TeamData[newList.size()]);
		Arrays.sort(newArray, new TeamDataComparator(sorter));
		currentList = new ArrayList<TeamData>(Arrays.asList(newArray));
	}
}
