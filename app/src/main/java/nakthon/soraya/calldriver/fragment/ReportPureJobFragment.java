package nakthon.soraya.calldriver.fragment;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Locale;

import nakthon.soraya.calldriver.GetAllData;
import nakthon.soraya.calldriver.GetDataWhere;
import nakthon.soraya.calldriver.MyConstant;
import nakthon.soraya.calldriver.R;
import nakthon.soraya.calldriver.ReportPureJobAdapter;
import nakthon.soraya.calldriver.ShowDetailOrderActivity;

/**
 * Created by masterUNG on 6/26/2017 AD.
 */

public class ReportPureJobFragment extends Fragment {

    //Explicit
    private TextView[] textViews = new TextView[7];
    private int[] ints = new int[]{R.id.txtColumn1, R.id.txtColumn2, R.id.txtColumn3,
            R.id.txtColumn4, R.id.txtColumn5, R.id.txtColumn6, R.id.txtColumn7};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_repord_purejob, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Show Column
        showColumn();

        //Create ListView
        createListView();

    }


    private void createListView() {

        ListView listView = getView().findViewById(R.id.livPureJob);
        String tag = "27JuneV1";
        MyConstant myConstant = new MyConstant();
        String urlPHP_getAllPassenger = myConstant.getUrlGetAllPureJom();
        String urlPHP_getPassengerWhereID = myConstant.getUrlGetPassengerWhereID();


        String[] strings = new String[]{"Test1", "Test2", "Test3", "dffd", "dfdf", "dfdff"};
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, strings);
        listView.setAdapter(stringArrayAdapter);

        try {

            //Get All ID from passengerTABLE
            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(urlPHP_getAllPassenger);
            String jsonAllPassenger = getAllData.get();
            Log.d(tag, "JSPN ALL Passenger ==> " + jsonAllPassenger);

            JSONArray jsonArray = new JSONArray(jsonAllPassenger);
            int index = jsonArray.length();
            Log.d(tag, "jsonArray.length ==> " + index);
            final String[] valueColumn1Strings1 = new String[jsonArray.length()];
            Log.d(tag, "value1.lentth ==> " + valueColumn1Strings1.length);

            String[] valueColumn1Strings2 = new String[jsonArray.length()];
            String[] valueColumn1Strings3 = new String[jsonArray.length()];
            String[] valueColumn1Strings4 = new String[jsonArray.length()];
            String[] valueColumn1Strings5 = new String[jsonArray.length()];
            String[] valueColumn1Strings6 = new String[jsonArray.length()];
            String[] valueColumn1Strings7 = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i += 1) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                valueColumn1Strings1[i] = jsonObject.getString("id");
                valueColumn1Strings2[i] = findNameAndPhone(0, jsonObject.getString("id_Passenger"));
                valueColumn1Strings3[i] = findNameAndPhone(1, jsonObject.getString("id_Passenger"));
                valueColumn1Strings4[i] = findNameAddress(jsonObject.getString("LatStart"), jsonObject.getString("LngStart"));

                Log.d(tag, "Receive Return ==> " + findLastDestination(jsonObject.getString("Job")));
                valueColumn1Strings5[i] = findLastDestination(jsonObject.getString("Job"));

                valueColumn1Strings6[i] = jsonObject.getString("TimeWork");
                valueColumn1Strings7[i] = "";

                //For Show Log
                Log.d(tag, "valueColumn5[" + i + "] ==> " + valueColumn1Strings5[i]);
//
            }   // for

            ReportPureJobAdapter reportPureJobAdapter = new ReportPureJobAdapter(getActivity(),
                    valueColumn1Strings1, valueColumn1Strings2, valueColumn1Strings3,
                    valueColumn1Strings4, valueColumn1Strings5,
                    valueColumn1Strings6, valueColumn1Strings7);
            listView.setAdapter(reportPureJobAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent intent = new Intent(getActivity(), ShowDetailOrderActivity.class);

                    Log.d("30JuneV2", "You Click id ==> " + valueColumn1Strings1[i]);
                    intent.putExtra("idUser", valueColumn1Strings1[i]);

                    startActivity(intent);

                }
            });


        } catch (Exception e) {
            Log.d(tag, "e ==> " + e.toString());
        }

    }   // create ListView

    private String findLastDestination(String job) {

        String result = null;
        String tag = "29JuneV1";

        Log.d(tag, "Job Pure ที่อ่านได้จาก JSON ==> " + job);

        result = job.replace("[", "");
        result = result.replace("]", "");

        Log.d(tag, "result ที่ตัด [] ออกแล้่ว ==> " + result);

        String[] strings = result.split(",");
        for (int i = 0; i < strings.length; i += 1) {
            Log.d(tag, "strings[" + i + "] ==> " + strings[i]);
        }
        result = strings[strings.length - 1];
        Log.d(tag, "ค่า result return ==> " + result);

        try {

            MyConstant myConstant = new MyConstant();
            GetDataWhere getDataWhere = new GetDataWhere(getActivity());
            getDataWhere.execute("id", result, myConstant.getUrlGetNameWhereID());
            String strJSON = getDataWhere.get();
            Log.d(tag, "JSON ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            result = jsonObject.getString("Name");

            if (checkUnknow(result)) {

                String strLat = jsonObject.getString("Lat");
                String strLng = jsonObject.getString("Lng");

                result = findNameAddress(strLat, strLng);

            }

            return result;

        } catch (Exception e) {
            Log.d(tag, "e findLastLocation ==> " + e.toString());
            return null;
        }


    }

    private boolean checkUnknow(String result) {

        String s = result.substring(0, 6);
        Log.d("30JuneV1", "s ==> " + s);

        if (s.equals("Unknow")) {
            return true;
        } else {
            return false;
        }


    }

    private String findNameAddress(String latStart, String lngStart) {

        try {

            String result = null;
            Geocoder geocoder = new Geocoder(getActivity(), Locale.ENGLISH);
            List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(latStart),
                    Double.parseDouble(lngStart), 1);

            if (addresses != null) {
                Address returnAddress = addresses.get(0);
                StringBuilder stringBuilder = new StringBuilder("Address:\n");
                for (int i = 0; i < returnAddress.getMaxAddressLineIndex(); i += 1) {
                    stringBuilder.append(returnAddress.getAddressLine(i)).append("\n");
                }   // for
                result = stringBuilder.toString();
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String findNameAndPhone(int index, String id_passenger) {

        String result = null;
        MyConstant myConstant = new MyConstant();

        try {

            GetDataWhere getDataWhere = new GetDataWhere(getActivity());
            getDataWhere.execute("id", id_passenger, myConstant.getUrlGetPassengerWhereID());

            JSONArray jsonArray = new JSONArray(getDataWhere.get());
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            switch (index) {
                case 0:
                    result = jsonObject.getString("Name");
                    break;
                case 1:
                    result = jsonObject.getString("Phone");
                    break;
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    private void showColumn() {
        MyConstant myConstant = new MyConstant();
        String[] columnStrings = myConstant.getColumnReportPureJobFragmentStrings();
        for (int i = 0; i < ints.length; i += 1) {
            textViews[i] = getView().findViewById(ints[i]);
            textViews[i].setText(columnStrings[i]);
        }
    }
}   // Main Class
