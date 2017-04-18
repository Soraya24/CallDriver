package nakthon.soraya.calldriver;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.Arrays;

public class ServiceActivity extends ListActivity {

    private EditText et;
    private ListView lv;
    private String[] listview_names = {"1234", "423", "12", "4231", "4123", "abcd",
            "abc", "ab", "xyzzz",
            "xy", "xxxx"};

    private ArrayList<String> array_sort;
    int textlength = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        try {

            et = (EditText) findViewById(R.id.edtSearch);
            lv = (ListView) findViewById(android.R.id.list);

//            MySynShop mySynShop = new MySynShop(SearchShop.this);
//            mySynShop.execute();
//            String strJSON = mySynShop.get();
//
//            JSONArray jsonArray = new JSONArray(strJSON);
//            listview_names = new String[jsonArray.length()];
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                listview_names[i] = jsonObject.getString("Shop");
//
//            }   // for




            array_sort = new ArrayList<String>(Arrays.asList(listview_names));
            setListAdapter(new bsAdapter(this));


            et.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {
                    // Abstract Method of TextWatcher Interface.
                }

                public void beforeTextChanged(CharSequence s,
                                              int start, int count, int after) {
                    // Abstract Method of TextWatcher Interface.
                }

                public void onTextChanged(CharSequence s,
                                          int start, int before, int count) {
                    textlength = et.getText().length();
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
                                    et.getText().toString().toLowerCase().trim())) {
                                array_sort.add(listview_names[i]);
                            }
                        }
                    }
                    AppendList(array_sort);
                }
            });

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> arg0,
                                        View arg1, int position, long arg3) {
                    Toast.makeText(getApplicationContext(), array_sort.get(position),
                            Toast.LENGTH_SHORT).show();
                    findDetailShop(array_sort.get(position));
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }


    }   // Main Method

    private void findDetailShop(String nameShop) {

        try {

//            SynShopData synShopData = new SynShopData(SearchShop.this);
//            synShopData.execute(nameShop);
//            String strResult = synShopData.get();
//            Log.d("12decV2", "strResult ==> " + strResult);
//
//            JSONArray jsonArray = new JSONArray(strResult);
//            String[] strings = new String[8];
//            JSONObject jsonObject = jsonArray.getJSONObject(0);
//            strings[0] = jsonObject.getString(MyManage.column_Shop);
//            strings[1] = jsonObject.getString(MyManage.column_Address);
//            strings[2] = jsonObject.getString(MyManage.column_Phone);
//            strings[3] = jsonObject.getString(MyManage.column_Service);
//            strings[4] = jsonObject.getString(MyManage.column_Image);
//            strings[5] = jsonObject.getString(MyManage.column_Icon);
//            strings[6] = jsonObject.getString(MyManage.column_Lat);
//            strings[7] = jsonObject.getString(MyManage.column_Lng);
//
//            Intent intent = new Intent(SearchShop.this, DetailActivity.class);
//            intent.putExtra("Shop", strings);
//            startActivity(intent);

        } catch (Exception e) {
            Log.d("12decV2", "e findDetail ==> " + e.toString());
        }

    }   // findDetail

    public void AppendList(ArrayList<String> str) {
        setListAdapter(new bsAdapter(this));
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
    }
}