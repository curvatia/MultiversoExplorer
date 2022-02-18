package com.example.multiversoexplorer.ui.home;

import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.transition.Transition;
import androidx.transition.TransitionInflater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.multiversoexplorer.R;

public class ViajeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private ImageView image;

    public static ViajeFragment newInstance() {
        return new ViajeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Transition transition = TransitionInflater.from(requireContext())
                .inflateTransition(R.transition.shared_image);
        setSharedElementEnterTransition(transition);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.viaje_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        image = root.findViewById(R.id.imgPicF);
        ViewCompat.setTransitionName(image, "heroImageTransition");
        return root;
    }

}