package yazdaniscodelab.ondemandfinalproject.CatActivityForJobSeeker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import yazdaniscodelab.ondemandfinalproject.CatActivityForJobSeeker.CatActivityDetailsJobPOST.CatHomeServiceDetailsActivity;
import yazdaniscodelab.ondemandfinalproject.JobDetailsActivity;
import yazdaniscodelab.ondemandfinalproject.Model.PostJob;
import yazdaniscodelab.ondemandfinalproject.R;

public class CatHomeServiceActivity extends AppCompatActivity {


    //This File is for Category home service job post.,.,from this activity if user click any of the job then it will redirect to Cathomeservicedetails activity

    private DatabaseReference mHomeServiewDb;

    private Toolbar toolbar;

    //recycler
    private RecyclerView mRecyclerHomeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_home_service);

        toolbar=findViewById(R.id.cat_home_service);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home Service Job");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mRecyclerHomeService=findViewById(R.id.recycler_cat_home_service);

        //databse

        mHomeServiewDb= FirebaseDatabase.getInstance().getReference().child("Home_public");


        //Recycler Home Service

        LinearLayoutManager layoutManagerHomeService=new LinearLayoutManager(getApplicationContext());
        layoutManagerHomeService.setReverseLayout(true);
        layoutManagerHomeService.setStackFromEnd(true);

        mRecyclerHomeService.setHasFixedSize(true);
        mRecyclerHomeService.setLayoutManager(layoutManagerHomeService);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<PostJob,MyViewHolder>adapter=new FirebaseRecyclerAdapter<PostJob, MyViewHolder>
                (
                        PostJob.class,
                        R.layout.cat_item_layout_design,
                        MyViewHolder.class,
                        mHomeServiewDb

                ) {
            @Override
            protected void populateViewHolder(MyViewHolder viewHolder,final PostJob model, int position) {

                viewHolder.setDate(model.getDate());
                viewHolder.setJobTitle(model.getTitle());
                viewHolder.setJobDescription(model.getDescription());
                viewHolder.setBudget(model.getBudget());

                viewHolder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent=new Intent(getApplicationContext(),CatHomeServiceDetailsActivity.class);


                        intent.putExtra("budget",model.getBudget());
                        intent.putExtra("phone",model.getPhone());
                        intent.putExtra("title",model.getTitle());
                        intent.putExtra("address",model.getAddress());
                        intent.putExtra("startDate",model.getStartDate());
                        intent.putExtra("endData",model.getEndDate());
                        intent.putExtra("description",model.getDescription());

                        startActivity(intent);


                    }
                });

            }
        };

        mRecyclerHomeService.setAdapter(adapter);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        View myview;
        public MyViewHolder(View itemView) {
            super(itemView);
            myview=itemView;
        }

        public void setDate(String date){
            TextView mDate=myview.findViewById(R.id.job_date);
            mDate.setText(date);
        }

        public void setJobTitle(String title){
            TextView mTitle=myview.findViewById(R.id.job_title);
            mTitle.setText(title);
        }

        public void setJobDescription(String description){
            TextView mDescription=myview.findViewById(R.id.job_description_cat);
            mDescription.setText(description);
        }

        public void setBudget(String budget){
            TextView mBudget=myview.findViewById(R.id.job_budget);
            mBudget.setText("TK."+budget);
        }

    }

}
