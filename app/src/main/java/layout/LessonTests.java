package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pashkobohdan.learnjava.R;
import com.pashkobohdan.learnjava.library.dateBaseHelper.ReadData;
import com.pashkobohdan.learnjava.library.lessonsFirebaseWorker.Test;
import com.pashkobohdan.learnjava.library.lessonsWorker.TestParser;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LessonTests extends Fragment {
    private static final String ARG_PARAM1 = "themeName";
    private String themeName;

    private List<Test> tests;
    private Queue<TestParser> testParsers;
    private TestParser nowTest;

    private TextView testNumber, testType, testText;
    private RadioButton var1, var2, var3, var4;

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
        if (getArguments() != null) {
            themeName = getArguments().getString(ARG_PARAM1);
        }

        ReadData.refreshTests();
        tests = ReadData.getTestByTheme(themeName);

        Toast.makeText(getContext(), "Theme : " + themeName + ",  size : " + tests.size(), Toast.LENGTH_SHORT).show();
        testParsers = new ArrayDeque<>();
        for (Test test : tests) {
            testParsers.add(TestParser.newInstance(test));
        }

        Toast.makeText(getContext(), "Size : " + testParsers.size(), Toast.LENGTH_SHORT).show();
        //deleting that test, which end now
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lesson_tests, container, false);

        testNumber = (TextView) rootView.findViewById(R.id.test_number);
        testType = (TextView) rootView.findViewById(R.id.type_of_task);
        testText = (TextView) rootView.findViewById(R.id.test_text);

        var1 = (RadioButton) rootView.findViewById(R.id.variant1);
        var2 = (RadioButton) rootView.findViewById(R.id.variant2);
        var3 = (RadioButton) rootView.findViewById(R.id.variant3);
        var4 = (RadioButton) rootView.findViewById(R.id.variant4);

        showNextTest();

        return rootView;
    }

    private void showNextTest() {
        nowTest = testParsers.remove();

        testNumber.setText("Test # " + (tests.size() - testParsers.size()) + "/" + tests.size());
        testType.setText(nowTest.getTestType());
        testText.setText(nowTest.getTestText());

        var1.setText(nowTest.getAnswers().get(0));
        var2.setText(nowTest.getAnswers().get(1));
        var3.setText(nowTest.getAnswers().get(2));
        var4.setText(nowTest.getAnswers().get(3));
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
