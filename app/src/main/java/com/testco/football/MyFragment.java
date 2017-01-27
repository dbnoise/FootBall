package com.testco.football;

import android.support.v4.app.Fragment;

/**
 * Created by sergey on 1/26/17.
 */

public class MyFragment extends Fragment {

    public MainActivity getMainActivity() {
        return (MainActivity) getContext();
    }
}
