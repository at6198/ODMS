import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;

public class BranchAction{

    static private JLabel  heading, organSelectText;
    static private JComboBox organSelect;
    static private JButton organButton;

    static private Connection conn;

    static private String branch;
    static private String action;

    static private int val;

    static private JFrame branchActionFrame;

    private void organActionPerformed(String organName, int val, String branch){
        try{
            Statement stmt5 = conn.createStatement();
            String rr6 = "update OrganBank set "+organName+" = "+Integer.toString(val)+" where BranchName = '"+branch+"';";
            System.out.println(rr6);
            boolean rs6 = stmt5.execute(rr6);

            Statement stmt6 = conn.createStatement();
            String strSelect6 = "select * from Hospitals";
            ResultSet rset2 = stmt6.executeQuery(strSelect6);
            while(rset2.next()){
                System.out.println(rset2.getString("Name"));
            }
            JOptionPane.showMessageDialog(null, "Operation Performed Successfully.");
            new Branch(branch);
            branchActionFrame.dispose();
        }catch(Exception e2){
            System.out.println(e2.getMessage());
            JOptionPane.showMessageDialog(null, "Internal Error.");
            branchActionFrame.dispose();
        }
    }

    private void organAction(String branch, String organName, String action){
        System.out.println("Test");
        try{
            Statement stmt4 = conn.createStatement();
            String rr2 = "select "+organName+" from OrganBank where BranchName = '"+branch+"';";
            System.out.println(rr2);
            ResultSet rs4 = stmt4.executeQuery(rr2);
            rs4.next();
            val = rs4.getInt(organName);
            if(action.equals("withdraw")) {
                if(val==0){
                    JOptionPane.showMessageDialog(null, "Operation Cannot be performed. No items remaining.");
                }else{
                    val =  val - 1;
                    organActionPerformed(organName, val, branch);
                }
            }else if(action.equals("deposit")){
                val = val + 1;
                organActionPerformed(organName, val, branch);
            }

        }catch(Exception e3){
            System.out.println(e3.getMessage());
        }
    }

    public BranchAction(String bran, String acti){

        this.branch = bran;
        this.action = acti;
System.out.println(bran);
        System.out.println(acti);
        branchActionFrame = new JFrame("ODMS");
        branchActionFrame.setContentPane(new JLabel(new ImageIcon("C:\\Users\\aman1\\Desktop\\ODMS\\dp.png")));
//        super("Sign Up Box");
        branchActionFrame.setLayout(null);

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?useSSL=false", "root", "xxxx");
            Statement stmt = conn.createStatement();
            String strSelect = "select * from Hospitals";
            ResultSet rset = stmt.executeQuery(strSelect);
             while(rset.next()){
                System.out.println(rset.getString("Name"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        Font f = new Font("Georgia",Font.BOLD,20);
        Font f2 = new Font("Arial",Font.BOLD,15);
        Font f3 = new Font("Comic Sans MS", Font.PLAIN,15);
        Font h = new Font("Algerian",Font.PLAIN,40);
        heading = new JLabel("Organ Donation Management System");
        organSelectText = new JLabel("Select Organ for Branch " + bran);
        organSelect = new JComboBox();
        organSelect.addItem("Kidney");
        organSelect.addItem("Liver");
        organSelect.addItem("Lung");
        organSelect.addItem("Heart");
        organSelect.addItem("Pancreas");
        organSelect.addItem("Intestine");

        organButton = new JButton("Submit");
        branchActionFrame.add(heading);
        branchActionFrame.add(organSelectText);
        branchActionFrame.add(organSelect);
        branchActionFrame.add(organButton);
        heading.setBounds(110,100,800,70);
        organSelectText.setBounds(290, 200, 500, 30);
        organSelect.setBounds(400, 270, 150, 40);
        organButton.setBounds(410, 360, 120, 30);
        heading.setFont(h);
        organSelectText.setFont(f);
        organSelect.setFont(f3);
        organButton.setFont(f2);

        organButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String)organSelect.getItemAt(organSelect.getSelectedIndex());
                System.out.println("after adding action listener to see selected organ");
                System.out.println(name);
                organAction(bran, name, acti);
            }
        });
        branchActionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        branchActionFrame.setSize(1000, 600);
        branchActionFrame.setVisible(true);

    }
}
