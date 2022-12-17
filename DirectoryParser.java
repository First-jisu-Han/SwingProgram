import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryParser {
    public static HashMap<String, ArrayList<Course>> courses = null;
    public static Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }
    
    
    @SuppressWarnings("resource")
	private static HashMap<String, ArrayList<Course>> getAllCoursesInternal(){
        String directory = "directory";
        Set<String> files = listFilesUsingJavaIO(directory);
        HashMap<String, ArrayList<Course>> response = new HashMap<>();
        for(String s : files){
            ArrayList<Course> temp = new ArrayList<>();
            //이전 폴더 무시
            if(s.equals(".") || s.equals("..")) {
                continue;
            }
            File f = new File(directory + "/" + s);
            if(f.exists() && !f.isDirectory()){
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(
                            new FileReader(f)
                    );
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                String str;
                while (true) {
                    try {
                        if ((str = reader.readLine()) == null) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Course c = new Course();
                    str = str.trim();
                    str = str.trim().replaceAll(" +", " ");
                    //빈칸 무시
                    if("".equals(str)){
                        continue;
                    }
                    String[] splited = str.split(" ");
                    if(splited.length > 0){
                        try {
                            c.courseId = Integer.parseInt(splited[0]);
                        }catch (NumberFormatException e){
                            //에러나면 무시
                            continue;
                        }
                    }
                    if(splited.length < 3){
                        continue;
                    }
                    c.name = splited[1];
                    c.professor = splited[2];
                    if(splited.length >= 4) {
                        try {
                            c.courseTime = Integer.parseInt(splited[3]);
                        }catch (Exception e){
                            c.time = splited[3];
                        }
                    }
                    if(splited.length >= 5) {
                        c.time = splited[4];
                    }
                    temp.add(c);
                }
            }
            response.put(s,temp);
        }
        return response;
    }
    public static HashMap<String, ArrayList<Course>> getAllCourses(){
        if(courses == null){
            courses = getAllCoursesInternal();
        }
        return courses;
    }
    public static void main(String[] args) {
        HashMap<String, ArrayList<Course>> courses = getAllCourses();
        for(String s : courses.keySet()){
            for(Course c : courses.get(s)){
                System.out.println(c.name);
                System.out.println(c.professor);
                System.out.println(c.time);
                System.out.println(c.courseTime);
            }
        }
    }
}
