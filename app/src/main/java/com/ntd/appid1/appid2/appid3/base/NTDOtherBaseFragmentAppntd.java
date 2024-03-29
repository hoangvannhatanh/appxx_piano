package com.ntd.appid1.appid2.appid3.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.ntd.appid1.appid2.appid3.local.NTDHelperPreferencesHelper;
import com.ntd.appid1.appid2.appid3.local.NTDHelperSharePrefUtils;
import com.ntd.appid1.appid2.appid3.utils.NTDOtherUtils;

public abstract class NTDOtherBaseFragmentAppntd<T extends ViewBinding> extends Fragment {
    protected T binding;

    protected abstract T setViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup viewGroup);

    //setupView here
    protected abstract void initView();

    //listen to user action here
    protected abstract void viewListener();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        NTDHelperSharePrefUtils.init(requireActivity());
        NTDHelperPreferencesHelper.init(requireActivity());
        NTDOtherUtils.INSTANCE.init(requireActivity());
        NTDOtherUtils.INSTANCE.setLocale(requireActivity(), NTDHelperPreferencesHelper.getLanguage());
        binding = setViewBinding(inflater, container);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        viewListener();
    }
}
