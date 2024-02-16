package com.ntd.appid1.appid2.appid3.screens.rate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.ntd.appid1.appid2.appid3.R;
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseDialogAppntd;
import com.ntd.appid1.appid2.appid3.databinding.DialogRatingAppBinding;

public class NTDOtherRatingDialog extends NTDOtherBaseDialogAppntd<DialogRatingAppBinding> {
    private OnPress onPress;
    private boolean isRated = false;

    @Override
    protected DialogRatingAppBinding setViewBinding(Context context) {
        return DialogRatingAppBinding.inflate(LayoutInflater.from(context));
    }

    @Override
    protected void initView(Context context) {
        onclick(context);
        changeRating();
        binding.rtb.setRating(5f);
    }

    public NTDOtherRatingDialog(Context context2) {
        super(context2);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(attributes);
        getWindow().setSoftInputMode(16);
    }

    public interface OnPress {
        void send();

        void rating();

        void later();
    }

    public void init(OnPress onPress) {
        this.onPress = onPress;
    }

    public void changeRating() {
        binding.rtb.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            String getRating = String.valueOf(binding.rtb.getRating());
            switch (getRating) {
                case "1.0":
                    binding.imgIcon.setImageResource(R.drawable.ic_rate_1);
                    break;
                case "2.0":
                    binding.imgIcon.setImageResource(R.drawable.ic_rate_2);
                    break;
                case "3.0":
                    binding.imgIcon.setImageResource(R.drawable.ic_rate_3);
                    break;
                case "4.0":
                    binding.imgIcon.setImageResource(R.drawable.ic_rate_4);
                    break;
                case "5.0":
                    binding.imgIcon.setImageResource(R.drawable.ic_rate_5);
                    break;
                default:
                    binding.imgIcon.setImageResource(R.drawable.ic_rate_1);
                    break;
            }
        });


    }

    private void onclick(Context ctx) {
        binding.btnRate.setOnClickListener(view -> {
            if (binding.rtb.getRating() == 0) {
                Toast.makeText(ctx, ctx.getResources().getString(R.string.please_feedback), Toast.LENGTH_SHORT).show();
                return;
            }

            if (binding.rtb.getRating() < 5) {
                onPress.send();
            } else {
                onPress.rating();
            }

            if (!isRated) {
                binding.loRate.setVisibility(View.GONE);
                binding.loThanks.setVisibility(View.VISIBLE);
                isRated = true;
            } else {
                dismiss();
            }
        });


        binding.cvOk.setOnClickListener(v -> {
            dismiss();
        });

        binding.btnLater.setOnClickListener(view -> onPress.later());

    }

}

