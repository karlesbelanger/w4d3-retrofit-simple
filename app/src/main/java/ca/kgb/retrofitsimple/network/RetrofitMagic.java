package ca.kgb.retrofitsimple.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.kgb.retrofitsimple.entities.Student;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 8/10/2016.
 */
public class RetrofitMagic {

    private static List<Student> students;

    public static void main(String...args){
        System.out.println("hello world");
        //3. Creating Retrofit builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //4. Instantiating the interface via the Retrofit Object
        NamesInterface namesInterface = retrofit.create(NamesInterface.class);

        //5. Setting up the method to be called from the interface
        Call<ArrayList<Student>> studentsCall = namesInterface.retrieveStudents();

        try {
            //6 Executing the Retrofit call
            students = studentsCall.execute().body();
            for (Student student:
                 students) {
                System.out.println(student.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  ArrayList<String> getStudents() {
        if (students != null) {
            ArrayList<String> temp = new ArrayList<String>();
            for (Student student :
                    students) {
                temp.add(student.getName());

            }
            return temp;

        }
        return null;
    }

}
