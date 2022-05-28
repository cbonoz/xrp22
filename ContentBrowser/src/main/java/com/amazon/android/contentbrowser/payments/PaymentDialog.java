package com.amazon.android.contentbrowser.payments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amazon.android.contentbrowser.R;
import com.amazon.android.model.content.Content;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import static com.amazon.android.contentbrowser.ContentBrowser.PRICE_MAP;
import static com.amazon.android.contentbrowser.payments.FundableHelper.XRP_ADDRESS;
import static com.amazon.android.contentbrowser.payments.PayIdHelper.Fundable_SERVER;
import static com.amazon.android.contentbrowser.payments.PayIdHelper.HTTP_CLIENT;

public class PaymentDialog {


    public static void createPayIdInputDialog(Activity context,
                                              Content content,
                                              DialogInterface.OnClickListener onClickListener)
            throws Exception {

        ViewGroup subView = (ViewGroup) context.getLayoutInflater().// inflater view
                inflate(R.layout.pay_id_input_dialog, null, false);

        TextView purchaseText = subView.findViewById(R.id.pay_id_text);
        purchaseText.setText(String.format(Locale.US, "Scan with your mobile wallet to give funding to %s.", content.getTitle()));

        TextView conversionText = subView.findViewById(R.id.conversion_text);
        final String text = String.format(Locale.US, "Fund by scanning one of the below QR codes:");
        conversionText.setText(text);

        final String finalXrpAddress = XRP_ADDRESS;
        Picasso picasso = new Picasso.Builder(context).downloader(new OkHttp3Downloader(HTTP_CLIENT)).build();
        picasso.setLoggingEnabled(true);
        new Handler(Looper.getMainLooper()).post(() -> {

            if (finalXrpAddress != null) {
                String url = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + finalXrpAddress;
                ImageView v = subView.findViewById(R.id.xrpImage);
                picasso.load(url).into(v);
            }

            new AlertDialog.Builder(context)
                    .setView(subView)
                    .setTitle("Scan the XRP address to continue")
                    .setPositiveButton("Done", onClickListener)
                    .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.cancel())
                    .show();

        });

    }
}
