import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

public class CourseView extends JFrame {
    Button back = new Button("뒤로 가기");
    String[] header = new String[]{"과목"};
    DefaultTableModel model3 = new DefaultTableModel(header, 0);

    public void setMiddle(){
        while(model3.getRowCount() != 0){
            model3.removeRow(0);
        }

        for(Course c : User.user.getRegistered()){
            Vector<String> temp = new Vector<>();
            temp.add(c.toString());
            model3.addRow(temp);
        }
    }
    CourseView(){
        this.setTitle("수강신청");
        Container container = this.getContentPane();
        this.setContentPane(container);
        setSize(1000,900);

        setBounds(0 , 0 , 1000 , 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTable jtable3 = new JTable(model3);
        jtable3.getColumn("과목").setWidth(200);
        jtable3.setPreferredSize(new Dimension(200,1000));
        JScrollPane scroll3 = new JScrollPane(jtable3);
        scroll3.setPreferredSize(new Dimension(200,250));

        this.add(scroll3);

        JPanel bottom = new JPanel();
        bottom.add(back);
        back.addActionListener(e -> {
            new MainView();
            setVisible(false);
        });
        setMiddle();
        this.add(BorderLayout.SOUTH, bottom);
        setVisible(true);
    }

    public static void main(String[] args) {
        User.selectUser(new User("123","123","123"));
        new CourseView();
    }
}
