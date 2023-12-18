package com.example.compterendu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final int MAX = 20;
    private final String[] contacts = new String[MAX];
    private EditText editTextName;
    private EditText editTextNumero;
    private Button addButton;
    private ListView listViewContacts;
    private ArrayAdapter<String> adapter;
    private int lastPosition = 0;
    private String enteredText;
    private String enteredNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = findViewById(R.id.editTextName);
        editTextNumero = findViewById(R.id.editTextNumero);
        addButton = findViewById(R.id.addButton);
        listViewContacts = findViewById(R.id.listViewContacts);

        Arrays.fill(contacts, "");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, contacts) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);

                int contactIndex = position * 2;
                if (contactIndex + 1 < MAX) {
                    text1.setText(contacts[contactIndex]);
                    text2.setText(contacts[contactIndex + 1]);
                }

                return view;
            }
        };

        listViewContacts.setAdapter(adapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredText = editTextName.getText().toString();
                enteredNumber = editTextNumero.getText().toString();
                contacts[lastPosition] = enteredText;
                contacts[lastPosition + 1] = enteredNumber;
                lastPosition += 2;
                adapter.notifyDataSetChanged();
                editTextName.setText("");
                editTextNumero.setText("");
            }
        });

        listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String retrievedName = contacts[position * 2];
                String retrievedNumber = contacts[position * 2 + 1];
                Intent intent = new Intent(MainActivity.this, CallActivity.class);
                intent.putExtra("name", retrievedName);
                intent.putExtra("number", retrievedNumber);
                startActivity(intent);
            }
        });
    }
}
