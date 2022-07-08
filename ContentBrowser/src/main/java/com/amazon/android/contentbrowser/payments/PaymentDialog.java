package com.amazon.android.contentbrowser.payments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amazon.android.contentbrowser.R;
import com.amazon.android.model.content.Content;

import java.lang.reflect.Field;
import java.util.Locale;

import static com.amazon.android.contentbrowser.ContentBrowser.PRICE_MAP;
import static com.amazon.android.contentbrowser.payments.FundableHelper.XRP_ADDRESS;
import static com.amazon.android.contentbrowser.payments.PayIdHelper.Fundable_SERVER;
import static com.amazon.android.contentbrowser.payments.PayIdHelper.HTTP_CLIENT;

public class PaymentDialog {

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void createPayIdInputDialog(Activity context,
                                              Content content,
                                              DialogInterface.OnClickListener onClickListener)
            throws Exception {

        ViewGroup subView = (ViewGroup) context.getLayoutInflater().// inflater view
                inflate(R.layout.pay_id_input_dialog, null, false);
        TextView purchaseText = subView.findViewById(R.id.pay_id_text);
        purchaseText.setText(String.format(Locale.US, "Scan the QR code below with your mobile wallet to give funding to\n%s.", content.getTitle()));

        TextView conversionText = subView.findViewById(R.id.conversion_text);
        final String text = String.format(Locale.US, "You'll be able to specify the amount and receive a transaction receipt post-payment!");
        conversionText.setText(text);

        ImageView v = subView.findViewById(R.id.xrpImage);
        int resID = getResId("qrcode" + content.getId(), R.drawable.class);
        v.setImageResource(resID);
        Log.d("createPayIdInputDialog", content.toString());

        new Handler(Looper.getMainLooper()).post(() -> {
            new AlertDialog.Builder(context)
                    .setView(subView)
                    .setTitle("Scan the XRP address to continue")
                    .setPositiveButton("Done", onClickListener)
                    .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.cancel())
                    .show();

        });

    }
}
