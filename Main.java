import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

 class Main extends JFrame 
{
   static String[] yourChoicesItems =
                           {
                            "Coriander        5.00/buddle",
                            "Amaranth        10.50/buddle", 
                            "broccoli            32.00/kg",
                            "potato              50.00/kg",
                            "cauliflower         35.00/kg",
                            "corn                40.00/kg",
                            "cucumber            70.00/kg",
                            "onion               50.00/kg",
                            "mushrooms           50.00/kg"
                            };
   
   static double[] yourChoicesPrices = { 5.00,10.50,32.00, 50.00, 35.00, 40.00,70.00, 50.00, 50.00};
   
   private JList yourChoices;
   private checkOutButtonHandler cHandler; 
   private clearButtonHandler crHandler;
   private selectionButtonHandler sHandler;
   private removeButtonHandler rmHandler;
   private JButton checkB,clearB,selectionB,removeB;
   private JCheckBox check;
   private JTextArea list,bill;
   private Container pane;
   
   public Main()
   {
      super("Welcome to Vegetable Store");

      pane = getContentPane();
      pane.setBackground(new Color(0, 100, 200));
      pane.setLayout(new BorderLayout(5, 5));

      JLabel yourChoicesJLabel = new JLabel("The Vegetable List                                        Shopping Cart  ");
      pane.add(yourChoicesJLabel,BorderLayout.NORTH);
      yourChoicesJLabel.setFont(new Font("Dialog",Font.BOLD,20));

      JPanel m=new JPanel();
      yourChoices = new JList(yourChoicesItems);
      m.add(yourChoices);
      yourChoices.setFont(new Font("Courier",Font.BOLD,14));
      pane.add(m,BorderLayout.WEST);
      
      selectionB=new JButton("Selection");
      sHandler=new selectionButtonHandler();
      selectionB.addActionListener(sHandler);
      m.add(selectionB);
     
      JPanel p=new JPanel();
      list = new JTextArea(20,50);
      p.add(list);
      list.setFont(new Font("Courier",Font.PLAIN,14));
      
      checkB=new JButton("Check out");
      cHandler=new checkOutButtonHandler();
      checkB.addActionListener(cHandler);
      
      clearB=new JButton("Clear");
      crHandler=new clearButtonHandler();
      clearB.addActionListener(crHandler);
      
      removeB=new JButton("Remove");
      rmHandler=new removeButtonHandler();
      removeB.addActionListener(rmHandler);
      
      p.add(removeB);
      p.add(clearB);
      p.add(checkB);

      bill = new JTextArea(20,60);
      p.add(bill);
      bill.setFont(new Font("Courier",Font.PLAIN,12));
      pane.add(p,BorderLayout.CENTER);
      
      setSize(800, 800);
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
   
   private void displayList()
   {
      list.setEditable(false);
      list.setText("");
      int[] listArray = yourChoices.getSelectedIndices();
      
      for (int index = 0; index < listArray.length; index++)
      {
         list.append(yourChoicesItems[listArray[index]] + "\n");
      }

      list.append("\n");
   }
   
   private void displayBill()
   { 
      int[] listArray = yourChoices.getSelectedIndices();
      
      double subTotal = 0;
      double total;

      bill.setEditable(false);
      bill.setText("");

      for (int index = 0; index < listArray.length; index++)
          subTotal = subTotal
                     + yourChoicesPrices[listArray[index]];
      total = subTotal ;

      bill.append("TOTAL    \t\tRM"
                 + String.format("%.2f", total) + "\n\n");
      bill.append("Thank you - Have a Nice Day\n\n");

          //Reset list array.
      yourChoices.clearSelection();

      repaint();
   }
   
  
   private class checkOutButtonHandler implements ActionListener
   {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand().equals("Check out"))
            displayBill();
        }   
    }
   
   private class clearButtonHandler implements ActionListener
   {
        public void actionPerformed(ActionEvent e)
        {
           
        }   
   }
   
   private class selectionButtonHandler implements ActionListener
   {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand().equals("Selection"))
            displayList();
        }   
    }
    
   private class removeButtonHandler implements ActionListener
   {
        public void actionPerformed(ActionEvent e)
        {
           
        }   
   }
   
   public static void main(String[] args)
   {
        Main veg = new Main();
   }
}