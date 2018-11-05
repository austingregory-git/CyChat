package edu.iastate.linux.git.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import edu.iastate.linux.git.ui.Utils.LetterImageView;

public class ContactsActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private RecyclerView rv;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        initButtonNavigation();
        initViews();
        initListView();

    }

    private void initButtonNavigation() {
        ImageButton homeB = (ImageButton) findViewById(R.id.homeButton);
        ImageButton contactsB = (ImageButton) findViewById(R.id.contactsButton);
        ImageButton notifsB = (ImageButton) findViewById(R.id.notificationsButton);

        homeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(ContactsActivity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        contactsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(ContactsActivity.this, ContactsActivity.class);
                startActivity(i2);
            }
        });

        notifsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(ContactsActivity.this, NotificationsActivity.class);
                startActivity(i3);
            }
        });
    }

    private void initViews() {
        rv = (RecyclerView) findViewById(R.id.recyclerViewContacts);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
    }

    private void initListView() {
        String[] contactNames = getResources().getStringArray(R.array.contactNames);
        String[] contactDet = getResources().getStringArray(R.array.contactDet);

        ContactsAdapter mAdapter = new ContactsAdapter(ContactsActivity.this, contactNames, contactDet);
        rv.setAdapter(mAdapter);
    }

    public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

        private Context context;
        //private LayoutInflater lf;
        //private TextView title, desc;
        private LetterImageView liv;
        private String[] contactNames, contactDet;

        public ContactsAdapter(Context context, String[] names, String[] msg) {
            this.context = context;
            this.contactNames = names;
            this.contactDet = msg;
        }

        @NonNull
        @Override
        public ContactsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // inflate the item Layout
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
            MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder

            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.name.setText(contactNames[position]);
            holder.det.setText(contactDet[position]);
            holder.liv.setOval(true);
            holder.liv.setLetter(contactNames[position].charAt(0));
        }

        @Override
        public int getItemCount() {
            return contactNames.length;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView name;// init the item view's
            TextView det;
            LetterImageView liv;
            public MyViewHolder(View itemView) {
                super(itemView);
                // get the reference of item view's
                name = (TextView) itemView.findViewById(R.id.textContactName);
                det = (TextView) itemView.findViewById(R.id.textContactDet);
                liv = (LetterImageView) itemView.findViewById(R.id.imageContact);

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topright_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settings:
                Intent i1 = new Intent(ContactsActivity.this, SettingsActivity.class);
                startActivity(i1);
                return(true);
            case R.id.options:
                Intent i2 = new Intent(ContactsActivity.this, OptionsActivity.class);
                startActivity(i2);
                return(true);
            case R.id.map:
                Intent i3 = new Intent(ContactsActivity.this, GoogleMapActivity.class);
                startActivity(i3);
                return(true);
            case R.id.schedule:
                Intent i4 = new Intent(ContactsActivity.this, ScheduleActivity.class);
                startActivity(i4);
                return(true);
            case R.id.exit:
                finish();
                System.exit(0);
                return(true);
            case R.id.log_out:
                Intent i5 = new Intent(ContactsActivity.this, LoginActivity.class);
                startActivity(i5);
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }
}
