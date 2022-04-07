package solver;

import org.jetbrains.annotations.NotNull;

public class MyString implements Comparable<MyString>{

    private String myString;
    int length;

    public MyString(String s){
        myString = s;
        length = s.length();
    }

    @Override
    public String toString() {
        return myString;
    }

    public int length(){
        return length;
    }

    @Override
    public int compareTo(@NotNull MyString o) {
        if(this.length()!=o.length()){
            return this.length()-o.length();
        }
        return -(this.toString().compareTo(o.toString()));
    }
}
