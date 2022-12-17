import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    Button button = new Button("로그인");
    JLabel idLabel = new JLabel("아이디");
    JTextField idText = new JTextField(15);
    JLabel pw = new JLabel("<html><br>password</html>");
    JTextField pwText = new JTextField(15);
    LoginView()
    {
        setTitle("로그인");
        setSize(300,400);
        setVisible(true);

        setBounds(100 , 100 , 300 , 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridLayout layout = new GridLayout(5,1);
        setLayout(layout);
        add(idLabel);
        add(idText);
        add(pw);
        add(pwText);
        add(button);
        button.addActionListener(e -> {
            String id = idText.getText();
            String password = pwText.getText();
            for(User u : User.getUserList()){
                if(u.id.equals(id) && u.password.equals(password)){
                    User.selectUser(u);
                    JOptionPane.showMessageDialog(null, u.name + "님 로그인 성공");
                    new MainView();
                    setVisible(false); // 창 안보이게 하기
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "로그인 실패");

        });
        setVisible(true);
    }
}