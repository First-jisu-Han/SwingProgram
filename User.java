import java.io.*;
import java.util.ArrayList;

public class User {
    public String id;
    public String password;
    public String name;
    public ArrayList<Course> saved = new ArrayList<>();
    private ArrayList<Course> registered = new ArrayList<>();
    public void addSaved(Course c){
        if(!saved.contains(c) && !registered.contains(c)){
            saved.add(c);
        }
    }
    public User(String id, String password, String name){
        this.id = id;
        this.password = password;
        this.name = name;
    }
    public static ArrayList<User> getUserList(){
        ArrayList<User> list = new ArrayList<>();
        list.add(new User("1","1","1"));
        list.add(new User("hello","hello","김안녕"));
        return list;
    }
    public static User user;
    public ArrayList<Course> getRegistered(){
        return this.registered;
    }
    public static void selectUser(User s){
        user = s;
        s.init();
    }
    public static User getUser(){
        user.init();
        return user;
    }

    public void init(){
        BufferedReader reader = null;
        try {
            File f = new File(user.name + ".txt");
            if(!f.exists()){
                return;
            }
            FileReader temp = new FileReader(f);
            reader = new BufferedReader(temp);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String str;
        if(registered.size() == 0){
            while (true) {
                try {
                    if (!((str = reader.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Course c = new Course(str);
                registered.add(c);
            }

            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void register(){
        registered.addAll(saved);
        saved.clear();
        String s = "";
        for(Course course : registered){
            s += course.toString();
        }
        File file = new File(user.name + ".txt");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(s);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
