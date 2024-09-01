
public class User {
    public long ID;
    public String name;

    public User(long ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public boolean isValid(){
        if(this.name == null || this.name.equals("")){
            return false;
        }
        return true;
    }
}
