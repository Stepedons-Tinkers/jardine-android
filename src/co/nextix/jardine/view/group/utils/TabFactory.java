package co.nextix.jardine.view.group.utils;

import co.nextix.jardine.JardineApp;
import android.view.View;
import android.widget.TabHost.TabContentFactory;

public class TabFactory implements TabContentFactory {

	@Override
	public View createTabContent(String tag) {
		View v = new View(JardineApp.context);
		v.setMinimumWidth(0);
		v.setMinimumHeight(0);
		return v;
	}

}
