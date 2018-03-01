package sci.modules;

import java.util.ArrayList;

import sci.SCI;
import scilib.objects.Group;
import scilib.objects.TeamData;

/**
 * A default module which allows the user to view TeamData objects.
 * @author Auxiliatrix
 *
 */
public class AccessModule implements Module {

	/*
	 * Creates a TeamAccessModule.
	 */
	public AccessModule() {}
	
	@Override
	public String getInvoker() {return "get";}

	@Override
	public String getName() {return "AccessModule";}

	@Override
	public String getHelpDoc() {
		String response = "";
		response += "___[Get] Usage___";
		response += "\nThe 'get' command lets you access and view information about teams and groups.";
		response += "\n\tget team\tReturns a list of teams.";
		response += "\n\tget team [name]\tReturns information about the given team.";
		response += "\n\tget group\tReturns a list of groups.";
		response += "\n\tget group [name]\tReturns information about the given group.";
		response += "\n------------------";
		return response;
	}

	@Override
	public String process(String line, ArrayList<String> tokens) {
		String response = "";
		if( tokens.size() == 0 ) {
			response = "\nYou must specify a team or group.";
		} else {
			String type = tokens.get(0);
			if( type.equalsIgnoreCase("team") || type.equalsIgnoreCase("teams") ) {
				if( tokens.size() == 1 ) {
					for( String team : SCI.teamAccessMap.keySet() ) {
						response += "Here is a list of teams:";
						response += "\t" + team + "\n";
					}
				} else {
					String teamName = tokens.get(1);
					TeamData td = SCI.teamAccessMap.get(teamName.toLowerCase());
					if( td == null ) {
						response = "You must enter a valid team name.";
					} else {
						response = td.toString();
					}
				}
			} else if( type.equalsIgnoreCase("group") || type.equalsIgnoreCase("groups") ) {
				if( tokens.size() == 1 ) {
					for( String group : SCI.groups.keySet() ) {
						response += "Here is a list of groups:";
						response += "\t" + group + "\n";
					}
				} else {
					String groupName = tokens.get(1);
					Group g = SCI.groups.get(groupName.toLowerCase());
					if( g == null ) {
						response = "You must enter a valid group name.";
					} else {
						response = g.toString();
					}
				}
			} else {
				response = "You must specify a valid object type.";
			}
		}
		return response;
	}

}
