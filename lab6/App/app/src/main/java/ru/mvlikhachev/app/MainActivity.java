package ru.mvlikhachev.app;

import java.util.Random;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private int mNumber;
    private int mGuessCount = 0;
    private TextView mGuessResultTextView;
    private EditText mGuessEditText;
    private Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Random random = new Random();
        mNumber = random.nextInt(100) + 1;

        mGuessResultTextView = findViewById(R.id.guess_result_text_view);
        mGuessEditText = findViewById(R.id.guess_edit_text);
        mSubmitButton = findViewById(R.id.submit_button);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Получить введенное пользователем число
                String guessText = mGuessEditText.getText().toString();
                int guess = Integer.parseInt(guessText);


                mGuessCount++;

                // Проверить, угадал ли пользователь число
                if (guess == mNumber) {
                    mGuessResultTextView.setText("Поздравляем! Вы угадали число за " + mGuessCount + " попыток.");
                    mSubmitButton.setEnabled(false);
                } else if (guess < mNumber) {
                    mGuessResultTextView.setText("Загаданное число больше.");
                } else if (guess > mNumber) {
                    mGuessResultTextView.setText("Загаданное число меньше.");
                }
            }
        });
    }

    public void restartGame(View view) {
        // Сбросить все значения и начать новую игру
        Random random = new Random();
        mNumber = random.nextInt(100) + 1;
        mGuessCount = 0;
        mGuessResultTextView.setText("");
        mGuessEditText.setText("");
        mSubmitButton.setEnabled(true);
    }
}