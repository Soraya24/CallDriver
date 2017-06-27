package nakthon.soraya.calldriver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by masterUNG on 6/27/2017 AD.
 */

public class ReportPureJobAdapter extends BaseAdapter {

    private Context context;
    private String[] column1Strings, column2Strings, column3Strings,
            column4Strings, column5Strings, column6Strings, column7Strings;
    private TextView[] textViews;
    private int[] ints = new int[]{R.id.txtColumn1l, R.id.txtColumn2l, R.id.txtColumn3l,
            R.id.txtColumn4l, R.id.txtColumn5l, R.id.txtColumn6l, R.id.txtColumn7l};

    public ReportPureJobAdapter(Context context,
                                String[] column1Strings,
                                String[] column2Strings,
                                String[] column3Strings,
                                String[] column4Strings,
                                String[] column5Strings,
                                String[] column6Strings,
                                String[] column7Strings) {
        this.context = context;
        this.column1Strings = column1Strings;
        this.column2Strings = column2Strings;
        this.column3Strings = column3Strings;
        this.column4Strings = column4Strings;
        this.column5Strings = column5Strings;
        this.column6Strings = column6Strings;
        this.column7Strings = column7Strings;
    }

    @Override
    public int getCount() {
        return column1Strings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.listview_report_pure_job, viewGroup, false);

        //Initial View
        textViews = new TextView[7];
        for (int i1 = 0; i1 < ints.length; i1 += 1) {
            textViews[i1] = (TextView) view1.findViewById(ints[i1]);
        }

       // textViews[0] = view1.findViewById(R.id.txtColumn1l);

        textViews[0].setText(column1Strings[i]);
        textViews[1].setText(column2Strings[i]);
        textViews[2].setText(column3Strings[i]);
        textViews[3].setText(column4Strings[i]);
        textViews[4].setText(column5Strings[i]);
        textViews[5].setText(column6Strings[i]);
        textViews[6].setText(column7Strings[i]);


        return view1;
    }
}   // Main Class
