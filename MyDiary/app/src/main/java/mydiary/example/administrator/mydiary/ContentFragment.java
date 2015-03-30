package mydiary.example.administrator.mydiary;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "content";
    private static final String ARG_PARAM2 = "date";
    private static final String ARG_PARAM3 = "days";
    private static final String ARG_PARAM4 = "winder";

    // TODO: Rename and change types of parameters
    private String content;
    private String date;
    private String days;
    private String winder;

    private TextView tv_content,tv_year, tv_days, tv_winder;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ContentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContentFragment newInstance(String content, String date,String days,String winder) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, content);
        args.putString(ARG_PARAM2, date);
        args.putString(ARG_PARAM3, days);
        args.putString(ARG_PARAM4, winder);
        fragment.setArguments(args);
        return fragment;
    }

    public ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            content = getArguments().getString(ARG_PARAM1);
            date = getArguments().getString(ARG_PARAM2);
            days = getArguments().getString(ARG_PARAM3);
            winder = getArguments().getString(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        tv_content =(TextView)view.findViewById(R.id.tv_content);
        tv_year=(TextView)view.findViewById(R.id.tv_year);
        tv_days=(TextView)view.findViewById(R.id.tv_days);
        tv_winder=(TextView)view.findViewById(R.id.tv_winder);

        tv_year.setText(date);
        tv_days.setText(days);
        tv_content.setText(content);
        tv_winder.setText("Weather：" + winder);

        return view;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


}
