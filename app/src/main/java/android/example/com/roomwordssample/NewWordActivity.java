package android.example.com.roomwordssample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewWordActivity extends AppCompatActivity {
    public static final String ADD_WORD = "com.example.android.roomwordssample.ADDWORD";
    public static final String UPDATE_WORD = "com.example.android.roomwordssample.UPDATEWORD";

    private EditText mEditWordView;
    private Word editWord;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            editWord = (Word) extras.getSerializable("SELECTED_WORD");
            if(editWord != null) {
                mEditWordView.setText(editWord.getWord());
            }
        }

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = mEditWordView.getText().toString().toUpperCase();
                    if(editWord != null) {
                        editWord.setWord(word);
                        replyIntent.putExtra(UPDATE_WORD, editWord);
                        setResult(RESULT_OK, replyIntent);
                    } else {
                        replyIntent.putExtra(ADD_WORD, word);
                        setResult(RESULT_OK, replyIntent);
                    }
                }
                finish();
            }
        });
    }
}
