import javax.swing.*;
import java.awt.*;

public class MainView  extends JFrame {
    Button preRegisterCourse = new Button("미리담기");
    Button registerCourse = new Button("수강신청");
    Button CourseList = new Button("수강신청리스트");
    Button back = new Button("뒤로 가기");
    MainView(){
        this.setTitle(User.getUser().name + "님 환영합니다.");
        Container container = this.getContentPane();
        this.setContentPane(container);
        setSize(300,400);
        setVisible(true);

        setBounds(100 , 100 , 300 , 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        JPanel display1 = new JPanel();
        display1.add(back);
        back.addActionListener(e -> {
            User.selectUser(null);
            new LoginView();
            setVisible(false);
        });
        display1.add(preRegisterCourse);
        preRegisterCourse.addActionListener(e -> {
            new PreCourseRegisterView();
            setVisible(false);
        });
        display1.add(registerCourse);
        registerCourse.addActionListener(e -> {
            new CourseRegisterView();
            setVisible(false);
        });
        display1.add(CourseList);
        CourseList.addActionListener(e -> {
            new CourseView();
            setVisible(false);
        });
        container.add(display1);
    }
}
