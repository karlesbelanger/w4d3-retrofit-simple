package ca.kgb.retrofitsimple;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.kgb.retrofitsimple.entities.Student;
import ca.kgb.retrofitsimple.network.NamesInterface;
import ca.kgb.retrofitsimple.network.RetrofitMagic;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
        private ArrayList<String> arrayList;
        private LibuAdapter mLibuAdapter;
    private static ArrayList<Student> students;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


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

        ArrayList<String> temp = new ArrayList<String>();
        for (Student student :
                students) {
            temp.add(student.getName());

        }
            //arrayList = students;
        arrayList = temp;
            Log.d(TAG, "onCreate: " + arrayList );
//                    new ArrayList<String>();
//            arrayList.add("Karles");
//            arrayList.add("Mike");
//            arrayList.add("Chris");
//            arrayList.add("Aldo");
//            arrayList.add("Edwin");
//            arrayList.add("Libu");

            mLibuAdapter = new LibuAdapter(temp);

            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mRecyclerView.setAdapter(mLibuAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
