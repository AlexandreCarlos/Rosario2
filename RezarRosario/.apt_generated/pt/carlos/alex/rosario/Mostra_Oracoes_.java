//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package pt.carlos.alex.rosario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pt.carlos.alex.rosario.R.layout;

public final class Mostra_Oracoes_
    extends Mostra_Oracoes
{

    private View contentView_;

    private void init_() {
        inicializa();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_();
        super.onCreate(savedInstanceState);
    }

    private void afterSetContentView_() {
        oracoesText = ((TextView) findViewById(pt.carlos.alex.rosario.R.id.oracoesText));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView_ = super.onCreateView(inflater, container, savedInstanceState);
        if (contentView_ == null) {
            contentView_ = inflater.inflate(layout.oracoes_layout, container, false);
        }
        afterSetContentView_();
        return contentView_;
    }

    public View findViewById(int id) {
        if (contentView_ == null) {
            return null;
        }
        return contentView_.findViewById(id);
    }

}
