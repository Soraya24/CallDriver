package nakthon.soraya.calldriver.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import nakthon.soraya.calldriver.GetAllData;
import nakthon.soraya.calldriver.GetDataWhere;
import nakthon.soraya.calldriver.MapsActivity;
import nakthon.soraya.calldriver.MyConstant;
import nakthon.soraya.calldriver.R;

/**
 * Created by masterUNG on 6/26/2017 AD.
 */

public class SearchViewFragment extends ListFragment{

    private EditText editText;
    private ListView listView;
    private String[] listview_names;
    private ArrayList<String> array_sort;
    private int textlength = 0;
    private MyConstant myConstant;
    private String[] columnPassengerStrings;
    private String tag = "18AprilV2";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_service, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Setup Constant
        setupConstant();

        //Create Search View
        createSearchView();

    }

    private void createSearchView() {
        try {

            editText = (EditText) getView().findViewById(R.id.edtSearch);
            listView = (ListView) getView().findViewById(android.R.id.list);

            //Get Data from passengerTABLE
            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(myConstant.getUrlGetPassenger());
            String strJSON = getAllData.get();
            Log.d(tag, "JSON ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            listview_names = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                listview_names[i] = jsonObject.getString(columnPassengerStrings[2]);
            }   // for


            array_sort = new ArrayList<String>(Arrays.asList(listview_names));
            setListAdapter(new bsAdapter(getActivity()));


            editText.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {
                    // Abstract Method of TextWatcher Interface.
                }

                public void beforeTextChanged(CharSequence s,
                                              int start, int count, int after) {
                    // Abstract Method of TextWatcher Interface.
                }

                public void onTextChanged(CharSequence s,
                                          int start, int before, int count) {
                    textlength = editText.getText().length();
                    array_sort.clear();
                    for (int i = 0; i < listview_names.length; i++) {
                        if (textlength <= listview_names[i].length()) {
                            /***
                             * If you want to highlight the countries which start with
                             * entered letters then choose this block.
                             * And comment the below If condition Block
                             */
                        /*if(et.getText().toString().equalsIgnoreCase(
                                (String)
								listview_names[i].subSequence(0,
										textlength)))
						{
							array_sort.add(listview_names[i]);
							image_sort.add(listview_images[i]);
						}*/

                            /***
                             * If you choose the below block then it will act like a
                             * Like operator in the Mysql
                             */

                            if (listview_names[i].toLowerCase().contains(
                                    editText.getText().toString().toLowerCase().trim())) {
                                array_sort.add(listview_names[i]);
                            }
                        }
                    }
                    AppendList(array_sort);
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> arg0,
                                        View arg1, int position, long arg3) {
                    Toast.makeText(getActivity(), array_sort.get(position),
                            Toast.LENGTH_SHORT).show();
                    findDetailPhone(array_sort.get(position));
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setupConstant() {
        myConstant = new MyConstant();
        columnPassengerStrings = myConstant.getPassengerColumnStrings();
    }

    private void findDetailPhone(String strPhone) {

        try {

            Log.d(tag, "strPhone ==> " + strPhone);

            GetDataWhere getDataWhere = new GetDataWhere(getActivity());
            getDataWhere.execute(columnPassengerStrings[2], strPhone,
                    myConstant.getUrlGetPassengerWherePhone());
            String strJSON = getDataWhere.get();
            Log.d(tag, "JSON where ==> " + strJSON);

            String[] passengerStrings = new String[columnPassengerStrings.length];
            JSONArray jsonArray = new JSONArray(strJSON);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            for (int i = 0; i < columnPassengerStrings.length; i++) {
                passengerStrings[i] = jsonObject.getString(columnPassengerStrings[i]);
                Log.d(tag, "passengerString(" + i + ") ==> " + passengerStrings[i]);
            }   // for

            //Intent to Map
            Intent intent = new Intent(getActivity(), MapsActivity.class);
            intent.putExtra("Passenger", passengerStrings);
            startActivity(intent);

        } catch (Exception e) {
            Log.d("12decV2", "e findDetail ==> " + e.toString());
        }

    }   // findDetail

    public void AppendList(ArrayList<String> str) {
        setListAdapter(new bsAdapter(getActivity()));
    }

    public class bsAdapter extends BaseAdapter {
        Activity cntx;

        public bsAdapter(Activity context) {
            // TODO Auto-generated constructor stub
            this.cntx = context;

        }

        public int getCount() {
            // TODO Auto-generated method stub
            return array_sort.size();
        }

        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return array_sort.get(position);
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return array_sort.size();
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            View row = null;

            LayoutInflater inflater = cntx.getLayoutInflater();
            row = inflater.inflate(R.layout.search_list_item, null);

            TextView tv = (TextView) row.findViewById(R.id.title);

            tv.setText(array_sort.get(position));

            return row;
        }
    }    //bsAdapter


}   // Class
