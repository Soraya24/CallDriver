package nakthon.soraya.calldriver.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import nakthon.soraya.calldriver.GetDataWhere;
import nakthon.soraya.calldriver.MyConstant;
import nakthon.soraya.calldriver.R;

/**
 * Created by masterUNG on 6/30/2017 AD.
 */

public class ShowDetailOrderFragment extends Fragment {

    private String idUserString;
    private TextView nameTextView, phoneTextView, dateTextView, timeTextView, priceTextView;

    public static ShowDetailOrderFragment newInstance(String strID) {

        ShowDetailOrderFragment showDetailOrderFragment = new ShowDetailOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("idUser", strID);
        showDetailOrderFragment.setArguments(bundle);

        return showDetailOrderFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idUserString = getArguments().getString("idUser");
        Log.d("30JuneV2", "idUserString ที่รับได้ใน Fragment ==> " + idUserString);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_detail_order_layout_fragment,
                container, false);

        //Initial View
        initialView(view);

        changeViewFragment();

        return view;
    }

    public void changeViewFragment() {

        String tag = "30JuneV2";
        MyConstant myConstant = new MyConstant();

        try {


            GetDataWhere getDataWhere = new GetDataWhere(getActivity());
            getDataWhere.execute("id", idUserString, myConstant.getUrlGetIdPassWhereID());
            String strJSON = getDataWhere.get();
            Log.d(tag, "JSON ==> " + strJSON);
            JSONArray jsonArray = new JSONArray(strJSON);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String strID_passenger = jsonObject.getString("id_Passenger");
            Log.d(tag, "id_Passenger ==> " + strID_passenger);

            String timeDate = jsonObject.getString("TimeWork");
            Log.d(tag, "timeDate ==> " + timeDate);
            String[] strings = timeDate.split(" ");
            Log.d(tag, "strings[0] ==> " + strings[0]);
            Log.d(tag, "strings[1] ==> " + strings[1]);


            GetDataWhere getDataWhere1 = new GetDataWhere(getActivity());
            getDataWhere1.execute("id", strID_passenger, myConstant.getUrlGetPassengerWhereID());
            String strJSON1 = getDataWhere1.get();
            Log.d(tag, "JSON2 ==> " + strJSON1);
            JSONArray jsonArray1 = new JSONArray(strJSON1);
            JSONObject jsonObject1 = jsonArray1.getJSONObject(0);

            nameTextView.setText(jsonObject1.getString("Name"));
            phoneTextView.setText(jsonObject1.getString("Phone"));
            dateTextView.setText(strings[0]);
            timeTextView.setText(strings[1]);


        } catch (Exception e) {
            Log.d(tag, "e changeView ==> " + e.toString());
        }

    }

    private void initialView(View view) {
        nameTextView = (TextView) view.findViewById(R.id.txtName);
        phoneTextView = (TextView) view.findViewById(R.id.txtPhone);
        dateTextView = (TextView) view.findViewById(R.id.txtDate);
        timeTextView = (TextView) view.findViewById(R.id.txtTime);
        priceTextView = (TextView) view.findViewById(R.id.txtPrice);
    }
}   // Main Class
