/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.nextix.jardine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import co.nextix.jardine.activities.add.fragments.AddActivityWithCoSMRsFragment;
import co.nextix.jardine.activities.update.fragments.EditActivityInfoFragment;

public class SuperAwesomeCardFragment extends Fragment {

	private static final String ARG_POSITION = "position";

	private int position;

	public static FrameLayout fl;

	public static SuperAwesomeCardFragment newInstance(int position) {
		SuperAwesomeCardFragment f = new SuperAwesomeCardFragment();
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		f.setArguments(b);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		position = getArguments().getInt(ARG_POSITION);
	}

	@Override
	public View onCreateView(final LayoutInflater inflater,
			final ViewGroup container, Bundle savedInstanceState) {

		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);

		fl = new FrameLayout(getActivity());
		fl.setLayoutParams(params);
		fl.setId(0123);

//		FragmentTransaction ft = getFragmentManager().beginTransaction();
//		
//		EditActivityInfoFragment.instantiate(getActivity(), EditActivityInfoFragment.class.getName());
//		AddActivityWithCoSMRsFragment.instantiate(getActivity(), AddActivityWithCoSMRsFragment.class.getName());
//
//		if (position == 0) {
//			Log.e("0", "here");
//			ft.replace(fl.getId(), new EditActivityInfoFragment());
//			ft.addToBackStack(null);
//			ft.commit();
//		} else if (position == 1) {
//			Log.e("1", "here");
//			ft.replace(fl.getId(), new AddActivityWithCoSMRsFragment());
//			ft.addToBackStack(null);
//			ft.commit();
//		}

		return fl;
	}

}