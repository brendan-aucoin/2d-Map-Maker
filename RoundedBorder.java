package gui;
/*
Brendan Aucoin
04/15/17
This class is simply used to create a round border around a button. this could be inside as a nested class similar to an action class
but i decided to make it a completly sepertae class so multiple classes in the application can use the rounded button features.
This class gives the illusion of the button being rounded.  it paints a rounded border over a button to make it seem like it has rounded edges. 
this is an easier solution to the other way of making a round button, which involves painting an oval or another shape and adding an action listener to that and adding animations to that once its clicked so it looks likes its being pressed.
*/
import java.awt.Insets;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.border.Border;//the import needed to make borders
public class RoundedBorder implements Border //a public class that implements the interface Border. the necassary methods for Border are getBordeInsets, and isBorderOpaque
{
 /*1 field*/
 int round;
 /*constructor takes in the parameter of how round you want the border to be*/
public RoundedBorder(int x) 
{
   this.round = x;//the field round is = to the parameter x. this.round is just saying the instance of round that is in this class. which in this case is the field round.
}
/*this method returns the border for the button you add it to*/
  public Insets getBorderInsets(Component c)//one of the necassary methods for this interface
  {
    //the parmeters are: (top,left,bottom,right); the borders in those directions.
    //the number entered is whatever nuber is passed into for the RoundedBorder. so round in this case.
    //so the top border is the number that is entered +1, the left is the number that is entered +1, the bottom is the number entered +2, and the right is the number entered.
    return new Insets(this.round+1, this.round+1, this.round+2, this.round);//returning a new set of insets which is the coordinates of the border.  the insets are teh border or the container.
  }
  /*this method checks if the button you add the border to is opaque. this will always return true becuase the button will be opaque*/
     public boolean isBorderOpaque()//the other necassary method for this interface.
     {
       return true;
     }
     /*this method actually paints the border. and this is another one of the methods that is implemented with the Border interface.*/
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) //this is an easy way to get around the button actually being a rectangle. 
    {
        g.drawRoundRect(x,y,width-1,height-1,round,round);//the drawRoundRect method has teh parameters: x coordinate, y coordinate, width,height,arc width, arc height
    }
}
