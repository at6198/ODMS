import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class Branch{
    static private JLabel heading, organSelect, kidney, liver, lung, heart, pancreas, intestine,kidneyVal, liverVal, lungVal, heartVal, pancreasVal, intestineVal;
    static private JButton withdrawWithdraw, depositDeposit, branchBranchWindow, branchLogout, branchExit;

    static private String branch;
    static private Connection conn;

    static private JFrame branchFrame;

    private void getBranchData(){
        try {
            Statement stmt2 = conn.createStatement();
            String strSelect = "select * from OrganBank where BranchName = '"+branch+"';";
            System.out.println(strSelect);
            ResultSet rset2 = stmt2.executeQuery(strSelect);
            rset2.next();
            System.out.println(rset2.getString("Kidney"));
            System.out.println(rset2.getString("Liver"));
            System.out.println(rset2.getString("Lung"));
            System.out.println(rset2.getString("Heart"));
            System.out.println(rset2.getString("Pancreas"));
            System.out.println(rset2.getString("Intestine"));

            kidneyVal = new JLabel(rset2.getString("Kidney"));
            liverVal = new JLabel(rset2.getString("Liver"));
            lungVal = new JLabel(rset2.getString("Lung"));
            heartVal = new JLabel(rset2.getString("Heart"));
            pancreasVal = new JLabel(rset2.getString("Pancreas"));
            intestineVal = new JLabel(rset2.getString("Intestine"));

        }catch(Exception ex2){
            System.out.println(ex2.getMessage());
        }
    }

    private void withdrawFunc(String bran){
        BranchAction withdraw = new BranchAction(bran, "withdraw");
        branchFrame.dispose();

    }

    private void depositFunc(String bran){
        BranchAction withdraw = new BranchAction(bran, "deposit");
        branchFrame.dispose();
    }

    private void branchWindowFunc(){
        new Hospital();
        branchFrame.dispose();
    }

    private void branchLogoutFunc(){
        new Bank();
        branchFrame.dispose();
    }

    public Branch(String bran){

      branchFrame = new JFrame("ODMS");
      branchFrame.setContentPane(new JLabel(new ImageIcon("C:\\Users\\aman1\\Desktop\\ODMS\\dp.png")));

        branchFrame.setLayout(null);

        branch = bran;

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

        heading = new JLabel("Organ Donation Management System");
        organSelect = new JLabel("Details of Availability of Organs");
        kidney = new JLabel("Kidney: ");
        liver = new JLabel("Liver: ");
        lung = new JLabel("Lung: ");
        heart = new JLabel("Heart: ");
        pancreas = new JLabel("Pancreas: ");
        intestine = new JLabel("Intestine: ");
        Font f = new Font("Georgia",Font.BOLD,20);
        Font f2 = new Font("Arial",Font.BOLD,15);
        Font f3 = new Font("Comic Sans MS", Font.BOLD,15);
        Font h = new Font("Algerian",Font.PLAIN,40);

        getBranchData();

        withdrawWithdraw = new JButton("Request Organ");
        depositDeposit = new JButton("Deposit Organ");

        branchBranchWindow = new JButton("Back to Branch Selection");
        branchLogout = new JButton("Logout");
        branchExit = new JButton("Exit");
        heading.setFont(h);
        heading.setBounds(110,40,800,70);
        organSelect.setBounds(300, 100, 500, 30);
        organSelect.setFont(f);
        kidney.setBounds(370, 155, 80, 20);
         kidney.setFont(f3);
        kidneyVal.setBounds(470, 155, 80, 20);
        kidneyVal.setFont(f3);
        liver.setBounds(370, 185, 80, 20);
        liver.setFont(f3);
        liverVal.setBounds(470, 185, 80, 20);
        liverVal.setFont(f3);
        lung.setBounds(370, 215, 80, 20);
        lung.setFont(f3);
        lungVal.setBounds(470, 215, 80, 20);
        lungVal.setFont(f3);
        heart.setBounds(370, 245, 80, 20);
        heart.setFont(f3);
        heartVal.setBounds(470, 245, 80, 20);
        heartVal.setFont(f3);
        pancreas.setBounds(370, 275, 80, 20);
        pancreas.setFont(f3);
        pancreasVal.setBounds(470, 275, 80, 20);
        pancreasVal.setFont(f3);
        intestine.setBounds(370, 305, 80, 20);
        intestine.setFont(f3);
        intestineVal.setBounds(470, 305, 80, 20);
        intestineVal.setFont(f3);
        withdrawWithdraw.setBounds(250, 360, 150, 30);
         withdrawWithdraw.setFont(f2);
        depositDeposit.setBounds(450, 360, 150, 30);
         depositDeposit.setFont(f2);
        branchBranchWindow.setBounds(310, 400, 240, 30);
        branchBranchWindow.setFont(f2);
        branchLogout.setBounds(320, 440, 100, 30);
         branchLogout.setFont(f2);
        branchExit.setBounds(430, 440, 100, 30);
         branchExit.setFont(f2);

        branchFrame.add(heading);
        branchFrame.add(organSelect);
        branchFrame.add(kidney);
        branchFrame.add(kidneyVal);
        branchFrame.add(liver);
        branchFrame.add(liverVal);
        branchFrame.add(lung);
        branchFrame.add(lungVal);
        branchFrame.add(heart);
        branchFrame.add(heartVal);
        branchFrame.add(pancreas);
        branchFrame.add(pancreasVal);
        branchFrame.add(intestine);
        branchFrame.add(intestineVal);
        branchFrame.add(withdrawWithdraw);
        branchFrame.add(depositDeposit);
        branchFrame.add(branchBranchWindow);
        branchFrame.add(branchLogout);
        branchFrame.add(branchExit);

        withdrawWithdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdrawFunc(bran);
            }
        });
        depositDeposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depositFunc(bran);
            }
        });
        branchBranchWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                branchWindowFunc();
            }
        });
        branchLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                branchLogoutFunc();
            }
        });
        branchExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        branchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        branchFrame.setSize(1000, 600);
        branchFrame.setVisible(true);
    }
}
