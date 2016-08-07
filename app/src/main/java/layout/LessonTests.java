package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pashkobohdan.learnjava.R;
import com.pashkobohdan.learnjava.library.dateBaseHelper.ReadData;
import com.pashkobohdan.learnjava.library.lessonsFirebaseWorker.Test;
import com.pashkobohdan.learnjava.library.lessonsFirebaseWorker.Theme;
import com.pashkobohdan.learnjava.library.lessonsWorker.PreferencesWorker;
import com.pashkobohdan.learnjava.library.lessonsWorker.TestParser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LessonTests extends Fragment {
    private static final String ARG_PARAM1 = "themeName";
    private String themeName;

    private List<Test> tests;
    private List<TestParser> testParsers;
    private TestParser nowTest;

    private TextView testNumber, testType, testText;
    private RadioButton variants[];
    private Button checkAnswer;

    private Theme currentTheme;
    private int themeNumber;

    private int currentTestNumber;

    public LessonTests() {
        // Required empty public constructor
    }

    public static LessonTests newInstance(String themeName) {
        LessonTests fragment = new LessonTests();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, themeName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
            return;
        }
        themeName = getArguments().getString(ARG_PARAM1);

        for (int i = 0; i < ReadData.getThemesList().size(); i++) {
            Theme theme = ReadData.getThemesList().get(i);
            if (theme.getName().equals(themeName)) {
                currentTheme = theme;
                themeNumber = i;
            }
        }

        tests = ReadData.getTestByTheme(themeName);

        testParsers = new ArrayList<>();

        currentTestNumber = PreferencesWorker.getSuccessTestsCount(currentTheme) % tests.size();
        for (Test test : tests) {
            testParsers.add(TestParser.newInstance(test));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lesson_tests, container, false);

        testNumber = (TextView) rootView.findViewById(R.id.test_number);
        testType = (TextView) rootView.findViewById(R.id.type_of_task);
        testText = (TextView) rootView.findViewById(R.id.test_text);

        variants = new RadioButton[4];
        variants[0] = (RadioButton) rootView.findViewById(R.id.variant1);
        variants[1] = (RadioButton) rootView.findViewById(R.id.variant2);
        variants[2] = (RadioButton) rootView.findViewById(R.id.variant3);
        variants[3] = (RadioButton) rootView.findViewById(R.id.variant4);

        checkAnswer = (Button) rootView.findViewById(R.id.checkAnswer);

        showNextTest();

        return rootView;
    }

    private void showNextTest() {
        nowTest = testParsers.get(currentTestNumber);

        testNumber.setText("Test # " + (currentTestNumber + 1) + "/" + tests.size());
        testType.setText(nowTest.getTestType());
        testText.setText(nowTest.getTestText());

        variants[0].setText(nowTest.getAnswers().get(0));
        variants[1].setText(nowTest.getAnswers().get(1));
        variants[2].setText(nowTest.getAnswers().get(2));
        variants[3].setText(nowTest.getAnswers().get(3));

        checkAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int answer = -1;
                for (int i = 0; i < variants.length; i++) {
                    if (variants[i].isChecked()) {
                        answer = i;
                    }
                }

                if (answer == -1) {
                    Toast.makeText(getContext(), "Check any answer !", Toast.LENGTH_SHORT).show();
                } else {
                    if (answer == nowTest.getAnswer()) {
                        Toast.makeText(getContext(), "True !", Toast.LENGTH_SHORT).show();
                        currentTestNumber++;

                        if (currentTestNumber <= testParsers.size()) {
                            PreferencesWorker.plusOneTest(currentTheme);
                        }
                        if (currentTestNumber == testParsers.size()) {
                            Toast.makeText(getContext(), "Next theme are open !", Toast.LENGTH_SHORT).show();

                            if (themeNumber == ReadData.getThemesByPart(currentTheme.getPart()).size() - 1) {
                                PreferencesWorker.openPart(
                                        ReadData.getPartsList().get(
                                                ReadData.getPartsList().indexOf(
                                                        ReadData.getPartByName(currentTheme.getPart())
                                                )
                                                        + 1
                                        )
                                );

                                Toast.makeText(getContext(), "Next Part are open !", Toast.LENGTH_SHORT).show();
                            } else {
                                PreferencesWorker.openTheme(ReadData.getThemesByPart(currentTheme.getPart()).get(themeNumber + 1));
                            }
                        }

                        currentTestNumber = currentTestNumber % testParsers.size();
                        showNextTest();
                    } else {
                        Toast.makeText(getContext(), "False !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
