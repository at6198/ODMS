import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
public class Hospital{
    static private JLabel hospitalBranchSelect, heading;
    static private JButton cityAhmedabad, cityBengaluru, cityChennai, cityHydrabad, cityJaipur;
    static private JButton cityKolkata, cityMumbai, cityNewDelhi, cityPune, citySurat;
    static private JButton hospitalLogout, hospitalExit;

    static private Connection conn;

    static private JFrame hospitalFrame;

    private void cityCaller(String a){
        new Branch(a);
        hospitalFrame.dispose();
    }

    private void hospitalLogoutFunc(){
        new Bank();
        hospitalFrame.dispose();
    }

    public Hospital(){

        hospitalFrame = new JFrame("ODMS");
        hospitalFrame.setContentPane(new JLabel(new ImageIcon("C:\\Users\\aman1\\Desktop\\ODMS\\dp.png")));

//        super("Welcome");
        hospitalFrame.setLayout(null);

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

        hospitalBranchSelect = new JLabel("Select the Branch: ");
        cityAhmedabad = new JButton("Ahmedabad");
        cityBengaluru = new JButton("Bengaluru");
        cityChennai = new JButton("Chennai");
        cityHydrabad = new JButton("Hyderabad");
        cityJaipur = new JButton("Jaipur");
        cityKolkata = new JButton("Kolkata");
        cityMumbai = new JButton("Mumbai");
        cityNewDelhi = new JButton("New Delhi");
        cityPune = new JButton("Pune");
        citySurat = new JButton("Surat");
        hospitalLogout = new JButton("Logout");
        hospitalExit = new JButton("Exit");
        heading = new JLabel("Organ Donation Management System");
        Font f = new Font("Georgia",Font.BOLD,20);
        Font f2 = new Font("Arial",Font.BOLD,15);
        Font f3 = new Font("Comic Sans MS", Font.PLAIN,12);
        Font h = new Font("Algerian",Font.PLAIN,40);
        hospitalFrame.add(heading);
        hospitalFrame.add(hospitalBranchSelect);
        hospitalFrame.add(cityAhmedabad);
        hospitalFrame.add(cityBengaluru);
        hospitalFrame.add(cityChennai);
        hospitalFrame.add(cityHydrabad);
        hospitalFrame.add(cityJaipur);
        hospitalFrame.add(cityKolkata);
        hospitalFrame.add(cityMumbai);
        hospitalFrame.add(cityNewDelhi);
        hospitalFrame.add(cityPune);
        hospitalFrame.add(citySurat);
        hospitalFrame.add(hospitalLogout);
        hospitalFrame.add(hospitalExit);
        heading.setFont(h);
        heading.setBounds(110,100,800,70);
        hospitalBranchSelect.setBounds(360, 180, 250, 30);
        hospitalBranchSelect.setFont(f);
        cityAhmedabad.setBounds(360, 220, 120, 20);
        cityAhmedabad.setFont(f3);
        cityBengaluru.setBounds(480, 220, 120, 20);
        cityBengaluru.setFont(f3);
        cityChennai.setBounds(360, 245, 120, 20);
        cityChennai.setFont(f3);
        cityHydrabad.setBounds(480, 245, 120, 20);
        cityHydrabad.setFont(f3);
        cityJaipur.setBounds(360, 270, 120, 20);
        cityJaipur.setFont(f3);
        cityKolkata.setBounds(480, 270, 120, 20);
        cityKolkata.setFont(f3);
        cityMumbai.setBounds(360, 295, 120, 20);
        cityMumbai.setFont(f3);
        cityNewDelhi.setBounds(480, 295, 120, 20);
        cityNewDelhi.setFont(f3);
        cityPune.setBounds(360, 320, 120, 20);
        cityPune.setFont(f3);
        citySurat.setBounds(480, 320, 120, 20);
        citySurat.setFont(f3);
        hospitalLogout.setBounds(330, 380, 130 ,30);
        hospitalLogout.setFont(f2);
        hospitalExit.setBounds(490, 380, 130, 30);
        hospitalExit.setFont(f2);
        cityAhmedabad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityCaller("Ahmedabad");
            }
        });
        cityBengaluru.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityCaller("Bengaluru");
            }
        });
        cityChennai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityCaller("Chennai");
            }
        });
        cityHydrabad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityCaller("Hydrabad");
            }
        });
        cityJaipur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityCaller("Jaipur");
            }
        });
        cityKolkata.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityCaller("Kolkata");
            }
        });
        cityMumbai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityCaller("Mumbai");
            }
        });
        cityNewDelhi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityCaller("New Delhi");
            }
        });;
        cityPune.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityCaller("Pune");
            }
        });;
        citySurat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityCaller("Surat");
            }
        });
        hospitalLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hospitalLogoutFunc();
            }
        });
        hospitalExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        hospitalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hospitalFrame.setSize(1000, 600);
        hospitalFrame.setVisible(true);

    }
}
