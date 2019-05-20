package _02_Chat_Application;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp {
	Server server;
	Client client;
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton enter = new JButton("Enter");
	JTextField textServer = new JTextField(50);
	JTextField textClient = new JTextField(50);
	JLabel serverL = new JLabel("Server: ");
	JLabel clientL = new JLabel("Client: ");
	
	public static void main(String[] args) {
		ChatApp chat = new ChatApp();
	}
	ChatApp(){
		int response = JOptionPane.showConfirmDialog(null, "Wanna host the connection?", "Connections Y'all", JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {
			server = new Server(8080, textServer);
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			frame.add(panel);
			panel.add(serverL);
			panel.add(textServer);
			panel.add(enter);
			panel.add(clientL);
			panel.add(textClient);
			frame.setVisible(true);
			frame.pack();
			enter.addActionListener((e) ->{
				server.sendClick(textServer.getText());
				textServer.setText(null);
			});
		}
		else {
			client = new Client(JOptionPane.showInputDialog("IP Address"), Integer.parseInt(JOptionPane.showInputDialog("Port")));
			frame.add(panel);
			panel.add(clientL);
			panel.add(textClient);
			panel.add(enter);
			panel.add(serverL);
			panel.add(textServer);
			frame.setVisible(true);
			frame.pack();
			enter.addActionListener((e) ->{
				server.sendClick(textClient.getText());
				textClient.setText(null);
			});
		}
	}
}
