package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pashkobohdan.learnjava.R;

public class LessonTests extends Fragment {
    private static final String ARG_PARAM1 = "themeName";
    private String themeName;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lesson_tests, container, false);
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
