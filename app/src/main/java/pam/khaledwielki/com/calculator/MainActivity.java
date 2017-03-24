package pam.khaledwielki.com.calculator;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.text.DecimalFormat;

import pam.khaledwielki.com.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private double valueOne = Double.NaN;
    private double valueTwo;

    private static final String substraction = "-";
    private static final String addition = "+";
    private static final String division = "/";
    private static final String multiplication = "*";
    private static final String changeSign = "+/-";
    private static final String clear = "clear";

    private String CURRENT_ACTION;

    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        decimalFormat = new DecimalFormat("#.########");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.ButtonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + ".");
            }
        });

        binding.Button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "0");
            }
        });

        binding.Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "1");
            }
        });

        binding.Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "2");
            }
        });

        binding.Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "3");
            }
        });

        binding.Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "4");
            }
        });

        binding.Button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "5");
            }
        });

        binding.Button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "6");
            }
        });

        binding.Button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "7");
            }
        });

        binding.Button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "8");
            }
        });

        binding.Button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "9");
            }
        });

        binding.ButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = addition;
                binding.infoTextView.setText(decimalFormat.format(valueOne) + "+");
                binding.editText.setText(null);
            }
        });

        binding.ButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = substraction;
                binding.infoTextView.setText(decimalFormat.format(valueOne) + "-");
                binding.editText.setText(null);
            }
        });

        binding.ButtonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = division;
                binding.infoTextView.setText(decimalFormat.format(valueOne) + "/");
                binding.editText.setText(null);
            }
        });

        binding.ButtonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = multiplication;
                binding.infoTextView.setText(decimalFormat.format(valueOne) + "*");
                binding.editText.setText(null);
            }
        });

        binding.ButtonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                binding.infoTextView.setText(decimalFormat.format(valueOne));
                valueOne = Double.NaN;
                binding.editText.setText(null);
            }
        });

        binding.ButtonChangeSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = changeSign;
                binding.infoTextView.setText(decimalFormat.format(valueOne));
            }
        });

        binding.ButtonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((binding.editText.length() > 0) || binding.infoTextView.length() > 0) {
                    computeCalculation();
                    CURRENT_ACTION = clear;
                    valueOne = Double.NaN;
                    valueTwo = Double.NaN;
                    binding.infoTextView.setText(null);
                    binding.editText.setText(null);
                }
            }
        });

        binding.ButtonBackspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    private void computeCalculation() {
        if (!Double.isNaN(valueOne)) {
            valueTwo = Double.parseDouble(binding.editText.getText().toString());
            binding.editText.setText(null);

            if (CURRENT_ACTION == substraction) {
                valueOne = this.valueOne - valueTwo;
            } else if (CURRENT_ACTION == addition) {
                valueOne = this.valueOne + valueTwo;
            } else if (CURRENT_ACTION == multiplication) {
                valueOne = this.valueOne * valueTwo;
            } else if (CURRENT_ACTION == division) {
                if(!(valueTwo == 0)) {
                    valueOne = this.valueOne / valueTwo;
                }
                else {
                    Context context = getApplicationContext();
                    CharSequence text = "Nie mozna dzielic przez 0";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    valueTwo = Double.NaN;
                }
            }
            else if (CURRENT_ACTION == changeSign) {
                valueOne = -(this.valueOne);
            }
            else if (CURRENT_ACTION == clear) {
                valueOne = Double.NaN;
                valueTwo = Double.NaN;
            }
        }
        else {
            try {
                valueOne = Double.parseDouble(binding.editText.getText().toString());
            }
            catch(Exception e) {}
        }
    }
}
