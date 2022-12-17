public class Course {
    int courseId;
    String name;
    String professor;
    int courseTime;
    String time;

    public Course(){

    }

    public Course(String s){
        String[] splited = s.split("-");
        this.courseId = Integer.parseInt(splited[0]);
        this.name = splited[1];
        this.professor = splited[2];
        this.courseTime = Integer.parseInt(splited[3]);
        this.time = splited[4];
    }

    @Override
    public boolean equals(Object obj) {
        Course c = (Course)obj;
        if(c.courseId != this.courseId) {
            return false;
        }
        if(c.professor.equals(this.professor)) {
            return false;
        }
        if(!c.name.equals(this.name)){
            return false;
        }
        return true;
    }

    @Override
    public String toString(){
        return courseId + "-" + name + "-" + professor + "-" + courseTime + "-" + time;
    }
}
