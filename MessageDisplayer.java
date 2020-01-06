/*
 Brendan Aucoin
 12/12/17
 this class has only static methods.
 for displaying and taking input through the JOptionPane class.
 you would never make an instance of this class.
 its only purpose is for if you want the basic pop up dialog or input dialog.
 */
package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MessageDisplayer
{
	/*all these methods are static becuase they don't rely on anything to work.*/
	public MessageDisplayer() {}
	/*displays a message on a specific frame.*/
	public static void displayMessage(JFrame f, String msg)
	{
		JOptionPane.showMessageDialog(f, msg);
	}
	/*this method displays a message and passes null as the frame parameter.*/
	public static void displayMessage(String msg)
	{
		JOptionPane.showMessageDialog(null,msg);
	}
	/*this method displays an input message. without a JFrame object passed in*/
	public static String displayInputMessage(String msg)
	{
		return(JOptionPane.showInputDialog(msg));
	}
	/*this method displays an input message on a specific frame.*/
	public static String displayInputMessage(JFrame f,String msg)
	{
		return (JOptionPane.showInputDialog(f,msg));
	}
	/*this method displays a message on a frame, given a message, title, type of icon it it, and the image.*/
	public static void displayComplexMessage(JFrame f,String msg,String title,int typeOfMessage,ImageIcon icon)
	{
		JOptionPane.showMessageDialog(f,msg,title, typeOfMessage,icon);
	}
	/*this method displays a message without a frame, given a message, title, type of icon it it, and the image.*/
	public static void displayComplexMessage(String msg,String title,int typeOfMessage,ImageIcon icon)
	{
		JOptionPane.showMessageDialog(null,msg,title, typeOfMessage,icon);
	}
	/*this method displays a yes or no option without a frame.*/
	public static int displayYesNoMessage(String msg,String title,int typeOfMessage)
	{
		return JOptionPane.showConfirmDialog(null, msg, title,typeOfMessage,
		        JOptionPane.YES_NO_OPTION);
	}
	/*this method displays a yes no option with a frame.*/
	public static int displayYesNoMessage(JFrame f,String msg,String title)
	{
		return JOptionPane.showConfirmDialog(f, msg, title,
		        JOptionPane.YES_NO_OPTION);
	}
}
