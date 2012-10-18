package pt.carlos.alex.rosario;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class OracoesFragment extends SherlockFragment {
	private static final String KEY_ORACAO = "oracao";
	private static final String TAG = "OracoesFragment";
	private static final boolean DEBUG = true;
//	String oracao;
	
	static OracoesFragment newInstance(String oracao) {
		OracoesFragment frag = new OracoesFragment();
		Bundle args = new Bundle();

		if (DEBUG) {
			Log.d(TAG, "newInstance-oração:" + oracao);
		}
		
		args.putString(KEY_ORACAO, oracao);
		// args.putInt(KEY_ORACAO, oracao);
		frag.setArguments(args);

		return (frag);
	}
//	public OracoesFragment (String oracao) {
//		this.oracao = oracao;
//	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View result = inflater.inflate(R.layout.oracoes_layout, container,
				false);
		TextView editor = (TextView) result.findViewById(R.id.oracoesText);
		String oracao = getArguments().getString(KEY_ORACAO);

		if (DEBUG) {
			Log.d(TAG, "onCreateView-oração:" + oracao);
		}
		
		editor.setText(Html.fromHtml(oracao));

		return (result);
	}
}
