package utils;

import java.util.List;

import org.junit.Test;

public class ClientCommandsTest {

	@Test
	public void testGetClientCommandList() {
		List<String> list = ClientCommands.CLIENT_COMMAND_LIST;
		
		for(String s : list) {
			System.out.println(s);
		}
	}

}
