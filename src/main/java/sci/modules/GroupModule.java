package sci.modules;

import java.util.ArrayList;

import sci.SCI;
import scilib.checkers.Checker;
import scilib.checkers.Condition;
import scilib.objects.Group;
import scilib.objects.Sorter;

/**
 * Default module for managing {@link Group} objects.
 * @author Auxiliatrix
 *
 */
public class GroupModule implements Module {

	/**
	 * Creates a GroupModule.
	 */
	public GroupModule() {}
	
	@Override
	public String[] getInvokers() {return new String[]{"group","grp"};}

	@Override
	public String getName() {return "Group Module";}

	@Override
	public String getHelpDoc() {
		String response = "";
		response += "[Group] Usage:";
		response += "\nThe 'group' or 'grp' command allows you to manage Groups.";
		response += "\n\tgroup\t\t\t\t\t\tLists existing Groups.";
		response += "\n\tgroup [name]\t\t\t\t\tReturns information about the given team";
		response += "\n\t\t-n, --new\t\t\t\tCreates a Group.";
		response += "\n\t\t-c, --copy <Group>\t\t\tCreates a Group as a copy of another.";
		response += "\n\t\t-d, --delete\t\t\t\tDeletes a Group.";
		response += "\n\t\t-s, --sort <DataType>\t\t\tSorts a Group by the given DataType.";
		response += "\n\t\t-i, --sort-increasing <DataType>\tSorts a Group in increasing order instead of in decreasing order.";
		response += "\n\t\t--add-filter <Filter>\t\t\tAdds a Filter to the Group.";
		response += "\n\t\t--rem-filter <Filter>\t\t\tRemoves a Filter from the Group.";
		response += "\n\t\t-a, --add <Team>\t\t\tAdds a Team to the Group.";
		response += "\n\t\t-r, --remove <Team>\t\t\tRemoves a Team from the Group.";
		response += "\n\t\t-m, --merge <Group>\t\t\tAdds Teams to a Group that are in another Group.";
		response += "\n\t\t-e -- extract <Group>\t\t\tRemoves Teams from a Group that are in another Group.";
		response += "\n";
		return response;
	}

	@Override
	public String process(String line, ArrayList<String> tokens) {
		String response = "";
		if( tokens.size() == 0 ) {
			response += "Here is a list of groups:";
			for( String group : SCI.groups.keySet() ) {
				response += "\n\t" + group;
			}
		} else {
			String groupName = "";
			for( int f=0; f < tokens.size(); f++ ) {
				String token = tokens.get(f);
				if( !token.startsWith("-") ) {
					groupName = token;
					tokens.remove(f);
					break;
				} else if( token.equalsIgnoreCase("-c") || token.equalsIgnoreCase("--copy") || token.equalsIgnoreCase("-s") || token.equalsIgnoreCase("--sort") || token.equalsIgnoreCase("-i") || token.equalsIgnoreCase("--sort-increasing") ||  token.equalsIgnoreCase("--add-filter") || token.equalsIgnoreCase("--rem-filter") || token.equalsIgnoreCase("-a") || token.equalsIgnoreCase("--add") || token.equalsIgnoreCase("-r") || token.equalsIgnoreCase("--rem") || token.equalsIgnoreCase("-m") || token.equalsIgnoreCase("--merge") || token.equals("-e") || token.equals("--extract") ) {
					f++;
				}
			}
			if( groupName.isEmpty() ) {
				response = "Error: Missing Group name.";
			} else {
				boolean flagged = false;
				for( String token : tokens ) {
					if( token.startsWith("-") ) {
						flagged = true;
						int index = tokens.indexOf(token);
						if( token.equalsIgnoreCase("-n") || token.equalsIgnoreCase("--new") || token.equalsIgnoreCase("-d") || token.equalsIgnoreCase("--delete") ) {
							if( token.equalsIgnoreCase("-n") || token.equalsIgnoreCase("--new") ) {
								if( SCI.groups.containsKey(groupName.toLowerCase()) ) {
									if( SCI.groups.get(groupName.toLowerCase()).immutable ) {
										response = "Error: Group '" + groupName + "' already exists, and is immutable.";
										break;
									}
									String confirm = "";
									while( !(confirm.equals("y") || confirm.equals("yes") || confirm.equals("n") || confirm.equals("no")) ) {
										System.out.println("Warning: Group '" + groupName + "' already exists. Are you sure you want to overwrite it? (y/n)");
										confirm = SCI.sc.nextLine();
									}
									if( confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("y") ) {
										SCI.groups.put(groupName.toLowerCase(), new Group(groupName.toLowerCase()));
										response = "Created new Group '" + groupName + "'";
									} else {
										response = "Cancelled operation.";
									}
								} else {
									SCI.groups.put(groupName.toLowerCase(), new Group(groupName.toLowerCase()));
									response = "Created new Group '" + groupName + "'";
								}
								break;
							} else if( token.equalsIgnoreCase("-d") || token.equalsIgnoreCase("--delete") ) {
								if( SCI.groups.containsKey(groupName.toLowerCase()) ) {
									if( SCI.groups.get(groupName.toLowerCase()).immutable ) {
										response = "Error: Group '" + groupName + "' is immutable.";
										break;
									}
									SCI.groups.remove(groupName.toLowerCase());
									response = "Deleted Group '" + groupName + "'";
								} else {
									response = "Error: Group '" + groupName + "' does not exist.";
								}
								break;
							}
						}
						else if( token.equalsIgnoreCase("-c") || token.equalsIgnoreCase("--copy") || token.equalsIgnoreCase("-s") || token.equalsIgnoreCase("--sort") || token.equalsIgnoreCase("-i") || token.equalsIgnoreCase("--sort-increasing") || token.equalsIgnoreCase("--add-filter") || token.equalsIgnoreCase("--rem-filter") || token.equalsIgnoreCase("-a") || token.equalsIgnoreCase("--add") || token.equalsIgnoreCase("-r") || token.equalsIgnoreCase("--rem") || token.equalsIgnoreCase("-m") || token.equalsIgnoreCase("--merge") || token.equalsIgnoreCase("-e") || token.equalsIgnoreCase("--extract") ) {
							if( tokens.size() < index + 2 ) {
								response = "Error: Missing argument for flag '" + token + "'";
								break;
							} else {
								tokens.remove(index);
								String argument = tokens.remove(index);
								if( token.equalsIgnoreCase("-c") || token.equalsIgnoreCase("--copy") ) {
									if( SCI.groups.containsKey(groupName.toLowerCase()) ) {
										if( SCI.groups.get(groupName.toLowerCase()).immutable ) {
											response = "Error: Group '" + groupName + "' already exists, and is immutable.";
											break;
										}
										if( !SCI.groups.keySet().contains(argument.toLowerCase()) ) {
											response = "Error: Group '" + argument + "' does not exist.";
											break;
										}
										String confirm = "";
										while( !(confirm.equals("y") || confirm.equals("yes") || confirm.equals("n") || confirm.equals("no")) ) {
											System.out.println("Warning: Group '" + groupName + "' already exists. Are you sure you want to overwrite it? (y/n)");
											confirm = SCI.sc.nextLine();
										} if( confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("y") ) {
											SCI.groups.put(groupName.toLowerCase(), new Group(groupName.toLowerCase(), SCI.groups.get(argument.toLowerCase())));
											response = "Created new Group '" + groupName + "' as a copy of '" + argument + "'";
										} else {
											response = "Cancelled operation.";
										}
									} else {
										if( !SCI.groups.keySet().contains(argument.toLowerCase()) ) {
											response = "Error: Group '" + argument + "' does not exist.";
											break;
										}
										SCI.groups.put(groupName.toLowerCase(), new Group(groupName.toLowerCase(), SCI.groups.get(argument.toLowerCase())));
										response = "Created new Group '" + groupName + "' as a copy of '" + argument + "'";
									}
									break;
								} else if( token.equalsIgnoreCase("-s") || token.equalsIgnoreCase("--sort") ) {
									if( !SCI.groups.containsKey(groupName.toLowerCase()) ) {
										response = "Error: Group '" + groupName + "' does not exist.";
										break;
									}
									if( !SCI.configuration.dataTypes.contains(argument.toLowerCase()) ) {
										response = "Error: Unrecognized data type '" + argument + "'";
										break;
									}
									SCI.groups.get(groupName.toLowerCase()).sorter = new Sorter(argument.toLowerCase());
									response = "Sorted Group '" + groupName + "' by data type '" + argument + "'";
									break;
								} else if( token.equalsIgnoreCase("-i") || token.equalsIgnoreCase("--sort-increasing") ) {
									if( !SCI.groups.containsKey(groupName.toLowerCase()) ) {
										response = "Error: Group '" + groupName + "' does not exist.";
										break;
									}
									if( !SCI.configuration.dataTypes.contains(argument.toLowerCase()) ) {
										response = "Error: Unrecognized data type '" + argument + "'";
										break;
									}
									SCI.groups.get(groupName.toLowerCase()).sorter = new Sorter(argument.toLowerCase(), true);
									response = "Sorted Group '" + groupName + "' by data type '" + argument + "' in increasing order";
									break;
								} else if( token.equalsIgnoreCase("--add-filter") ) {
									if( !SCI.groups.containsKey(groupName.toLowerCase()) ) {
										response = "Error: Group '" + groupName + "' does not exist.";
										break;
									}
									if( SCI.groups.get(groupName.toLowerCase()).immutable ) {
										response = "Error: Group '" + groupName + "' is immutable.";
										break;
									}
									Condition c = Condition.factory(argument.toLowerCase());
									if( c == null ) {
										response = "Error: Invalid Condition format.";
										break;
									}
									SCI.groups.get(groupName.toLowerCase()).filter.add(c);
									response = "Added filter '" + argument + "' to Group '" + groupName + "'";
									break;
								} else if( token.equalsIgnoreCase("--rem-filter") ) {
									if( !SCI.groups.containsKey(groupName.toLowerCase()) ) {
										response = "Error: Group '" + groupName + "' does not exist.";
										break;
									}
									if( SCI.groups.get(groupName.toLowerCase()).immutable ) {
										response = "Error: Group '" + groupName + "' is immutable.";
										break;
									}
									boolean found = false;
									for( Checker c : SCI.groups.get(groupName.toLowerCase()).filter.getCheckers() ) {
										if( c.toString().equals(argument.toLowerCase()) ) {
											found = true;
											SCI.groups.get(groupName.toLowerCase()).filter.remove(c);
											response = "Removed filter '" + argument + "' from Group '" + groupName + "'";
											break;
										}
									}
									if( !found ) {
										response = "Error: The filter you have enter does not match any of the filters in Group '" + groupName + "'.";
									}
									break;
								} else if( token.equalsIgnoreCase("-a") || token.equalsIgnoreCase("--add") ) {
									if( !SCI.groups.containsKey(groupName.toLowerCase()) ) {
										response = "Error: Group '" + groupName + "' does not exist.";
										break;
									}
									if( SCI.groups.get(groupName.toLowerCase()).immutable ) {
										response = "Error: Group '" + groupName + "' is immutable.";
										break;
									}
									if( !SCI.teamAccessMap.keySet().contains(argument.toLowerCase()) ) {
										response = "Error: Team '" + argument + "' does not exist.";
										break;
									}
									if( SCI.groups.get(groupName.toLowerCase()).getTeamList().contains(SCI.teamAccessMap.get(argument.toLowerCase()))) {
										response = "Error: Group '" + groupName + "' already contains team '" + argument + "'.";
										break;
									}
									SCI.groups.get(groupName.toLowerCase()).add(SCI.teamAccessMap.get(argument.toLowerCase()));
									response = "Added team '" + argument + "' to Group '" + groupName + "'";
									break;
								} else if( token.equalsIgnoreCase("-r") || token.equalsIgnoreCase("--rem") ) {
									if( !SCI.groups.containsKey(groupName.toLowerCase()) ) {
										response = "Error: Group '" + groupName + "' does not exist.";
										break;
									}
									if( SCI.groups.get(groupName.toLowerCase()).immutable ) {
										response = "Error: Group '" + groupName + "' is immutable.";
										break;
									}
									if( !SCI.teamAccessMap.keySet().contains(argument.toLowerCase()) ) {
										response = "Error: Team '" + argument + "' does not exist.";
										break;
									}
									if( !SCI.groups.get(groupName.toLowerCase()).getTeamList().contains(SCI.teamAccessMap.get(argument.toLowerCase()))) {
										response = "Error: Group '" + groupName + "' does not contains team '" + argument + "'.";
										break;
									}
									SCI.groups.get(groupName.toLowerCase()).remove(SCI.teamAccessMap.get(argument.toLowerCase()));
									response = "Removed team '" + argument + "' to Group '" + groupName + "'";
									break;
								} else if( token.equalsIgnoreCase("-m") || token.equalsIgnoreCase("--merge") ) {
									if( !SCI.groups.containsKey(groupName.toLowerCase()) ) {
										response = "Error: Group '" + groupName + "' does not exist.";
										break;
									}
									if( !SCI.groups.containsKey(argument.toLowerCase()) ) {
										response = "Error: Group '" + argument + "' does not exist.";
										break;
									}
									if( SCI.groups.get(groupName.toLowerCase()).immutable ) {
										response = "Error: Group '" + groupName + "' is immutable.";
										break;
									}
									SCI.groups.get(groupName.toLowerCase()).addAll(SCI.groups.get(argument.toLowerCase()).getTeamPool());
									response = "Added teams from Group '" + argument + "' into Group '" + groupName + "'";
									break;
								} else if( token.equalsIgnoreCase("-e") || token.equalsIgnoreCase("--extract") ) {
									if( !SCI.groups.containsKey(groupName.toLowerCase()) ) {
										response = "Error: Group '" + groupName + "' does not exist.";
										break;
									}
									if( !SCI.groups.containsKey(argument.toLowerCase()) ) {
										response = "Error: Group '" + argument + "' does not exist.";
										break;
									}
									if( SCI.groups.get(groupName.toLowerCase()).immutable ) {
										response = "Error: Group '" + groupName + "' is immutable.";
										break;
									}
									SCI.groups.get(groupName.toLowerCase()).removeAll(SCI.groups.get(argument.toLowerCase()).getTeamPool());
									response = "Removed teams in Group '" + argument + "' from Group '" + groupName + "'";
									break;
								}
							}
						} else {
							response = "Error: Unrecognized flag '" + token + "'";
							break;
						}
					}
				}
				if( !flagged ) {
					if( !SCI.groups.containsKey(groupName.toLowerCase()) ) {
						response = "Error: Group '" + groupName + "' does not exist.";
					} else {
						Group g = SCI.groups.get(groupName.toLowerCase());
						response = g.toString();
					}
				}
			}
		}
		return response;
	}

}
