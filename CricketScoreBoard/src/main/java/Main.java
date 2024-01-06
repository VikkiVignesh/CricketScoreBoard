import javax.swing.plaf.nimbus.State;
import java.util.*;
import java.sql.*;
import java.lang.*;
public class Main {
    public static void main(String[] args) throws  Exception
    {
        Scanner sc=new Scanner(System.in);

        //Loading driver
        //Before Loading driver first you should add the jdbc jar to t
        Class.forName("com.mysql.cj.jdbc.Driver");

        //Building Connection
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CricketDB","root","**ur Password**");


        Statement st=con.createStatement();
        boolean flag=true;
        while(flag)
        {
            System.out.println("1.For Displaying the Table");
            System.out.println("2.For Adding new Entry in the Table");
            System.out.println("3.For Updating  Already Existing Values");
            System.out.println("4.For Deleting Entries in the Table");
            System.out.println("5.Exit");

            System.out.println("Select any One option");
            int ch=sc.nextInt();

            switch (ch)
            {
                case 1:
                    display(st);
                    break;
                case 2:
                    Insert(sc,st);
                    break;
                case 3:
                    Update(st, sc);
                    break;
                case 4:
                    Delete(sc,st);
                    break;
                case 5:
                    System.out.println("Have a Good Day !!");
                    flag=false;
                    break;
            }
        }


    }

    static void display(Statement st) throws Exception
    {
        String sql="select * from ScoreTable;";
        ResultSet rs = st.executeQuery(sql);
        System.out.println("ID\t"+"PlayerName\t"+"Runs\t"+"Balls");
        while(rs.next())
        {
            System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+
                    rs.getInt(3)+"\t"+rs.getInt(4));
        }
    }

    static  void Insert(Scanner sc,Statement st) throws  Exception
    {
        System.out.println("Enter the ID");
        int id=sc.nextInt();
        System.out.println("Enter the Player Name");
        String name=sc.next();
        System.out.println("Enter the Runs Scored by the Player");
        int runs=sc.nextInt();
        System.out.println("Enter the Number of Balls");
        int balls=sc.nextInt();

        String sql="insert into scoretable values("+id+",'"+name+"',"+runs+","+balls+");";
        int row=st.executeUpdate(sql);
        System.out.println(row+"Rows ");
    }

    static  void Update(Statement st,Scanner sc) throws  Exception
    {
        System.out.println("Enter Player Id for Updating");
        int id=sc.nextInt();
        System.out.println("Enter new Run");
        int runs= sc.nextInt();
        System.out.println("Enter num of Balls");
        int balls=sc.nextInt();
        String sql="Update scoretable set runs="+runs+",balls="+balls+
                " where id="+id+";";
        System.out.println(sql);
        int row=st.executeUpdate(sql);
        System.out.println(row);
        System.out.println(row+" Updated !!");
    }


    static  void Delete(Scanner sc,Statement st) throws  Exception
    {
        System.out.println("Enter Player Id to Delete");
        int id=sc.nextInt();
        String sql="Delete from scoretable where id="+id+";";
        System.out.println(st.executeUpdate(sql)+" row deleted");
    }
}
