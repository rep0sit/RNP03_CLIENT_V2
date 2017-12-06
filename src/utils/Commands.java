package utils;

public final class Commands {
	private Commands() {
	}
	
	
	//#######NEUE SERVERBEFEHLE########################
		//FROM SERVER TO CLIENT
		public static final String SERVER_PREFIX = "SERVER";
		//Server grüßt nach dem Verbindungsaufbau: SERVER GREETINGS <message>
		public static final String GREETINGS = "GREETINGS";
		public static final String SERVER_GREETINGS = SERVER_PREFIX + " "+GREETINGS;
		//Server sendet eine im Chatraum geschriebene Nachricht: SERVER SEND <date user message>
		public static final String SERVER_SEND = SERVER_PREFIX + "SEND";
		//Server löscht chatraum: SERVER DELETE <chatroomname>
//		public static final String SERVER_DELETE = SERVER_PREFIX + "DELETE";
		public static final String DELETE = "DELETE";
		//Server informiert den CLient, dass er die Verbindung beendet: QUIT <message>
		public static final String QUIT = "QUIT";
		
		public static final String LOGIN = "LOGIN";
		public static final String SEND = "SEND";
		
		//POSITIVE RÜCKMELDUNG VOM SERVER  <timecode> BEFEHL...
		//Antwort vom Server an Client bei erfolgreicher Anmeldung des CLients: <timecode> LOGIN SUCCESS <message>
		public static final String LOGIN_SUCCESS = "LOGIN SUCCESS";
		//Antwort vom Server an Client bei Anfrage,w as es für Chatraeume gibt: 
		//<timecode> LIST START "\n" LIST KAtzenSindHUnde "\n" LIST..... "\n" LIST END
		public static final String LIST_START = "LIST START";
		public static final String LIST_END = "LIST END";
		//Antwort vom Server bei erfolgreichem Chatroom-Wechsel: <timecode> JOIN SUCCESS
		public static final String JOIN_SUCCESS = "JOIN SUCCESS";
		//ANtwort des Servers auf USERS-Anfrage vom Client: <timecode> USERS START...<timecode> USERS <username>...<timecode>USERS END
		public static final String USERS_START = "USERS START";
		public static final String USERS_END = "USERS END";
		//erfolgreicheCLient Aktion
		public static final String SUCCESS = "SUCCESS";
		
		public static final String FAIL = "FAIL";
		
		//erfolgreiche Neuerstellung eines Raumes
		public static final String CREATE_SUCCESS = "CREATE SUCCESS";
		
		public static final String CREATE = "CREATE";
		//erfolgreiches Verlassen des Raumes
		public static final String LEAVE_SUCCESS = "LEAVE SUCCESS";
		public static final String LEAVE = "LEAVE";
		
		//NEGATIVE RÜCKMELDUNG VOM SERVER: <timecode> BEFEHL...
		//Client-Login war nciht erfolgreich: <timecode> LOGIN FAIL
		public static final String LOGIN_FAIL = "LOGIN FAIL";
		public static final String CREATE_FAIL = "CREATE FAIL";
		public static final String JOIN_FAIL = "JOIN_FAIL";
		
		public static final String JOIN = "JOIN";
		
		public static final String LEAVE_FAIL = "LEAVE_FAIL";
		public static final String USERS = "USERS";
		public static final String START = "START";
		public static final String END = "END";
		public static final String LIST = "LIST";
		
		
	
	
	
}
