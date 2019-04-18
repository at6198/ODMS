import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
public class Bank{

    //Declaring UI components
    private JLabel loginUser, loginPass, heading;
    static private JTextField loginUserVal, loginPassVal;
    private JButton loginLogin, loginSignup, loginExit;
    static private JFrame bankFrame;


//    static Statement stmt, st1, st2, st3, st4, st5;
    static private Connection conn;

    private void bankLoginFunc(){
        String user = loginUserVal.getText();
        String pass = loginPassVal.getText();

        if(user == "" || pass == ""){
            JOptionPane.showMessageDialog(null, "Please enter necessary fields.");
        }else{
            try{
                Statement stmt = conn.createStatement();
                String rr = "select pass from Hospitals where Name = '" + user + "';";
                System.out.println(rr);
                ResultSet rs1 = stmt.executeQuery(rr);
                if(rs1.next()){
                    System.out.println(rs1.getString("Pass"));
                    System.out.println(pass);
                    if (pass.equals(rs1.getString("Pass"))) {
                        System.out.println("Login!");
                        JOptionPane.showMessageDialog(null, "Login Successful.");
                        new Hospital();
                        bankFrame.dispose();
                    }else{
                        System.out.println("Invalid Password for given Username");
                        JOptionPane.showMessageDialog(null, "Invalid Password for given Username");
                    }
                }
                else{
                    System.out.println("No login");
                    JOptionPane.showMessageDialog(null, "Invalid Username and Password. Please Try Again");
                }

            }catch(Exception e2){
                JOptionPane.showMessageDialog(null, "Internal Error. Please check database connectivity.");
            }
        }
    }

    private void bankSignupFunc(){
        new BankSignup();
        bankFrame.dispose();
    }

    public Bank() {

        bankFrame = new JFrame("ODMS");
        bankFrame.setContentPane(new JLabel(new ImageIcon("C:\\Users\\aman1\\Desktop\\ODMS\\dp.png")));
        bankFrame.setLayout(null);

        //Attempt to connect to database.
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?useSSL=false", "root", "xxxx");
            Statement stmt = conn.createStatement();
            String strSelect = "select * from OrganBank";
            ResultSet rset = stmt.executeQuery(strSelect);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        heading = new JLabel("Organ Donation Management System");
        loginUser = new JLabel("Username: ");
        loginUserVal = new JTextField(15);
        loginPass = new JLabel("Password:");
        loginPassVal = new JTextField(15);
        loginLogin = new JButton("Login");
        loginSignup = new JButton("Sign up");
        loginExit = new JButton("Exit");
        Font f = new Font("Georgia",Font.BOLD,20);
        Font f2 = new Font("Arial",Font.BOLD,15);
        Font h = new Font("Algerian",Font.PLAIN,40);
        bankFrame.add(heading);
        bankFrame.add(loginLogin);
        bankFrame.add(loginSignup);
        bankFrame.add(loginExit);
        bankFrame.add(loginUser);
        bankFrame.add(loginUserVal);
        bankFrame.add(loginPass);
        bankFrame.add(loginPassVal);
        heading.setFont(h);
        heading.setBounds(110,100,800,70);
        loginUser.setBounds(340, 180, 120, 30);
        loginUser.setFont(f);
        loginUserVal.setBounds(480, 180, 120, 30);
        loginPass.setBounds(340, 220, 120, 30);
        loginPass.setFont(f);
        loginPassVal.setBounds(480, 220, 120, 30);
        loginLogin.setBounds(360, 300, 100, 30);
        loginLogin.setFont(f2);
        loginSignup.setBounds(490, 300, 100, 30);
        loginSignup.setFont(f2);
        loginExit.setBounds(415, 350, 100, 30);
        loginExit.setFont(f2);
        loginLogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                bankLoginFunc();
            }
        });
        loginSignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankSignupFunc();
            }
        });
        loginExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        bankFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bankFrame.setSize(1000, 600);
        bankFrame.setVisible(true);
    }


    public static void main(String args[]){
        new Bank();
    }
}
