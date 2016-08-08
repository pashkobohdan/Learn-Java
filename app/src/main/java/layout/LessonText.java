package layout;

import android.content.Context;
import android.content.res.Resources;
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
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(dpToPx(3), dpToPx(10),dpToPx(3),dpToPx(2));

        for (View view : lessonViews.getViews()) {
            view.setLayoutParams(params);

            linearLayout.addView(view);
        }

        return rootView;
    }

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
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
