package mainClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import gui.ClientGui;
import utils.ClientCommands;
import utils.Commands;
import utils.Constants;
import utils.MyArrayList;
import utils.MyList;

/**
 * 
 * @author Nelli Welker, Etienne Onasch
 *
 */
public final class ClientThread extends AbstractClientServerThread {

	//private String serverIp;
	private String name;
	private InetAddress inetAddress;
	
	private ClientGui gui = null;
	

	// state
	//private boolean loggedIn = false;
	
	
	private int timeout = Constants.TIMEOUT_5SEC;
	
	public ClientThread(String name, String serverIp, int port) {
		this.name = name;
		//this.serverIp = serverIp;
		this.port = port;
		try {
			this.inetAddress = InetAddress.getByName(serverIp);
		} catch (UnknownHostException e) {
			selfMessage("serverIp couldn't be resolved into a valid internet address.");
			e.printStackTrace();
		}

	}
	
	private void init() {

		try {
			
			socket = new Socket();
			socket.connect(new InetSocketAddress(inetAddress, port), timeout);
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream());
		} 
		catch (SocketTimeoutException e) {

			e.printStackTrace();
		}
		
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		selfMessage("ClientThread initialized.");
	}
	
	
	public void setGui(ClientGui gui) {
		this.gui = gui;
	}
	

	
	
	
	@Override
	protected void selfMessage(String message) {
		if(gui != null) {
			gui.console(message);
		}
		super.selfMessage(message);
	}
	
	
	private String listToString(List<String> l) {
		StringBuilder sb = new StringBuilder();
		int counter = 0;
		for(String s : l) {
			sb.append(s);
			counter++;
			
			if(counter < l.size()) {
				sb.append(" ");
			}
			
		}
		return sb.toString();
	}
	
	@Override
	public void run() {
		init();
		String currentLine;
		
		
		@SuppressWarnings("unused")
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
		int userCounter = 0;
		int roomCounter = 0;
		System.out.println("ClientThread is running now.");
		
		// SERVER DOES NOT SEND GREETINGS COMMAND!!!
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		msgFromClient(Commands.LOGIN + " " + name);
		
		try {
			while (!closed && (currentLine = br.readLine()) != null) {
			
				
				
				//List of all words separated by " " 
				
				MyList<String> wordList = new MyArrayList<>(Arrays.asList(currentLine.split(" ")));
				
				
				
				/* 
				 * Die Wortliste wird nacheinander destruktiv abgearbeitet
				 * bis es nur noch Text gibt, der ausgegeben werden soll.
				 */

				
				if(wordList.removeFirstIfEquals(Commands.SERVER_PREFIX)) {
					if(wordList.removeFirstIfEquals(Commands.GREETINGS)) {
						//selfMessage("[LOGIN SUCCESSFUL]" + listToString(wordList));
						msgFromClient(Commands.LOGIN + " " + name);
					}
					else if(wordList.removeFirstIfEquals(Commands.SEND)) {
						selfMessage("SERVER: " + listToString(wordList));
					}
					else if(wordList.removeFirstIfEquals(Commands.DELETE)) {
						selfMessage("SERVER:  Room " + listToString(wordList) + " closed.");
					}
				}else if(wordList.removeFirstIfEquals(Commands.QUIT)) {
					selfMessage("SERVER: " + listToString(wordList) + " (kicked from Server)");
					close();
					gui.dispose();
					break;
				}
				
				else if(wordList.get(0).matches("[0-9]+")) {
					String serverTimeCode = wordList.remove(0);;
					@SuppressWarnings("unused")
					Date serverDate = new Date(Long.valueOf(serverTimeCode));
					
					if(wordList.removeFirstIfEquals(Commands.LOGIN)) {
						if(wordList.removeFirstIfEquals(Commands.SUCCESS)) {
							selfMessage("SERVER: " + listToString(wordList) + " [LOGIN SUCCESSFUL]");
						}
						else if(wordList.removeFirstIfEquals(Commands.FAIL)) {
							selfMessage("[LOGIN NOT SUCCESSFUL]");
						}
					}
					else if(wordList.removeFirstIfEquals(Commands.USERS)) {
						if(wordList.removeFirstIfEquals(Commands.START)) {
							selfMessage("[USER LIST]-->");
						}
						else if(wordList.removeFirstIfEquals(Commands.END)) {
							selfMessage("-->[END OF USER LIST]");
							userCounter = 0;
						}
						else {
							selfMessage("user#"+ ++userCounter +": " +listToString(wordList));
						}
					}
					else if(wordList.removeFirstIfEquals(Commands.LIST)) {
						if(wordList.removeFirstIfEquals(Commands.START)) {
							selfMessage("[ROOM LIST]-->");
						}
						else if(wordList.removeFirstIfEquals(Commands.END)) {
							selfMessage("-->[END OF ROOM LIST]");
							roomCounter = 0;
						}
						else {
							selfMessage("room#"+ ++roomCounter + ": " + listToString(wordList));
						}
					}
					else if(wordList.removeFirstIfEquals(Commands.CREATE)) {
						if(wordList.removeFirstIfEquals(Commands.FAIL)) {
							selfMessage("[ROOM COULD NOT BE CREATED!]");
						}
						else if(wordList.removeFirstIfEquals(Commands.SUCCESS)) {
							selfMessage("[SUCCESSFULLY CREATED ROOM!]");
						}
					}
					else if(wordList.removeFirstIfEquals(Commands.JOIN)) {
						if(wordList.removeFirstIfEquals(Commands.SUCCESS)) {
							selfMessage("[SUCCESSFULLY JOINED ROOM!]");
						}
						else if(wordList.removeFirstIfEquals(Commands.FAIL)) {
							selfMessage("[ROOM COULD NOT BE JOINED!]");
						}
					}
					else if(wordList.removeFirstIfEquals(Commands.LEAVE)) {
						if(wordList.removeFirstIfEquals(Commands.SUCCESS)) {
							selfMessage("[SUCCESSFULLY LEFT ROOM!]");
						}
						else if(wordList.removeFirstIfEquals(Commands.FAIL)) {
							selfMessage("[ROOM COULD NOT BE LEFT!]");
						}
					}
					
					
					
					
					
				}
				
				
				


			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
			System.out.println("Server does not respond anymore!");
		}
	}
	
	
	public void msgFromClient(String message) {
		
		if(!message.matches("\\s+") && !message.equals("")) {
			long timeCode = System.currentTimeMillis();
			List<String> userCommands = ClientCommands.getClientCommandList();
			boolean isCommand = userCommands.contains(message.split(" ")[0]);
			write(isCommand ? timeCode + " " + message : timeCode + " " + ClientCommands.SEND + " " + message);
		}
		
	
//		Date date = new Date();
//		long timecode = date.getTime();
//		
//		//Vom client ankommende, im Chat eingegebene Nachricht, gesplittet nach Leerzeichen
//		String[] splitMsg = message.split(" ");
//
//		if (splitMsg.length > 0) {
//			System.out.println("MESSAGE FROM CLIENT: " + message);
//			switch (splitMsg[0]) {
//			case Commands.USERS:
//				write(timecode + " " + message);
//				break;
//			case Commands.LIST:
//				write(timecode + " " + message);
//				break;
//			case Commands.LEAVE:
//				write(timecode + " " + message);
//				break;
//			case Commands.QUIT:
//				write(timecode + " " + message);
//				break;
//			case Commands.GET:
//				write(timecode + " GET " + buildMessage(2, splitMsg));
//				break;
//			case Commands.CREATE_ROOM:
//				write(timecode + " CREATE " + buildMessage(2, splitMsg));
//				break;
//			case Commands.JOIN:
//				write(timecode + " JOIN " + buildMessage(2, splitMsg));
//				break;
//			default:
//				write(timecode + " SEND " + message);
//				break;
//			}
//		}
	}
	
	
	
	public static void main(String...args) throws IOException {
		if(args.length != 3) {
			System.out.println("3 arguments needed! <username>, <server ip> and <server port>.");
		}else {
			String username = args[0];
			String ip = args[1];
			String port = args[2];
			
			ClientThread ct = new ClientThread(username, ip, Integer.parseInt(port));
			ct.start();
			
		}
		
	}

	
}
