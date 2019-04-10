package com.MVP.team5.universenews.ui.fragment;


import android.annotation.TargetApi;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.MVP.team5.universenews.R;
import com.MVP.team5.universenews.databinding.FragmentSettingsBinding;
import com.MVP.team5.universenews.databinding.NavHeaderMainBinding;
import com.MVP.team5.universenews.ui.Utils.Utilities;
import com.MVP.team5.universenews.ui.activity.MainActivity;
import com.MVP.team5.universenews.ui.model.SettingsModel;

import top.defaults.colorpicker.ColorPickerPopup;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    Switch aSwitch;
    TextView fontSize, tvTheme, tvThemeSelect, tvThemeNews, tvThemeNewsSelect, tvFont;
    SeekBar skFont;

    FragmentSettingsBinding binding;
    SettingsModel settingsModel;

    Toolbar toolbar;
    View demoTheme;

    NavHeaderMainBinding headerMainBinding;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);
        settingsModel = new SettingsModel(
                Utilities.getTheme(getActivity()),
                Utilities.getFont(getActivity()),
                Utilities.getNight(getActivity())
        );
        View view = binding.getRoot();
        binding.setSettings(settingsModel);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = getActivity().findViewById(R.id.toolbar);
        initView();
        attatchEvent();
    }

    void initView() {
        tvFont = getActivity().findViewById(R.id.settings_tv_font);
        fontSize = getActivity().findViewById(R.id.settings_tv_font_size);
        skFont = getActivity().findViewById(R.id.settings_sk_font);
        demoTheme = getActivity().findViewById(R.id.theme_demo);

        binding.settingsTvFont.setTextSize(settingsModel.getFontSize());
        binding.settingsTvFontSize.setTextSize(settingsModel.getFontSize());
        binding.settingsTvFontSize.setText(String.valueOf(settingsModel.getFontSize()));
        binding.settingsTvTheme.setTextSize(settingsModel.getFontSize());
        binding.switch1.setTextSize(settingsModel.getFontSize());
        binding.settingsSkFont.setProgress(settingsModel.getFontSize());
        binding.switch1.setChecked(Utilities.getNight(getActivity()));
    }

    void attatchEvent() {
        skFont.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                fontSize.setText(String.valueOf(i));
                Utilities.saveFont(getContext(), i);
                settingsModel.setFontSize(i);

                binding.settingsTvFont.setTextSize(i);
                binding.settingsTvFontSize.setTextSize(i);
                binding.settingsTvTheme.setTextSize(i);
                binding.switch1.setTextSize(i);

                binding.executePendingBindings();
                binding.invalidateAll();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        demoTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new ColorPickerPopup.Builder(getContext())
                        .initialColor(Utilities.getTheme(getContext())) // Set initial color
                        .enableBrightness(true) // Enable brightness slider or not
                        .enableAlpha(true) // Enable alpha slider or not
                        .okTitle("Choose")
                        .cancelTitle("Cancel")
                        .showIndicator(true)
                        .showValue(true)
                        .build()
                        .show(v, new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {
                                v.setBackgroundColor(color);
                                demoTheme.setBackgroundColor(color);
                                toolbar.setBackgroundColor(color);

                                Utilities.saveTheme(getContext(), color);
                                Utilities.setStatusBarColor(getActivity(), color);

                                settingsModel.setThemeApp(color);

                                getActivity().findViewById(R.id.mainHeader).setBackgroundColor(color);

                                demoTheme.setBackgroundColor(color);

                                binding.executePendingBindings();
                                binding.invalidateAll();
                            }
                        });
            }
        });

        binding.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Utilities.saveNight(getContext(), isChecked);
                settingsModel.setNightShift(isChecked);
                getActivity().findViewById(R.id.nav_view).setBackgroundColor(settingsModel.getNightShift());
                getActivity().findViewById(R.id.flContents).setBackgroundColor(settingsModel.getNightShift());
                binding.executePendingBindings();
                binding.invalidateAll();
            }
        });
    }
}
