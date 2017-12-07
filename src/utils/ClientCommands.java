package utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class ClientCommands {
	private ClientCommands() {}
	
	// ######FROM CLIENT USER TO SERVER (START WITH "/")######
	// Client logt sich mit Benutzernamen ein: <timecode> LOGIN <username>
	public static final String LOGIN = "LOGIN";
	// Client fragt an, welche Chatraueme es gibt: <timecode> LIST
	public static final String LIST = "LIST";
	// Client waehlt Chatraum aus: <timecode> JOIN <chatroomname>
	public static final String JOIN = "JOIN";
	// Client schreibt eine Nachricht in den Chatraum: <timecode> SEND <message>
	public static final String SEND = "SEND";

	// Client will den Chatroom verlassen: <timecode> LEAVE
	public static final String LEAVE = "LEAVE";

	// Client fordert vom Server m�glichst alle Nachrichten an, die j�ngger sind als
	// timecodeVonNachricht
	// <timecode> GET timecodeVonNachricht -> SErver antwortet mit "SERVER SEND
	// oldestMsg.....SERVER SEND newestMsg
	public static final String GET = "GET";

	// QUIT USER??! <timecode> QUIT

	// Client fragt an, welche User sich im selben Raum befinden: <timecode> USERS
	public static final String USERS = "USERS";
	// Client versucht, CHatraum zu erstellen (nur wenn es diesen ncoh nicht gibt):
	// <timecode> CREATE <chatroomname>
	public static final String CREATE_ROOM = "CREATE";

	public static final String QUIT = "QUIT";
	
	
	
	// 
	/**
	 * LIST OF ALL CLIENT COMMANDS. <br> Is automatically updated with reflections to ClientCommand.class.
	 */
	public static final List<String> CLIENT_COMMAND_LIST = getClientCommandList();
		
	private static List<String> getClientCommandList(){
		
		List<String> valueList = new ArrayList<>();
		
	
		Field[] allFields = ClientCommands.class.getFields();
		
		for(Field f : allFields) {
			try {
				String val = (String)f.get(null);
				if(val != null) {
					valueList.add(val);
				}
				
				
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return valueList;
	}
	
	
}
