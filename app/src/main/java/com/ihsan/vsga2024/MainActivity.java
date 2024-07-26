package com.ihsan.vsga2024;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "catatan.txt";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText = findViewById(R.id.editText);
    }

    private void buatFile() {
        File file = new File(getFilesDir(), FILENAME);
        try (FileOutputStream fos = new FileOutputStream(file, false)) {
            file.createNewFile();
            fos.write(editText.getText().toString().getBytes());
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bacaFile() {
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    text.append(line).append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            editText.setText(text.toString().trim());
        } else {
            editText.setText("");
        }
    }

    private void hapusFile() {
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()) {
            file.delete();
            editText.setText("");
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.button1) {
            buatFile();
        } else if (view.getId() == R.id.button2) {
            bacaFile();
        } else if (view.getId() == R.id.button3) {
            hapusFile();
        }
    }
}
