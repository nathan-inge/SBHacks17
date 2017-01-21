package hacks.sb.sbhacks17;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.portal.Portal;
import com.esri.arcgisruntime.portal.PortalInfo;

public class Home extends AppCompatActivity {
    public final static String POP_FLOOR = "POP_FLOOR";
    public final static String POP_CEILING = "POP_CEILING";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("DreamHouse");

        //Population Spinner
        final Spinner spinnerPop = (Spinner) findViewById(R.id.spinnerPop);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pop_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPop.setAdapter(adapter);






    }

    public void showMap(View view) {

        Intent intent = new Intent(this, MainActivity.class);

        //Pop values
        Spinner spinnerPop = (Spinner) findViewById(R.id.spinnerPop);
        String pop_range = spinnerPop.getSelectedItem().toString();
        String[] parts = pop_range.split(" - ");
        String pop_floor_str = parts[0];
        String pop_ceiling_str = parts[1];
        int pop_floor = Integer.parseInt(pop_floor_str);
        int pop_ceiling = Integer.parseInt(pop_floor_str);

        intent.putExtra(POP_FLOOR, pop_floor_str);
        intent.putExtra(POP_CEILING, pop_ceiling_str);

        startActivity(intent);
    }

    public void toToPoMap(View view) {

        Intent intent = new Intent(this, TopographicMap.class);
        startActivity(intent);
    }


}


