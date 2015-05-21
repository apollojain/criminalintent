package com.example.apollo.criminalintent;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;


public class CrimeFragment extends android.support.v4.app.Fragment {
            // TODO: Rename parameter arguments, choose names that match
            // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
            private Crime mCrime;
            private EditText mTitleField;
            private Button mDateButton;
            private CheckBox mSolvedCheckBox;
            private OnFragmentInteractionListener mListener;
    public static final String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";

            public CrimeFragment() {
                // Required empty public constructor
            }

            public static CrimeFragment newInstance(UUID crimeId){
                Bundle args = new Bundle();
                args.putSerializable(EXTRA_CRIME_ID, crimeId);
                CrimeFragment fragment = new CrimeFragment();
                fragment.setArguments(args);
                return fragment;
            }

            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                //mCrime = new Crime();
                UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
                mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
            }

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                    Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                View v = inflater.inflate(R.layout.fragment_crime, container, false);
                mTitleField = (EditText) v.findViewById(R.id.crime_title);
                mTitleField.setText(mCrime.getTitle());
                mTitleField.addTextChangedListener(new TextWatcher(){
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    public void onTextChanged(CharSequence c, int start, int before, int count){
                        mCrime.setTitle(c.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                mDateButton = (Button) v.findViewById (R.id.crime_date);
                String s = mCrime.getDate().toString();
                String[] preprocessed = s.split(" ");
                mDateButton.setText(preprocessed[1] + " " + preprocessed[2] +", " + preprocessed[preprocessed.length - 1]);
                mDateButton.setEnabled(false);

                mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
                mSolvedCheckBox.setChecked(mCrime.isSolved());
                mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        //set crime's solved property
                        mCrime.setSolved(isChecked);
                    }
                });

                return v;
            }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
