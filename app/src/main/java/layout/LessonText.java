package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pashkobohdan.learnjava.R;
import com.pashkobohdan.learnjava.library.dateBaseHelper.ReadData;
import com.pashkobohdan.learnjava.library.lessonsFirebaseWorker.Theme;
import com.pashkobohdan.learnjava.library.lessonsWorker.LessonViews;

public class LessonText extends Fragment {
    private static final String ARG_PARAM1 = "themeName";
    private String themeName;

    Theme currentTheme;
    LinearLayout linearLayout;

    public LessonText() {
        // Required empty public constructor
    }

    public static LessonText newInstance(String themeName) {
        LessonText fragment = new LessonText();
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

        for (Theme theme : ReadData.getThemesList()) {
            if (theme.getName().equals(themeName)) {
                currentTheme = theme;
                break;
            }
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lesson_text, container, false);

        linearLayout = (LinearLayout) rootView.findViewById(R.id.linear_with_lesson_text);

        LessonViews lessonViews = new LessonViews(currentTheme, getContext());

        LinearLayout.LayoutParams params;
        for (View view : lessonViews.getViews()) {

            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            float d = getContext().getResources().getDisplayMetrics().density;
            params.setMargins((int) (5 * d), (int) (3 * d), (int) (5 * d), 0);
            view.setLayoutParams(params);

            linearLayout.addView(view);
        }

        return rootView;
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
