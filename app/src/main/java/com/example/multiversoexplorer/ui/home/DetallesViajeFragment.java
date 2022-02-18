package com.example.multiversoexplorer.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.transition.Transition;
import androidx.transition.TransitionInflater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

import com.example.multiversoexplorer.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetallesViajeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetallesViajeFragment extends Fragment {

    public DetallesViajeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Transition transition = TransitionInflater.from(requireContext())
                .inflateTransition(R.transition.shared_image_transition);
        setSharedElementEnterTransition(transition);
        postponeEnterTransition();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_detalles_viaje, container, false);
        String url = getArguments().getString("url");
        ImageView iv = root.findViewById(R.id.ivImageDetails);
        Picasso.get().load(url).into(iv, new Callback() {
            @Override
            public void onSuccess() {
                startPostponedEnterTransition();
            }

            @Override
            public void onError(Exception e) {
                startPostponedEnterTransition();
            }
        });
        return root;
    }
}