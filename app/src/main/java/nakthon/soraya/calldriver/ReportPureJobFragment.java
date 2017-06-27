package nakthon.soraya.calldriver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by masterUNG on 6/26/2017 AD.
 */

public class ReportPureJobFragment extends Fragment{

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

        ListView listView = getView().findViewById(R.id.livPureJob);

        String[] strings = new String[]{"Test1", "Test2", "Test3", "dffd", "dfdf", "dfdff"};
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, strings);
        listView.setAdapter(stringArrayAdapter);

    }

    private void showColumn() {
        MyConstant myConstant = new MyConstant();
        String[] columnStrings = myConstant.getColumnReportPureJobFragmentStrings();
        for (int i=0;i<ints.length;i+=1) {
            textViews[i] = getView().findViewById(ints[i]);
            textViews[i].setText(columnStrings[i]);
        }
    }
}   // Main Class
