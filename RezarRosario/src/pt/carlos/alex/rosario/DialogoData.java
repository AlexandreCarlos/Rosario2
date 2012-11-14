/**
 * 
 */
package pt.carlos.alex.rosario;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import de.greenrobot.event.EventBus;

/**
 * @author alexandre
 * 
 */
public class DialogoData extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {

	private EventBus eventBus;
	// Use the current date as the default date in the picker
	final Calendar c = Calendar.getInstance();
	int year = c.get(Calendar.YEAR);
	int month = c.get(Calendar.MONTH);
	int day = c.get(Calendar.DAY_OF_MONTH);

	/**
	 * 
	 */
	@Override
	public Dialog onCreateDialog(final Bundle savedInstanceState) {

		eventBus = EventBus.getDefault();

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	public void onDateSet(final DatePicker view, final int pYear,
			final int pMonth, final int pDay) {
		c.set(pYear, pMonth, pDay);

		eventBus.post(new Rezar(c.get(Calendar.DAY_OF_WEEK), 1, 0));
	}

}
