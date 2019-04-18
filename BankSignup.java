import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class BankSignup{
    static private JLabel signupUser, signupPass, signupCity, heading;
    static private JTextField signupUserVal, signupPassVal, signupCityVal;
    static private JButton signupSignup;

    static private Connection conn;
    static private JFrame bankSignupFrame;

    private void signupSignupFunc(){
        String user = signupUserVal.getText();
        String pass = signupPassVal.getText();
        String city = signupCityVal.getText();
        if (user=="" || pass=="" || city=="") {
            JOptionPane.showMessageDialog(null, "Please complete the form.");
        }
        System.out.println("Test");
        try{
            Statement stmt = conn.createStatement();
            String rr = "insert into Hospitals values('"+user+"', '"+pass+"', '"+city+"');";
            System.out.println(rr);
            boolean rs1 = stmt.execute(rr);

            Statement stmt2 = conn.createStatement();
            String strSelect2 = "select * from Hospitals";
            ResultSet rset2 = stmt2.executeQuery(strSelect2);
            while(rset2.next()){
                System.out.println(rset2.getString("Name"));
            }
            JOptionPane.showMessageDialog(null, "Account Created Successfully.");
            new Bank();
            bankSignupFrame.dispose();
        }catch(Exception e2){
            System.out.println(e2.getMessage());
            JOptionPane.showMessageDialog(null, "Username Already Exists.");  //Internal Error?
        }
    }

    public BankSignup(){

        bankSignupFrame = new JFrame("ODMS");
        bankSignupFrame.setContentPane(new JLabel(new ImageIcon("C:\\Users\\aman1\\Desktop\\ODMS\\dp.png")));
//        super("Sign Up Box");
    bankSignupFrame.setLayout(null);
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
        signupUser = new JLabel("Username");
        signupPass = new JLabel("Password");
        signupCity = new JLabel("City");
        signupUserVal = new JTextField(15);
        signupPassVal = new JTextField(15);
        signupCityVal = new JTextField(15);
        signupSignup = new JButton("Create Account");
        Font f = new Font("Georgia",Font.BOLD,20);
        Font f2 = new Font("Arial",Font.BOLD,15);
        Font h = new Font("Algerian",Font.PLAIN,40);
        heading.setBounds(110,100,800,70);
        heading.setFont(h);
        signupUser.setBounds(340, 180, 120, 30);
        signupUser.setFont(f);
        signupUserVal.setBounds(480, 180, 120, 30);
        signupPass.setBounds(340, 220, 120, 30);
        signupPass.setFont(f);
        signupPassVal.setBounds(480, 220, 120, 30);
        signupCity.setBounds(340, 260, 120, 30);
        signupCity.setFont(f);
        signupCityVal.setBounds(480, 260, 120, 30);
        signupSignup.setBounds(380, 350, 180, 30);
        signupSignup.setFont(f2);

        bankSignupFrame.add(heading);
        bankSignupFrame.add(signupSignup);
        bankSignupFrame.add(signupUser);
        bankSignupFrame.add(signupUserVal);
        bankSignupFrame.add(signupPass);
        bankSignupFrame.add(signupPassVal);
        bankSignupFrame.add(signupCity);
        bankSignupFrame.add(signupCityVal);
        signupSignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signupSignupFunc();
            }
        });
        bankSignupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bankSignupFrame.setSize(1000, 600);
        bankSignupFrame.setVisible(true);
    }
//    public static void main(String args[]){
//        new BankSignup();
//    }
}
