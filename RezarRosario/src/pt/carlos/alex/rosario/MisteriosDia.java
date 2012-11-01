/**
 * 
 */
package pt.carlos.alex.rosario;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;

import de.greenrobot.event.EventBus;

/**
 * @author alexandre
 * 
 */
@EFragment
public class MisteriosDia extends SherlockListFragment {
	final static String TAG = "Rosário.misterios_dia";
	// final static boolean DEBUG = true;

	private int mIndexDiaSemana = -1;
	private boolean mDualPage = false;
	private MainActivity mMa = null;
	private EventBus mEventBus;

	@ViewById(R.id.oracoes)
	protected View mOracoes;

	@AfterInject
	void obterDiaSemana() {

		mEventBus = EventBus.getDefault();

		mMa = (MainActivity) getActivity();
		mIndexDiaSemana = mMa.mIndexDiaSemana;
		this.mDualPage = mMa.mDualPage;
		mMa = null;

		if (V.DEBUG) {
			Log.d(TAG, "Inicializa-mIndexDiaSemana: " + mIndexDiaSemana
					+ "; mDualPage:" + mDualPage);
		}

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// setListAdapter(new ArrayAdapter<String>(getActivity(),
		// android.R.layout.simple_list_item_1, android.R.id.text1,
		// Misterios.Design__Misterios(mIndexDiaSemana)));

		setListAdapter(new ArrayAdapter<String>(getActivity(),
				R.layout.misterio_list_itemlayout, R.id.item_misterio,
				Misterios.designacaoMisterios(mIndexDiaSemana)));

		// getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		// getListView().setSelector(R.color.ics_bold_red);

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		if (V.DEBUG) {
			Log.d(TAG, "List Item Click: " + position);

		}

		// ma.misterio_selected = position; //Persistência do estado (mistério
		// seleccionado)

		// v.getFocusables(position);
		// v.setSelected(true);
		mMa = (MainActivity) getActivity();
		this.mDualPage = mMa.mDualPage;
		mMa = null;

		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		l.setItemChecked(position, true);

		if (mDualPage) {
			mEventBus.post(new Rezar(mIndexDiaSemana, position, 0));
			Log.i(TAG, "Rezar eventBus generated");
		} else {
			Intent intent = new Intent(this.getActivity(),
					ActivityMostraOracoes_.class);
			intent.putExtra(V.DIA, mIndexDiaSemana);
			intent.putExtra(V.MISTERIO, position);
			intent.putExtra(V.PAGINA, 0);
			Log.d(TAG, "MostraOracoes_.class Intent generated & started");
			startActivity(intent);
		}

	}

}
