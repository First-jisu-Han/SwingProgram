import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

public class PreCourseRegisterView extends JFrame {

    private static final long serialVersionUID = 1L;
	ArrayList<Course> onRight = new ArrayList<>();
    Course selected = null;
    Button back = new Button("뒤로 가기");
    Button saveButton = new Button("추가");
    ArrayList<String> onLeft = new ArrayList<>();
    String[] header = new String[]{"과목"};
    String[] header2 = new String[]{"미리담기"};
    DefaultTableModel model2 = new DefaultTableModel(header, 0);
    DefaultTableModel model3 = new DefaultTableModel(header2, 0);

    private void setNext(String s){
        ArrayList<Course> targets = DirectoryParser.getAllCourses().get(s);
        while(model2.getRowCount() != 0){
            model2.removeRow(0);
        }

        for(Course c : targets){
            Vector<String> temp = new Vector<>();
            temp.add(c.toString());
            model2.addRow(temp);
        }
        onRight = targets;
    }

    public void setMiddle(){
        while(model3.getRowCount() != 0){
            model3.removeRow(0);
        }

        for(Course c : User.user.saved){
            Vector<String> temp = new Vector<>();
            temp.add(c.toString());
            model3.addRow(temp);
        }
    }
    PreCourseRegisterView(){
        this.setTitle("미리담기");
        Container container = this.getContentPane();
        this.setContentPane(container);
        setSize(1000,900);

        setBounds(0 , 0 , 1000 , 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Vector<String> v = new Vector<>(DirectoryParser.getAllCourses().keySet());
        DefaultTableModel model = new DefaultTableModel(header, 0);
        for(String s : v){
            Vector<String> temp = new Vector<>();
            onLeft.add(s);
            temp.add(s);
            model.addRow(temp);
        }
        JTable jtable = new JTable(model);
        jtable.getColumn("과목").setWidth(200);
        jtable.setPreferredSize(new Dimension(200,1000));
        JScrollPane scroll1 = new JScrollPane(jtable);
        scroll1.setPreferredSize(new Dimension(200,250));
        scroll1.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));	//서로 가장자리 띄우기 
        model.fireTableDataChanged();
        jtable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jtable.rowAtPoint(evt.getPoint());
                int col = jtable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    setNext(onLeft.get(row));
                }
            }
        });
        JPanel display1 = new JPanel();
        display1.add(scroll1);
        this.add(BorderLayout.WEST, display1);


        JTable jtable2 = new JTable(model2);
        jtable2.getColumn("과목").setWidth(200);
        jtable2.setPreferredSize(new Dimension(200,1000));
        JScrollPane scroll2 = new JScrollPane(jtable2);
        scroll2.setPreferredSize(new Dimension(200,250));
        scroll2.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));	//서로 가장자리 띄우기 
        jtable2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = jtable.rowAtPoint(e.getPoint());
                int col = jtable.columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0) {
                    selected = onRight.get(row);
                }
            }
        });
        this.add(BorderLayout.EAST, scroll2);


        JTable jtable3 = new JTable(model3);
        jtable3.getColumn("미리담기").setWidth(200);
        jtable3.setPreferredSize(new Dimension(200,1000));
        JScrollPane scroll3 = new JScrollPane(jtable3);
        scroll3.setPreferredSize(new Dimension(200,250));
        scroll2.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));	// 서로 가장자리 띄우기 

        this.add(scroll3);

        JPanel bottom = new JPanel();
        bottom.add(back);
        back.addActionListener(e -> {
            new MainView();
            setVisible(false);
        });
        bottom.add(saveButton);
        setMiddle();
        saveButton.addActionListener(e -> {
            if(selected!=null){
                User.getUser().addSaved(selected);
            }
            setMiddle();
        });
        this.add(BorderLayout.SOUTH, bottom);
        setVisible(true);
    }

    public static void main(String[] args) {
        User.selectUser(new User("123","123","123"));
        new PreCourseRegisterView();
    }
}
