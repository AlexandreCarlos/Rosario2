package pt.carlos.alex.rosario;

import android.util.Log;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.ViewById;

import de.greenrobot.event.EventBus;

@EActivity(R.layout.activity_mostra_oracoes)
@OptionsMenu(R.menu.activity_main)
public class ActivityMostraOracoes extends SherlockFragmentActivity {

	final static String TAG = "Rosário.MostraOracoes Activity";

	@ViewById(R.id.dia_semana_p)
	protected TextView dia_semana;

	private EventBus eventBus;
//	private GregorianCalendar calendario;
	protected int index_dia_semana = -1;
	protected int misterio_selected = 0;
	protected int pagina_actual = 0;
	protected boolean mDualPage = false;
	
	@AfterInject
	void startup() {
//		calendario = (GregorianCalendar) GregorianCalendar.getInstance();
//
//		index_dia_semana = calendario.get(Calendar.DAY_OF_WEEK);

		eventBus = EventBus.getDefault();

		index_dia_semana = getIntent().getIntExtra(V.DIA, -1);
		misterio_selected = getIntent().getIntExtra(V.MISTERIO, -1);
		pagina_actual = getIntent().getIntExtra(V.PAGINA, -1);
		
		if (V.DEBUG) {
			Log.d(TAG, "IntentExtra values -Dia:"+index_dia_semana+"; Mistério:"+misterio_selected+"; Página:"+pagina_actual);
			Log.d(TAG, "Dia Semana:"+V.DIA_SEMANA[index_dia_semana]);
		}

		if (V.DEBUG) {
			Log.d(TAG, "mIndexDiaSemana: " + index_dia_semana);
		}
	}

	@AfterViews
	void init() {

		try {
//			eventBus.register(this);

//			mIndexDiaSemana = getIntent().getIntExtra(V.DIA, -1);
//			mMisterioSelected = getIntent().getIntExtra(V.MISTERIO, -1);
//			mPaginaActual = getIntent().getIntExtra(V.PAGINA, -1);
//			
//			if (V.DEBUG) {
//				Log.d(TAG, "IntentExtra values -Dia:"+mIndexDiaSemana+"; Mistério:"+mMisterioSelected+"; Página:"+mPaginaActual);
//				Log.d(TAG, "Dia Semana:"+V.DIA_SEMANA[mIndexDiaSemana]);
//			}

			dia_semana.setText(V.DIA_SEMANA[index_dia_semana]);
			
			eventBus.post(new Estado(index_dia_semana, misterio_selected, pagina_actual, false));
			
		} catch (Exception e) {
			Log.e(TAG, "Erro no init() @AfterViews:", e);
		}

	}
	
	
}
