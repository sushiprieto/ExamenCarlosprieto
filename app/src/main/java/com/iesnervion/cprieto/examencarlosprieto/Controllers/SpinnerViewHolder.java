package com.iesnervion.cprieto.examencarlosprieto.Controllers;

import android.view.View;
import android.widget.TextView;

/**
 * Created by cprieto on 14/12/16.
 *
 * se usa para los dos spinners
 *
 */

public class SpinnerViewHolder {

    TextView numero;

    public SpinnerViewHolder(View row, int numeroId) {

        this.numero = (TextView) row.findViewById(numeroId);

    }

    public TextView getNumero() {
        return this.numero;
    }

}
